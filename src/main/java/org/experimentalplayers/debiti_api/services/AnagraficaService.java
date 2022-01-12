package org.experimentalplayers.debiti_api.services;


import org.experimentalplayers.debiti_api.exceptions.UserException;
import org.experimentalplayers.debiti_api.models.Anagrafica;
import org.springframework.data.domain.Page;

public interface AnagraficaService {

    Page<Anagrafica> findAll(Integer page, Integer limit);

    Anagrafica getUserByUsername(String username);

    Anagrafica getAuth(String username, String password) throws UserException;

    Anagrafica getUserById(Integer id) throws UserException;

    void updatePassword(Integer idUser, String password) throws UserException;
}
