package org.experimental_players.debitiApi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ruoli_discord")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuoloDiscord {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descrizione")
    private String descrizione;


}
