package com.totvs.desafiobackendtotvs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

//@Repository
public class BillToPayRepository {

    List<Bill> listBill;

    public BillToPayRepository() {
        Bill billOne = new Bill();
        billOne.setId(1L);
        billOne.setDataVencimento(LocalDate.now());
        billOne.setDataPagamento(LocalDate.now());
        billOne.setValor(BigDecimal.TEN);
        billOne.setDescricao("Conta existente 1");
        billOne.setSituacao("PAGA");

        Bill billTwo = new Bill();
        billTwo.setId(2L);
        billOne.setDataVencimento(LocalDate.now());
        billOne.setDataPagamento(LocalDate.now());
        billOne.setValor(BigDecimal.TEN);
        billOne.setDescricao("Conta existente 2");
        billOne.setSituacao("Não PAGA");

        this.listBill = List.of(billOne, billTwo);

    }

    public Optional<Bill> findByID(Bill bill) {

        return this.listBill
                .stream()
                .filter(billLocal -> billLocal.getId() == bill.getId())
                .findAny();
    }

    public void save(Bill bill) {
        this.listBill.add(bill);
    }
}
