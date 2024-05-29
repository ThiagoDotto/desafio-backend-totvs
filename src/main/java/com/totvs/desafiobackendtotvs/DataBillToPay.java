package com.totvs.desafiobackendtotvs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataBillToPay(LocalDate dataVencimento, LocalDate dataPagamento, BigDecimal valor, String descricao,
                            String situacao) {
}
