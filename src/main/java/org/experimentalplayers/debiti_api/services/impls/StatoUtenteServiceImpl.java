package org.experimentalplayers.debiti_api.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.experimentalplayers.debiti_api.models.StatoUtente;
import org.experimentalplayers.debiti_api.repositories.StatoUtenteRepository;
import org.experimentalplayers.debiti_api.services.StatoUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class StatoUtenteServiceImpl implements StatoUtenteService {

    @Autowired
	StatoUtenteRepository statoUtenteRepository;

    @Override
    public Page<StatoUtente> findAll(Integer page, Integer limit) {

        log.debug("Begin findAll()... StatoUtenteServiceImpl");

        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.ASC);

        Page<StatoUtente> pageStatiUtenti = statoUtenteRepository.findAll(pageable);

        log.debug("End findAll()... StatoUtenteServiceImpl");
        return pageStatiUtenti;
    }

    @Override
    public StatoUtente getById(Integer id) {

        log.debug("Begin findById()... StatoUtenteServiceImpl");

        Optional<StatoUtente> optionalStatoUtente = statoUtenteRepository.findById(id);
        StatoUtente statoUtente = null;

        if (optionalStatoUtente.isPresent())
            statoUtente = optionalStatoUtente.get();

        log.debug("End findById()... StatoUtenteServiceImpl");
        return statoUtente;
    }

}
