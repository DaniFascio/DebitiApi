package org.experimental_players.debitiApi.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.experimental_players.debitiApi.configs.Status;
import org.experimental_players.debitiApi.exceptions.UserException;
import org.experimental_players.debitiApi.models.Anagrafica;
import org.experimental_players.debitiApi.repositories.AnagraficaRepository;
import org.experimental_players.debitiApi.services.StatoUtenteService;
import org.experimental_players.debitiApi.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.experimental_players.debitiApi.services.AnagraficaService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class AnagraficaServiceImpl implements AnagraficaService {

    @Autowired
    AnagraficaRepository anagraficaRepository;

    @Autowired
    StatoUtenteService statoUtenteService;

    @Override
    public Page<Anagrafica> findAll(Integer page, Integer limit) {

        log.debug("Begin findAll()... AnagraficaServiceImpl");


        Pageable pageable = new PageUtil(limit,page);

        List<Anagrafica> listAnags = anagraficaRepository.findAll();
        Page<Anagrafica> pageAnags = new PageImpl<>(listAnags, pageable, listAnags.size());

        log.debug("End findAll()... AnagraficaServiceImpl");
        return pageAnags;
    }

    @Override
    public Anagrafica getUserByUsername(String username) {

        log.debug("Begin findAll()... AnagraficaServiceImpl");

        Optional<Anagrafica> optionalAnagrafica = anagraficaRepository.findAnagraficaByUsername(username);
        Anagrafica anagrafica = null;

        if (optionalAnagrafica.isPresent())
            anagrafica = optionalAnagrafica.get();

        log.debug("End findAll()... AnagraficaServiceImpl");
        return anagrafica;
    }

    @Override
    @Transactional(readOnly = true)
    public Anagrafica getAuth(String username, String password) throws UserException {
        log.debug("BEGIN metodo ricerca USER da email e password per AUTENTICAZIONE...");
        Anagrafica user;
        try {
            log.debug("...preparazione della ricerca...");
            Optional<Anagrafica> optUser = anagraficaRepository.findAnagraficaByUsernameAndPassword(username, password);

            if (optUser.isPresent())
                user = optUser.get();

            else {
                optUser = anagraficaRepository.findAnagraficaByUsername(username);
                if (optUser.isPresent()) {
                    throw new UserException(Status.ERR_AUTHORIZATION_PASSWORD.getKey());
                } else
                    throw new UserException(Status.ERR_CREDENZIALI.getKey());
            }

        } catch (Exception e) {
            log.error("Exception occurs{}", e);
            throw new UserException(Status.ERR_GENERICO.getKey());
        }

        return user;
    }


    @Override
    @Transactional(readOnly = true)
    public Anagrafica getUserById(Integer id) throws UserException {
        log.debug("BEGIN metodo di ricerca User con id [{}]...", id);
        Anagrafica user;
        try {
            log.debug("...preparazione della ricerca dell'utente...");
            user = anagraficaRepository.findById(id).get();
            log.debug("...ricerca conclusa...");
        } catch (NoSuchElementException e) {
            log.error("Exception occurs{}", e);
            throw new UserException(Status.WARN_NO_SUCH_ELEMENT.getKey());
        } catch (Exception e) {
            log.error("Exception occurs{}", e);
            throw new UserException(Status.ERR_GENERICO.getKey());
        }
        log.debug("...END metodo di ricerca User per id");
        return user;
    }


    @Override
    @Transactional(rollbackFor = UserException.class)
    public void updatePassword(Integer idUser, String password) throws UserException {
        log.debug("BEGIN metodo di update della password per l'utente con id [{}]", idUser);
        Anagrafica user = getUserById(idUser);
        if (user == null)
            throw new UserException(Status.ERR_GENERICO.getKey());
        user.setPassword(password);
        try {
            log.debug("...preparazione al salvataggio della nuova password...");
            updateUser(user);
            log.debug("...salvataggio concluso...");
        } catch (DataIntegrityViolationException e) {
            log.error("DataIntegrityViolationException Occurs{}", e);
            throw new UserException(Status.ERR_DATA_INTEGRITY_VIOLATION.getKey());
        } catch (Exception e) {
            log.error("Exception occurs{}", e);
            throw new UserException(Status.ERR_GENERICO.getKey());
        }
        log.debug("...END metodo di update della password per l'utente con id [{}]", idUser);

    }


    @Transactional(rollbackFor = UserException.class)
    public void updateUser(Anagrafica anagrafica) throws UserException {
        log.debug("BEGIN metodo di update della password per l'utente con id [{}]", anagrafica.getId());
        anagraficaRepository.delete(anagrafica);
        anagraficaRepository.save(anagrafica);

        log.debug("...END metodo di update della password per l'utente con id [{}]", anagrafica.getId());

    }
}
