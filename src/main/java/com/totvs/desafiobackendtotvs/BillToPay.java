package com.totvs.desafiobackendtotvs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillToPay {

    BillToPayRepository billToPayRepository;

    public BillToPay(BillToPayRepository billToPayRepository) {
        this.billToPayRepository = billToPayRepository;
    }

    @Transactional
    public void newBill(Bill bill) {
        billToPayRepository.save(bill);
    }
}
