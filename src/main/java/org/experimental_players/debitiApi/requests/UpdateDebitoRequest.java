package org.experimental_players.debitiApi.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class UpdateDebitoRequest {

    private Integer idDebito;
    private Integer idNominativoDebitore;
    private Integer idNominativoCreditore;
    private Date dataSaldato;
    private Boolean saldato;
    private Double debito;
    private Boolean valido;


    public UpdateDebitoRequest(Integer idDebito, Integer idNominativoDebitore, Integer idNominativoCreditore,
                               Date dataSaldato, Boolean saldato, Double debito, Boolean valido) {

        this.idDebito = idDebito;
        this.idNominativoDebitore = idNominativoDebitore;
        this.idNominativoCreditore = idNominativoCreditore;
        this.dataSaldato = dataSaldato;
        this.saldato = saldato;
        this.debito = debito;
        this.valido = valido;
    }

}
