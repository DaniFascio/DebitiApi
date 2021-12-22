package org.experimentaldevelopers.debitiApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.experimentaldevelopers.debitiApi.models.StatoUtente;

@Repository
public interface StatoUtenteRepository extends JpaRepository<StatoUtente,Integer> {


}
