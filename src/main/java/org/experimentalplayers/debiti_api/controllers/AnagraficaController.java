package org.experimentalplayers.debiti_api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.experimentalplayers.debiti_api.models.Anagrafica;
import org.experimentalplayers.debiti_api.configs.ApiMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.experimentalplayers.debiti_api.services.AnagraficaService;

@Slf4j
@RestController
@RequestMapping(ApiMappings.MAP_ANAGRAFICA)
public class AnagraficaController extends BaseController{

    @Autowired
    AnagraficaService anagraficaService;

    @GetMapping(ApiMappings.FIND_ALL)
    public HttpEntity<?> findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit){


        log.debug("Begin findAll()...");
        Page<Anagrafica> anagraficaPage = anagraficaService.findAll(page,limit);

        log.info(anagraficaPage.toString());

        log.debug("End findAll()...");
        return new HttpEntity<>(anagraficaPage);

    }


}
