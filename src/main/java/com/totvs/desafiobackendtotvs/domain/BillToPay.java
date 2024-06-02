package com.totvs.desafiobackendtotvs.domain;


import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPayData;
import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPayDataUpdate;
import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPaySituationUpdate;
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
public class BillToPay {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private Situation situation;

    public BillToPay(BillToPayData billToPayData) {
        this.dataVencimento = billToPayData.dataVencimento();
        this.dataPagamento = billToPayData.dataPagamento();
        this.valor = billToPayData.valor();
        this.descricao = billToPayData.descricao();
        this.situation = billToPayData.situacao();
    }

    public BillToPay(String[] data) {
        this.dataVencimento = LocalDate.parse(data[0]);
        this.dataPagamento = data[1].isEmpty() ? null : LocalDate.parse(data[1]);
        this.valor = new BigDecimal(data[2]);
        this.descricao = data[3];
        this.situation = Situation.valueOf(data[4]);
    }

    public void updateData(BillToPayDataUpdate updatedBill) {
        if (updatedBill.dataVencimento() != null) {
            this.dataVencimento = updatedBill.dataVencimento();
        }
        if (updatedBill.dataPagamento() != null) {
            this.dataPagamento = updatedBill.dataPagamento();
        }
        if (updatedBill.valor() != null) {
            this.valor = updatedBill.valor();
        }
        if (updatedBill.descricao() != null) {
            this.descricao = updatedBill.descricao();
        }
        if (updatedBill.situacao() != null) {
            this.situation = updatedBill.situacao();
        }
    }

    public void updateSituation(BillToPaySituationUpdate billToPaySituationUpdate) {
        if (billToPaySituationUpdate.situacao() != null) {
            this.situation = billToPaySituationUpdate.situacao();
        }
    }
}
