package com.totvs.desafiobackendtotvs;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "conta")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "situacao", nullable = false)
    private String situacao;

    public Bill(DataBillToPay dataBillToPay) {
        this.dataVencimento = dataBillToPay.dataVencimento();
        this.dataPagamento = dataBillToPay.dataPagamento();
        this.valor = dataBillToPay.valor();
        this.descricao = dataBillToPay.descricao();
        this.situacao = dataBillToPay.situacao();
    }
}
