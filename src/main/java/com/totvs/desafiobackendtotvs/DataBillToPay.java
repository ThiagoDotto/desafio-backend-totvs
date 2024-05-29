package com.totvs.desafiobackendtotvs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataBillToPay(

        @NotNull(message = "O campo 'dataVencimento' não pode ser vazio")
        LocalDate dataVencimento,

        LocalDate dataPagamento,

        @NotNull(message = "O campo 'valor' não pode ser vazio")
        BigDecimal valor,

        @NotBlank(message = "O campo 'descricao' não pode ser vazio")
        String descricao,

        @NotBlank(message = "O campo 'situacao' não pode ser vazio")
        String situacao) {
}
