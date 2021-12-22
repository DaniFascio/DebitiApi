package org.experimentaldevelopers.debitiApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.experimentaldevelopers.debitiApi.models.RuoloDiscord;

@Repository
public interface RuoloDiscordRepository extends JpaRepository<RuoloDiscord,Integer> {


}
