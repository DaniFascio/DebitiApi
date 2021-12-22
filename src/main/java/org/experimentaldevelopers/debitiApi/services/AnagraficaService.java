package org.experimentaldevelopers.debitiApi.services;


import org.experimentaldevelopers.debitiApi.models.Anagrafica;
import org.springframework.data.domain.Page;

public interface AnagraficaService {

    Page<Anagrafica> findAll(Integer page, Integer limit);

}
