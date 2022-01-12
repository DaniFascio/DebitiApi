package org.experimental_players.debitiApi.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDebitoRequest {

    private Integer idDebito;
    private Integer idNominativoDebitore;
    private Integer idNominativoCreditore;
    private Date dataSaldato;
    private Boolean saldato = false;
    private Double debito;


}
