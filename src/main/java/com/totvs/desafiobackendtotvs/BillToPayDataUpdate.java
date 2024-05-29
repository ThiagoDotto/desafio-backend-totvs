package com.totvs.desafiobackendtotvs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillToPayDataUpdate(LocalDate dataVencimento,
                                  LocalDate dataPagamento,
                                  BigDecimal valor,
                                  String descricao,
                                  String situacao) {
}
