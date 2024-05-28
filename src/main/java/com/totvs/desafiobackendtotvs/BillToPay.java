package com.totvs.desafiobackendtotvs;

import java.util.Optional;

//@Service
public class BillToPay {

    BillToPayRepository billToPayRepository;

    public BillToPay(BillToPayRepository billToPayRepository) {
        this.billToPayRepository = billToPayRepository;
    }

    public void newBill(Bill bill) {
        Optional<Bill> billExisting = billToPayRepository.findByID(bill);
        if (billExisting.isPresent()) {
            throw new IllegalArgumentException();
        }

        billToPayRepository.save(bill);
    }
}
