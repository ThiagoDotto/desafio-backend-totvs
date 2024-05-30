package com.totvs.desafiobackendtotvs.infrastructure;


import com.totvs.desafiobackendtotvs.domain.BillToPay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface BillToPayRepository extends JpaRepository<BillToPay, Integer> {

    Page<BillToPay> findByDataVencimentoAndDescricaoContaining(LocalDate dataVencimento, String descricao, Pageable pageable);

    @Query("SELECT SUM(b.valor) FROM BillToPay b WHERE b.dataPagamento BETWEEN :startDate AND :endDate")
    BigDecimal sumTotalPaidBetweenDates(LocalDate startDate, LocalDate endDate);
}
