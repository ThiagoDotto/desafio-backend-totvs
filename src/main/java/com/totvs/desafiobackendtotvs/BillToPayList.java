package com.totvs.desafiobackendtotvs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillToPayList(Integer id, LocalDate dataVencimento,
                            LocalDate dataPagamento, BigDecimal valor,
                            String descricao,
                            String situacao) {

    public BillToPayList(Bill bill) {
        this(bill.getId(), bill.getDataVencimento(), bill.getDataPagamento(), bill.getValor(), bill.getDescricao(), bill.getSituacao());
    }
}
