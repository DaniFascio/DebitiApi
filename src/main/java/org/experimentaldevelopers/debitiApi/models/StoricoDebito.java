package org.experimentaldevelopers.debitiApi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "storico_debiti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoricoDebito {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "debito")
    private Double debito;

    @JoinColumn(name = "fk_utente_debitore")
    @ManyToOne
    private Anagrafica utenteDebitore;

    @JoinColumn(name = "fk_utente_creditore")
    @ManyToOne
    private Anagrafica utenteCreditore;

    @Column(name = "saldato")
    private Boolean saldato;

    @Column(name = "data_inserimento")
    private Date dataInserimento;

    @Column(name = "data_saldato")
    private Date dataSaldato;



}
