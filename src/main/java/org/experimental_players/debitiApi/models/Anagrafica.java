package org.experimental_players.debitiApi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "anagrafica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anagrafica {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @JoinColumn(name = "fk_ruolo_discord", referencedColumnName = "id")
    @ManyToOne
    private RuoloDiscord ruoloDiscord;

    @JoinColumn(name = "fk_stato", referencedColumnName = "id")
    @ManyToOne
    private StatoUtente statoUtente;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
