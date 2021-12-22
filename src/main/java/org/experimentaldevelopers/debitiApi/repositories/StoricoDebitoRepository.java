package org.experimentaldevelopers.debitiApi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.experimentaldevelopers.debitiApi.models.StoricoDebito;
import org.experimentaldevelopers.debitiApi.responses.SearchDebitiResponse;

import java.util.Date;

@Repository
public interface StoricoDebitoRepository extends JpaRepository<StoricoDebito,Integer> {



    @Query(value = "select new org.experimentaldevelopers.debitiApi.responses.SearchDebitiResponse(CONCAT(a.nome , ' ' , a.cognome), CONCAT(a.nome , ' ' , a.cognome),  " +
            " sd.dataInserimento, sd.dataSaldato, sd.debito, sd.saldato ) from StoricoDebito sd " +
            " join Anagrafica a on a.id = sd.utenteDebitore.id " +
            " where ((upper(a.nome) like :debitore and :debitore <> '') or (upper(a.cognome) like :debitore and :debitore <> '') " +
            " or (concat(upper(a.nome), ' ', upper(a.cognome)) like :debitore and :debitore <> '') or (a.nome is not null  and :debitore = '' ))   " +
            " and ((upper(a.nome) like :creditore and :creditore <> '') or (upper(a.cognome) like :creditore and :creditore <> '') " +
            " or (concat(upper(a.nome), ' ', upper(a.cognome)) like :debitore and :creditore <> '') or (a.nome is not null  and :creditore = '' ))  " +
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
            " order by sd.dataInserimento "
            ,nativeQuery = false)
    Page<SearchDebitiResponse> findAllByFilter(@Param("debitore") String debitore, @Param("creditore") String creditore, @Param("dataInsDa") Date dataInsDa,
                                               @Param("dataInsA") Date dataInsA, @Param("dataSaldatoDa") Date dataSaldatoDa, @Param("dataSaldatoA") Date dataSaldatoA,
                                               @Param("debitoDa") Double debitoDa, @Param("debitoA") Double debitoA, @Param("saldato") Boolean saldato, Pageable pageable);

}
