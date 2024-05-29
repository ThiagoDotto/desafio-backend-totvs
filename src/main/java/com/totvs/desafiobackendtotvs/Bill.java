package com.totvs.desafiobackendtotvs;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "conta")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
