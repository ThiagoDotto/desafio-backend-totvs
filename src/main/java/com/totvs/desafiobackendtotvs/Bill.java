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
            this.situacao = updatedBill.situacao();
        }
    }

    public void updateSituation(BillToPaySituationUpdate billToPaySituationUpdate) {
        if (billToPaySituationUpdate.situacao() != null) {
            this.situacao = billToPaySituationUpdate.situacao();
        }
    }
}
