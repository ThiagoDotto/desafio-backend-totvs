package com.totvs.desafiobackendtotvs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BillToPay {

    BillToPayRepository billToPayRepository;

    public BillToPay(BillToPayRepository billToPayRepository) {
        this.billToPayRepository = billToPayRepository;
    }

    public Optional<Bill> findById(Integer id) {
        return billToPayRepository.findById(id);
    }

    @Transactional
    public void newBill(Bill bill) {
        billToPayRepository.save(bill);
    }
}
