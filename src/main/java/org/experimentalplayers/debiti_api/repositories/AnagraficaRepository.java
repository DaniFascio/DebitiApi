package org.experimentalplayers.debiti_api.repositories;

import org.experimentalplayers.debiti_api.models.Anagrafica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnagraficaRepository extends JpaRepository<Anagrafica,Integer> {

    Optional<Anagrafica> findAnagraficaByUsername(String username);

    Optional<Anagrafica> findAnagraficaByUsernameAndPassword(String username, String password);

}
