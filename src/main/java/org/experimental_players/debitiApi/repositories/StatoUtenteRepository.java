package org.experimental_players.debitiApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.experimental_players.debitiApi.models.StatoUtente;

@Repository
public interface StatoUtenteRepository extends JpaRepository<StatoUtente,Integer> {


}
