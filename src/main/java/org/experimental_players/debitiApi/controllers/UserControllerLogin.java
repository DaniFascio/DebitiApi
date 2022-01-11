package org.experimental_players.debitiApi.controllers;


import lombok.extern.log4j.Log4j2;
import org.experimental_players.debitiApi.configs.ApiMappings;
import org.experimental_players.debitiApi.configs.EnumStatoUtente;
import org.experimental_players.debitiApi.configs.EnumStatus;
import org.experimental_players.debitiApi.models.Anagrafica;
import org.experimental_players.debitiApi.repositories.AnagraficaRepository;
import org.experimental_players.debitiApi.requests.LoginRequest;
import org.experimental_players.debitiApi.services.AnagraficaService;
import org.experimental_players.debitiApi.services.StatoUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

        log.info("BEGIN Login Utente.");

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
        log.warn("Non è stato possibile cancellare il token");
        return httpEntity;

    }
*/

    private Anagrafica authenticate(String username, String password) throws Exception {

        Anagrafica anagrafica;

        try {
            log.info("ESEGUO IL TENTATIVO DI AUTENTICAZIONE ...");
            anagrafica = anagraficaService.getAuth(username, password);
        } catch (Exception e) {
            log.warn("UTENTE DISABILITATO");
            throw new Exception(EnumStatus.ERR_GENERICO.getKey());
        }
        return anagrafica;
    }
}

