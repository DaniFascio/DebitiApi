package org.experimental_players.debitiApi.services;


import org.experimental_players.debitiApi.models.StoricoDebito;
import org.experimental_players.debitiApi.requests.NewDebitoRequest;
import org.experimental_players.debitiApi.requests.SearchDebitiRequest;
import org.experimental_players.debitiApi.requests.UpdateDebitoRequest;
import org.experimental_players.debitiApi.responses.SearchDebitiResponse;
import org.springframework.data.domain.Page;

public interface DebitoService {

    Page<SearchDebitiResponse> findAll(SearchDebitiRequest searchDebitiRequest);

    StoricoDebito insert(NewDebitoRequest newDebitoRequest) throws Exception;

    StoricoDebito update(UpdateDebitoRequest updateDebitoRequest);

    StoricoDebito salda(Integer idDebito);


}
