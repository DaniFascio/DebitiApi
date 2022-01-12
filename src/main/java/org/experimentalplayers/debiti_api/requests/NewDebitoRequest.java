package org.experimentalplayers.debiti_api.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDebitoRequest {

    private Integer idNominativoDebitore;
    private Integer idNominativoCreditore;
    private Date dataSaldato;
    private Boolean saldato = false;
    private Double debito;



}
