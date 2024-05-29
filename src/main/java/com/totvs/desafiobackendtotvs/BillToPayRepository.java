package com.totvs.desafiobackendtotvs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BillToPayRepository extends JpaRepository<Bill, Integer> {

    Page<Bill> findByDataVencimentoAndDescricaoContaining(LocalDate dataVencimento, String descricao, Pageable pageable);
}
