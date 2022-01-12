package org.experimentalplayers.debiti_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.experimentalplayers.debiti_api.models.StatoUtente;

@Repository
public interface StatoUtenteRepository extends JpaRepository<StatoUtente,Integer> {


}
