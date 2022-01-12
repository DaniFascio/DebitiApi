package org.experimental_players.debitiApi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.experimental_players.debitiApi.models.StoricoDebito;
import org.experimental_players.debitiApi.responses.SearchDebitiResponse;

import java.util.Date;

@Repository
public interface StoricoDebitoRepository extends JpaRepository<StoricoDebito,Integer> {



    @Query(value = "select new org.experimental_players.debitiApi.responses.SearchDebitiResponse(CONCAT(a1.nome , ' ' , a1.cognome),CONCAT(a2.nome , ' ' , a2.cognome) , " +
            " sd.dataInserimento, sd.dataSaldato, sd.debito, sd.saldato, sd.valido ) from StoricoDebito sd " +
            " join Anagrafica a1 on a1.id = sd.utenteDebitore.id " +
            " join Anagrafica a2 on a2.id = sd.utenteCreditore.id " +
            " where ((upper(a1.nome) like :debitore and :debitore <> '') or (upper(a1.cognome) like :debitore and :debitore <> '') " +
            " or (concat(upper(a1.nome), ' ', upper(a1.cognome)) like :debitore and :debitore <> '') or (a1.nome is not null  and :debitore = '' ))   " +
            " and ((upper(a2.nome) like :creditore and :creditore <> '') or (upper(a2.cognome) like :creditore and :creditore <> '') " +
            " or (concat(upper(a2.nome), ' ', upper(a2.cognome)) like :debitore and :creditore <> '') or (a2.nome is not null  and :creditore = '' ))  " +
            " and ((sd.debito >= :debitoDa and :debitoDa is not null) or (sd.debito >= 0 and :debitoDa is null)) " +
            " and ((sd.debito <= :debitoA and :debitoA is not null) or (sd.debito >= 0 and :debitoA is null )) " +
            " and ( (coalesce(:dataInsDa, null) is null and coalesce(:dataInsA, null) is null) or ((coalesce(sd.dataInserimento, null) is not null) " +
            " and (((coalesce(:dataInsDa, null) is null and coalesce(:dataInsA, null) is not null) and sd.dataInserimento <= :dataInsA) " +
            " or ((coalesce(:dataInsA, null) is null and coalesce(:dataInsDa, null) is not null) and sd.dataInserimento >= :dataInsDa) " +
            " or (sd.dataInserimento between :dataInsDa and :dataInsA )))) " +
            " and ( (coalesce(:dataSaldatoDa, null) is null and coalesce(:dataSaldatoA, null) is null) or ((coalesce(sd.dataInserimento, null) is not null) " +
            " and (((coalesce(:dataSaldatoDa, null) is null and coalesce(:dataSaldatoA, null) is not null) and sd.dataInserimento <= :dataSaldatoA) " +
            " or ((coalesce(:dataInsA, null) is null and coalesce(:debitoDa, null) is not null) and sd.dataInserimento >= :dataSaldatoDa) " +
            " or (sd.dataSaldato between :dataSaldatoDa and :dataSaldatoA )))) " +
            " and ((sd.saldato = :saldato and :saldato is not null) or (sd.saldato is not null and :saldato is null)) " +
            " and ((sd.valido = :valido and :valido is not null) or (sd.valido is not null and :valido is null)) " +
            " order by sd.dataInserimento "
            ,nativeQuery = false)
    Page<SearchDebitiResponse> findAllByFilter(@Param("debitore") String debitore, @Param("creditore") String creditore, @Param("dataInsDa") Date dataInsDa,
                                               @Param("dataInsA") Date dataInsA, @Param("dataSaldatoDa") Date dataSaldatoDa, @Param("dataSaldatoA") Date dataSaldatoA,
                                               @Param("debitoDa") Double debitoDa, @Param("debitoA") Double debitoA, @Param("saldato") Boolean saldato, @Param("valido") Boolean valido,
                                               Pageable pageable);

    @Query(value = "update storico_debiti set data_saldato = CURRENT_TIMESTAMP, saldato = true  " +
            " where id = :idDebito ",nativeQuery = true)
    Integer salda(@Param("idDebito") Integer idDebito);


    @Query(value = "update storico_debiti " +
            " set debito = :debito, fk_utente_creditore = :creditore, fk_utente_debitore = :debitore, saldato = :saldato,  " +
            "     data_saldato = :dataSaldato, valido = :valido " +
            " where id = :idDebito ",nativeQuery = true)
    Integer update(@Param("idDebito") Integer idDebito, @Param("debito") Double debito, @Param("creditore") Integer creditore,
                @Param("debitore") Integer debitore, @Param("saldato") Boolean saldato, @Param("dataSaldato") Date dataSaldato, @Param("valido") Boolean valido);


    @Query(value = "update storico_debiti set valido = false  " +
            " where id = :idDebito ",nativeQuery = true)
    Integer setValidoFalse(@Param("idDebito") Integer idDebito);

}
