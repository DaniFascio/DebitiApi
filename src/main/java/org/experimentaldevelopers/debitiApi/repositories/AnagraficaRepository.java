package org.experimentaldevelopers.debitiApi.repositories;

import org.experimentaldevelopers.debitiApi.models.Anagrafica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagraficaRepository extends JpaRepository<Anagrafica,Integer> {


}
