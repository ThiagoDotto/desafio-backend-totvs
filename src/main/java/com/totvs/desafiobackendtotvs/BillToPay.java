package com.totvs.desafiobackendtotvs;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillToPay {

    BillToPayRepository billToPayRepository;

    public BillToPay(BillToPayRepository billToPayRepository) {
        this.billToPayRepository = billToPayRepository;
    }

    public void newBill(Bill bill) {
        Optional<Bill> billExisting = billToPayRepository.findById(bill.getId());
        if (billExisting.isPresent()) {
            throw new IllegalArgumentException();
        }
        billToPayRepository.save(bill);
    }
}
