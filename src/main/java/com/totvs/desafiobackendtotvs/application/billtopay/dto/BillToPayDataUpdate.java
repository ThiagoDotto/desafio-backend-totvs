package com.totvs.desafiobackendtotvs.application.billtopay.dto;

import com.totvs.desafiobackendtotvs.domain.Situation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillToPayDataUpdate(LocalDate dataVencimento,
                                  LocalDate dataPagamento,
                                  BigDecimal valor,
                                  String descricao,
                                  Situation situacao) {
}
