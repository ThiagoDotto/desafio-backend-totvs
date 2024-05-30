package com.totvs.desafiobackendtotvs.application.billtopay.dto;

import com.totvs.desafiobackendtotvs.domain.Situation;
import com.totvs.desafiobackendtotvs.domain.BillToPay;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillToPayList(Integer id, LocalDate dataVencimento,
                            LocalDate dataPagamento, BigDecimal valor,
                            String descricao,
                            Situation situacao) {

    public BillToPayList(BillToPay billToPay) {
        this(billToPay.getId(), billToPay.getDataVencimento(), billToPay.getDataPagamento(), billToPay.getValor(), billToPay.getDescricao(), billToPay.getSituation());
    }
}
