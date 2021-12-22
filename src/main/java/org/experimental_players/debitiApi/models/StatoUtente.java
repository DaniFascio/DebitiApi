package org.experimental_players.debitiApi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stati_utente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatoUtente {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "descrizione")
    private String descrizione;

}
