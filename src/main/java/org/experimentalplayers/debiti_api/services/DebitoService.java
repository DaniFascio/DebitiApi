package org.experimentalplayers.debiti_api.services;


import org.experimentalplayers.debiti_api.models.StoricoDebito;
import org.experimentalplayers.debiti_api.requests.NewDebitoRequest;
import org.experimentalplayers.debiti_api.requests.SearchDebitiRequest;
import org.experimentalplayers.debiti_api.requests.UpdateDebitoRequest;
import org.experimentalplayers.debiti_api.responses.SearchDebitiResponse;
import org.springframework.data.domain.Page;

public interface DebitoService {

    Page<SearchDebitiResponse> findAll(SearchDebitiRequest searchDebitiRequest);

    Double totaleDebtUser(Integer idUser);

    StoricoDebito insert(NewDebitoRequest newDebitoRequest) throws Exception;

    StoricoDebito update(UpdateDebitoRequest updateDebitoRequest);

    StoricoDebito salda(Integer idDebito);

    StoricoDebito delete(Integer idDebito);


}
