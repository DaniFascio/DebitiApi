package org.experimentaldevelopers.debitiApi.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.experimentaldevelopers.debitiApi.repositories.StoricoDebitoRepository;
import org.experimentaldevelopers.debitiApi.requests.SearchDebitiRequest;
import org.experimentaldevelopers.debitiApi.responses.SearchDebitiResponse;
import org.experimentaldevelopers.debitiApi.services.DebitoService;
import org.experimentaldevelopers.debitiApi.utils.DateUtil;
import org.experimentaldevelopers.debitiApi.utils.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Slf4j
@Service
public class DebitoServiceImpl implements DebitoService {

    @Autowired
    StoricoDebitoRepository storicoDebitoRepository;

    @Override
    public Page<SearchDebitiResponse> findAll(SearchDebitiRequest filter) {

        log.debug("Begin findAll()... DebitoServiceImpl");

        PaginationRequest pagination = filter.getPagination();

        Pageable pageable = PageRequest.of(pagination.getOffset(),10, Sort.Direction.ASC, pagination.getOrderBy());

        log.info("request: "+filter);

        filter.setNominativoDebitore(filter.getNominativoDebitore().toUpperCase(Locale.ROOT).replaceAll("\\s+","").replace("","%"));
        filter.setNominativoCreditore(filter.getNominativoCreditore().toUpperCase(Locale.ROOT).replaceAll("\\s+","").replace("","%"));

        if (filter.getDataCreazioneA()!=null)
            filter.setDataCreazioneA(DateUtil.setEndOfDay(filter.getDataCreazioneA()));

        if (filter.getDataSaldatoA()!=null)
            filter.setDataSaldatoA(DateUtil.setEndOfDay(filter.getDataSaldatoA()));

        Page<SearchDebitiResponse> pageDebts = storicoDebitoRepository.findAllByFilter(filter.getNominativoDebitore(),filter.getNominativoCreditore(),
                                            filter.getDataCreazioneDa(), filter.getDataCreazioneA(),filter.getDataSaldatoDa(), filter.getDataSaldatoA(),
                                            filter.getDebitoDa(), filter.getDebitoA(), filter.getSaldato(), pageable);

        log.debug("End findAll()... DebitoServiceImpl");
        return pageDebts;
    }

}