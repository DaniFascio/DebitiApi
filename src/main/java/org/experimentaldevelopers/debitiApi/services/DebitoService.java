package org.experimentaldevelopers.debitiApi.services;


import org.experimentaldevelopers.debitiApi.requests.SearchDebitiRequest;
import org.experimentaldevelopers.debitiApi.responses.SearchDebitiResponse;
import org.springframework.data.domain.Page;

public interface DebitoService {

    Page<SearchDebitiResponse> findAll(SearchDebitiRequest searchDebitiRequest);


}
