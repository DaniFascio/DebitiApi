package org.experimentalplayers.debiti_api.services;


import org.experimentalplayers.debiti_api.models.StatoUtente;
import org.springframework.data.domain.Page;

public interface StatoUtenteService {

    Page<StatoUtente> findAll(Integer page, Integer limit);

    StatoUtente getById(Integer id);

}
