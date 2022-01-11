package org.experimental_players.debitiApi.services;


import org.experimental_players.debitiApi.models.StatoUtente;
import org.springframework.data.domain.Page;

public interface StatoUtenteService {

    Page<StatoUtente> findAll(Integer page, Integer limit);

    StatoUtente getById(Integer id);

}
