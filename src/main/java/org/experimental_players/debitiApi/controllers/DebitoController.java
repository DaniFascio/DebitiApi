package org.experimental_players.debitiApi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.experimental_players.debitiApi.requests.IntegerRequest;
import org.experimental_players.debitiApi.requests.NewDebitoRequest;
import org.experimental_players.debitiApi.requests.UpdateDebitoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.experimental_players.debitiApi.configs.ApiMappings;
import org.experimental_players.debitiApi.requests.SearchDebitiRequest;
import org.experimental_players.debitiApi.services.DebitoService;

@Slf4j
@RestController
@RequestMapping(ApiMappings.MAP_DEBITO)
public class DebitoController extends BaseController {

    @Autowired
    DebitoService debitoService;

    @GetMapping(ApiMappings.FIND_ALL)
    public HttpEntity<?> findAll(@RequestBody SearchDebitiRequest searchDebitiRequest) {

        log.debug("Begin findAll(SearchDebitiRequest)..." + searchDebitiRequest.toString());
        return new HttpEntity<>(debitoService.findAll(searchDebitiRequest));

    }

    @PostMapping(ApiMappings.TOTALE_DEBT_USER)
    public HttpEntity<?> findTotDebtUser(@RequestBody IntegerRequest idUser) {

        log.debug("Begin findTotDebtUser(IntegerRequest)..." + idUser.getId());
        return new HttpEntity<>(debitoService.totaleDebtUser(idUser.getId()));

    }

    @PostMapping(ApiMappings.INSERT)
    public HttpEntity<?> insert(@RequestBody NewDebitoRequest newDebitoRequest) throws Exception {

        log.debug("Begin insert(NewDebitoRequest)..." + newDebitoRequest.toString());
        return new HttpEntity<>(debitoService.insert(newDebitoRequest));

    }

    @PostMapping(ApiMappings.UPDATE)
    public HttpEntity<?> update(@RequestBody UpdateDebitoRequest updateDebitoRequest) {

        log.debug("Begin update(UpdateDebitoRequest)..." + updateDebitoRequest.toString());
        return new HttpEntity<>(debitoService.update(updateDebitoRequest));

    }

    @PostMapping(ApiMappings.SALDATO)
    public HttpEntity<?> salda(@RequestBody IntegerRequest idDebito) {

        log.debug("Begin salda(IntegerRequest)..." + idDebito.getId());
        return new HttpEntity<>(debitoService.salda(idDebito.getId()));

    }

    @PostMapping(ApiMappings.DELETE)
    public HttpEntity<?> delete(@RequestBody IntegerRequest idDebito) {

        log.debug("Begin delete(IntegerRequest)..." + idDebito.getId());
        return new HttpEntity<>(debitoService.delete(idDebito.getId()));

    }

}
