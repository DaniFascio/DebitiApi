package org.experimentalplayers.debiti_api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDebitiResponse {

    private String nominativoDebitore;
    private String nominativoCreditore;
    private Date dataCreazione;
    private Date dataSaldato;
    private Double debito;
    private Boolean saldato;
    private Boolean valido;

}
