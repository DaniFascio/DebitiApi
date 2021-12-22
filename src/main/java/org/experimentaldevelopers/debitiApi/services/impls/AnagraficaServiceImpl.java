package org.experimentaldevelopers.debitiApi.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.experimentaldevelopers.debitiApi.models.Anagrafica;
import org.experimentaldevelopers.debitiApi.repositories.AnagraficaRepository;
import org.experimentaldevelopers.debitiApi.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.experimentaldevelopers.debitiApi.services.AnagraficaService;

import java.util.List;

@Slf4j
@Service
public class AnagraficaServiceImpl implements AnagraficaService {

    @Autowired
    AnagraficaRepository anagraficaRepository;

    @Override
    public Page<Anagrafica> findAll(Integer page, Integer limit) {

        log.debug("Begin findAll()... AnagraficaServiceImpl");


        Pageable pageable = new PageUtil(limit,page);

        List<Anagrafica> listAnags = anagraficaRepository.findAll();
        Page<Anagrafica> pageAnags = new PageImpl<>(listAnags, pageable, listAnags.size());

        log.debug("End findAll()... AnagraficaServiceImpl");
        return pageAnags;
    }

}
