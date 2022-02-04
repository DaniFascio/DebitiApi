package org.experimentalplayers.debiti_api.services.impls;

import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.experimentalplayers.debiti_api.configs.Status;
import org.experimentalplayers.debiti_api.exceptions.UserException;
import org.experimentalplayers.debiti_api.models.StoricoDebito;
import org.experimentalplayers.debiti_api.repositories.AnagraficaRepository;
import org.experimentalplayers.debiti_api.repositories.StoricoDebitoRepository;
import org.experimentalplayers.debiti_api.requests.NewDebitoRequest;
import org.experimentalplayers.debiti_api.requests.SearchDebitiRequest;
import org.experimentalplayers.debiti_api.requests.UpdateDebitoRequest;
import org.experimentalplayers.debiti_api.responses.SearchDebitiResponse;
import org.experimentalplayers.debiti_api.services.DebitoService;
import org.experimentalplayers.debiti_api.utils.DateUtil;
import org.experimentalplayers.debiti_api.utils.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Slf4j
@Service
public class DebitoServiceImpl implements DebitoService {

    @Autowired
    StoricoDebitoRepository storicoDebitoRepository;

    @Autowired
	AnagraficaRepository anagraficaRepository;

    @Override
    public Page<SearchDebitiResponse> findAll(SearchDebitiRequest filter) {

        log.debug("Begin findAll(SearchDebitiRequest)... DebitoServiceImpl: " + filter.toString());

        PaginationRequest pagination = filter.getPagination();

        Pageable pageable = PageRequest.of(pagination.getOffset(), 10, Sort.Direction.ASC, pagination.getOrderBy());

        //TODO: controllare corretto funzionamento ricerca per nominativi, possibile errore settaggio regex.
        filter.setNominativoDebitore(filter.getNominativoDebitore().toUpperCase(Locale.ROOT).replaceAll("\\s+", "").replace("", "%"));
        filter.setNominativoCreditore(filter.getNominativoCreditore().toUpperCase(Locale.ROOT).replaceAll("\\s+", "").replace("", "%"));

        if (filter.getDataCreazioneA() != null)
            filter.setDataCreazioneA(DateUtil.setEndOfDay(filter.getDataCreazioneA()));

        if (filter.getDataSaldatoA() != null)
            filter.setDataSaldatoA(DateUtil.setEndOfDay(filter.getDataSaldatoA()));

        Page<SearchDebitiResponse> pageDebts = storicoDebitoRepository.findAllByFilter(filter.getNominativoDebitore(), filter.getNominativoCreditore(),
                filter.getDataCreazioneDa(), filter.getDataCreazioneA(), filter.getDataSaldatoDa(), filter.getDataSaldatoA(),
                filter.getDebitoDa(), filter.getDebitoA(), filter.getSaldato(), filter.getValido(), pageable);

        log.debug("End findAll()... DebitoServiceImpl");
        return pageDebts;
    }

    @Override
    public Double totaleDebtUser(Integer idUser) {

        List<StoricoDebito> list = storicoDebitoRepository.findAllByUtenteDebitore_Id(idUser);
        Double tot = 0.0;

        for (StoricoDebito storicoDebito : list) {
            tot += storicoDebito.getDebito();

        }

        return tot;
    }

    @Override
    public StoricoDebito insert(NewDebitoRequest newDebitoRequest) throws Exception {

        log.debug("Begin insert(NewDebitoRequest)... DebitoServiceImpl");
        StoricoDebito storicoDebito = null;

        try {

            storicoDebito = setDefaultDebito(newDebitoRequest);
            storicoDebitoRepository.save(storicoDebito);

        } catch (Exception e) {
            log.debug(Status.ERR_INSERT.getKey());
        }

        log.debug("End insert(NewDebitoRequest)... DebitoServiceImpl");
        return storicoDebito;

    }

    @Override
    public StoricoDebito update(UpdateDebitoRequest updateDebitoRequest) {
        log.debug("Begin update(UpdateDebitoRequest)... DebitoServiceImpl");

        StoricoDebito storicoDebito = new StoricoDebito();
        try {
            Optional<StoricoDebito> optDebito = storicoDebitoRepository.findById(updateDebitoRequest.getIdDebito());
            if (optDebito.isPresent())
                storicoDebito = optDebito.get();
            else
                throw new Exception();

            setDefaultDebitoForUpdate(storicoDebito, updateDebitoRequest);

            Integer num = storicoDebitoRepository.update(storicoDebito.getId(), storicoDebito.getDebito(), storicoDebito.getUtenteCreditore().getId(),
                    storicoDebito.getUtenteDebitore().getId(), storicoDebito.getSaldato(), storicoDebito.getDataSaldato(), storicoDebito.getValido());

        } catch (Exception e) {
            log.debug(Status.ERR_UPDATE.getKey());
        }


        log.debug("End update(UpdateDebitoRequest)... DebitoServiceImpl");
        return storicoDebito;
    }

    @Override
    public StoricoDebito salda(Integer idDebito) {
        log.debug("Begin salda(Integer)... DebitoServiceImpl: " + idDebito);

        StoricoDebito storicoDebito = null;
        try {
            Optional<StoricoDebito> optDebito = storicoDebitoRepository.findById(idDebito);
            if (optDebito.isPresent())
                storicoDebito = optDebito.get();
            else
                throw new Exception();

            Integer num = storicoDebitoRepository.salda(idDebito);

        } catch (Exception e) {
            log.debug(Status.ERR_UPDATE.getKey());
        }

        return storicoDebito;
    }

    @Override
    public StoricoDebito delete(Integer idDebito) {
        log.debug("Begin delete(Integer)... DebitoServiceImpl: " + idDebito);

        StoricoDebito storicoDebito = null;
        try {

            Optional<StoricoDebito> optDebito = storicoDebitoRepository.findById(idDebito);
            if (optDebito.isPresent())
                storicoDebito = optDebito.get();
            else
                throw new Exception();

            Integer num = storicoDebitoRepository.setValidoFalse(idDebito);
            storicoDebito.setValido(false);


        } catch (Exception e) {
            log.debug(Status.ERR_UPDATE.getKey());
        }

        return storicoDebito;
    }

    private StoricoDebito setDefaultDebito(NewDebitoRequest newDebitoRequest) throws Exception {
        log.debug("Begin setDefaultDebito(NewDebitoRequest)... DebitoServiceImpl");

        StoricoDebito storicoDebito = new StoricoDebito();
        try {

            storicoDebito.setDebito(newDebitoRequest.getDebito());
            storicoDebito.setDataInserimento(new Date());
            if (anagraficaRepository.findById(newDebitoRequest.getIdNominativoCreditore()).isPresent())
                storicoDebito.setUtenteCreditore(anagraficaRepository.findById(newDebitoRequest.getIdNominativoCreditore()).get());
            else
                throw new UserException(String.valueOf(Status.WARN_NO_SUCH_ELEMENT));

            if (anagraficaRepository.findById(newDebitoRequest.getIdNominativoDebitore()).isPresent())
                storicoDebito.setUtenteDebitore(anagraficaRepository.findById(newDebitoRequest.getIdNominativoDebitore()).get());
            else
                throw new UserException(String.valueOf(Status.WARN_NO_SUCH_ELEMENT));

            if (newDebitoRequest.getSaldato()) {
                storicoDebito.setSaldato(true);
                storicoDebito.setDataSaldato(newDebitoRequest.getDataSaldato());
            }

        } catch (Exception e) {
            throw new Exception(Status.ERR_GENERICO.getKey());
        }

        return storicoDebito;

    }

    private void setDefaultDebitoForUpdate(StoricoDebito storicoDebito, UpdateDebitoRequest updateDebitoRequest) throws Exception {
        log.debug("Begin setDefaultDebitoForUpdate(StoricoDebito, UpdateDebitoRequest)... DebitoServiceImpl");

        try {

            storicoDebito.setDebito(updateDebitoRequest.getDebito());
            storicoDebito.setDataInserimento(new Date());
            if (anagraficaRepository.findById(updateDebitoRequest.getIdNominativoCreditore()).isPresent())
                storicoDebito.setUtenteCreditore(anagraficaRepository.findById(updateDebitoRequest.getIdNominativoCreditore()).get());
            else
                throw new UserException(String.valueOf(Status.WARN_NO_SUCH_ELEMENT));

            if (anagraficaRepository.findById(updateDebitoRequest.getIdNominativoDebitore()).isPresent())
                storicoDebito.setUtenteDebitore(anagraficaRepository.findById(updateDebitoRequest.getIdNominativoDebitore()).get());
            else
                throw new UserException(String.valueOf(Status.WARN_NO_SUCH_ELEMENT));

            if (updateDebitoRequest.getSaldato() != null) {
                if (updateDebitoRequest.getSaldato()) {
                    storicoDebito.setSaldato(true);
                    storicoDebito.setDataSaldato(updateDebitoRequest.getDataSaldato());
                } else
                    storicoDebito.setSaldato(false);
            }

            if (updateDebitoRequest.getValido() != null)
                storicoDebito.setValido(updateDebitoRequest.getValido());

        } catch (Exception e) {
            throw new Exception(Status.ERR_GENERICO.getKey());
        }


    }

}
