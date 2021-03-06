package org.experimentalplayers.debiti_api.controllers;


import lombok.extern.log4j.Log4j2;
import org.experimentalplayers.debiti_api.configs.ApiMappings;
import org.experimentalplayers.debiti_api.configs.EnumStatoUtente;
import org.experimentalplayers.debiti_api.configs.Status;
import org.experimentalplayers.debiti_api.models.Anagrafica;
import org.experimentalplayers.debiti_api.repositories.AnagraficaRepository;
import org.experimentalplayers.debiti_api.requests.LoginRequest;
import org.experimentalplayers.debiti_api.services.AnagraficaService;
import org.experimentalplayers.debiti_api.services.StatoUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiMappings.MAP_HOME)
@Log4j2
public class UserControllerLogin extends BaseController{

    @Autowired
    private AnagraficaService anagraficaService;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private StatoUtenteService statoUtenteService;

    @Autowired
    private AnagraficaRepository anagraficaRepository;


    @PostMapping(ApiMappings.LOGIN)
    public HttpEntity<?> login(@RequestBody LoginRequest authenticationRequest) throws Exception {

        HttpEntity<?> httpEntity;

        log.debug("BEGIN Login Utente.");

        Anagrafica utente = anagraficaService.getUserByUsername(authenticationRequest.getUsername());
        if (utente != null) {
            if (utente.getStatoUtente().getId().compareTo(EnumStatoUtente.BLOCCATO.getId()) == 0) {
                utente.setStatoUtente(statoUtenteService.getById(EnumStatoUtente.ATTIVO.getId()));

                anagraficaRepository.save(utente);
            }
        }

        httpEntity = new HttpEntity<>((authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword())));
        return httpEntity;
    }
/*
    @PostMapping(ApiMappings.LOGOUT)
    public HttpEntity<?> logout(HttpServletRequest request) throws UserException {
        HttpEntity<?> httpEntity;

        String jwtToken;
        try {
            jwtToken = jwtTokenUtil.getToken(request);
        } catch (TokenStreamException e) {
            log.error("Errore nell'acquisire il token", e);
            httpEntity = new HttpEntity<>((Status.WARN_VALIDAZIONE));
            return httpEntity;
        }

        log.info("*********TENTATIVO DI LOGOUT PER IL TOKEN " + jwtToken
                + "**********");
        if (accessiService.cancellaToken(jwtToken)) {
            httpEntity = new HttpEntity<>((EnumStatus.OK));
            log.info("Hai effettuato correttamente il logOut");
            return httpEntity;
        }

        httpEntity = new HttpEntity<>((Status.WARN_AGGIORNAMENTO));
        log.warn("Non ?? stato possibile cancellare il token");
        return httpEntity;

    }
*/

    private Anagrafica authenticate(String username, String password) throws Exception {

        Anagrafica anagrafica;

        try {
            log.debug("ESEGUO IL TENTATIVO DI AUTENTICAZIONE ...");
            anagrafica = anagraficaService.getAuth(username, password);
        } catch (Exception e) {
            log.warn("UTENTE DISABILITATO");
            throw new Exception(Status.ERR_GENERICO.getKey());
        }
        return anagrafica;
    }
}

