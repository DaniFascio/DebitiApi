package org.experimentaldevelopers.debitiApi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.experimentaldevelopers.debitiApi.configs.ApiMappings;
import org.experimentaldevelopers.debitiApi.requests.SearchDebitiRequest;
import org.experimentaldevelopers.debitiApi.responses.SearchDebitiResponse;
import org.experimentaldevelopers.debitiApi.services.DebitoService;

@Slf4j
@RestController
@RequestMapping(ApiMappings.MAP_DEBITO)
public class DebitoController {

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

}
