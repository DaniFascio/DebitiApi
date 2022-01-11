package org.experimental_players.debitiApi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.experimental_players.debitiApi.configs.ApiMappings;
import org.experimental_players.debitiApi.requests.SearchDebitiRequest;
import org.experimental_players.debitiApi.responses.SearchDebitiResponse;
import org.experimental_players.debitiApi.services.DebitoService;

@Slf4j
@RestController
@RequestMapping(ApiMappings.MAP_DEBITO)
public class DebitoController extends BaseController{

    @Autowired
    DebitoService debitoService;

    @GetMapping(ApiMappings.FIND_ALL)
    public HttpEntity<?> findAll(@RequestBody SearchDebitiRequest searchDebitiRequest){


        log.debug("Begin findAll()...");
        Page<SearchDebitiResponse> storicoPage = debitoService.findAll(searchDebitiRequest);

        log.info(storicoPage.toString());

        log.debug("End findAll()...");
        return new HttpEntity<>(storicoPage);

    }
//
//    @PostMapping(ApiMappings.INSERT)
//    public HttpEntity<?> insert(@RequestBody SearchDebitiRequest searchDebitiRequest){
//
//
//        log.debug("Begin findAll()...");
//        Page<SearchDebitiResponse> storicoPage = debitoService.insert(searchDebitiRequest);
//
//        log.info(storicoPage.toString());
//
//        log.debug("End findAll()...");
//        return new HttpEntity<>(storicoPage);
//
//    }



}
