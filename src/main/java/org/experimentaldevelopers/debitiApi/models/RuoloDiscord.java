package org.experimentaldevelopers.debitiApi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ruoli_discord")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuoloDiscord {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "descrizione")
    private String descrizione;


}
