package org.experimental_players.debitiApi.services;


import org.experimental_players.debitiApi.models.Anagrafica;
import org.springframework.data.domain.Page;

public interface AnagraficaService {

    Page<Anagrafica> findAll(Integer page, Integer limit);

}
