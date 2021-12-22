package org.experimental_players.debitiApi.services;


import org.experimental_players.debitiApi.requests.SearchDebitiRequest;
import org.experimental_players.debitiApi.responses.SearchDebitiResponse;
import org.springframework.data.domain.Page;

public interface DebitoService {

    Page<SearchDebitiResponse> findAll(SearchDebitiRequest searchDebitiRequest);


}
