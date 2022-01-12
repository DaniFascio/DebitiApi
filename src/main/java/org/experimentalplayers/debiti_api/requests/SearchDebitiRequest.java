package org.experimentalplayers.debiti_api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.experimentalplayers.debiti_api.utils.PaginationRequest;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDebitiRequest {

    private PaginationRequest pagination;
    private String nominativoDebitore;
    private String nominativoCreditore;
    private Date dataCreazioneDa;
    private Date dataCreazioneA;
    private Date dataSaldatoDa;
    private Date dataSaldatoA;
    private Boolean saldato = false;
    private Boolean valido = true;
    private Double debitoDa;
    private Double debitoA;


}
