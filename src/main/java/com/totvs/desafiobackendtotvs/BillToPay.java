package com.totvs.desafiobackendtotvs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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


    public Page<Bill> findByFilters(LocalDate dataVencimento, String descricao, Pageable pageable) {
        return billToPayRepository.findByDataVencimentoAndDescricaoContaining(dataVencimento, descricao, pageable);
    }
}
