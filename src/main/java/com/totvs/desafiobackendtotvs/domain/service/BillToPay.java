package com.totvs.desafiobackendtotvs.application.service;

import com.totvs.desafiobackendtotvs.domain.model.Bill;
import com.totvs.desafiobackendtotvs.BillToPayDataUpdate;
import com.totvs.desafiobackendtotvs.BillToPayRepository;
import com.totvs.desafiobackendtotvs.BillToPaySituationUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    public BigDecimal getTotalPaid(LocalDate startDate, LocalDate endDate) {
        return billToPayRepository.sumTotalPaidBetweenDates(startDate, endDate);
    }


    @Transactional
    public void update(Integer id, BillToPayDataUpdate billToPayDataUpdate) {

        Optional<Bill> optionalConta = billToPayRepository.findById(id);
        if (optionalConta.isPresent()) {
            Bill bill = optionalConta.get();
            bill.updateData(billToPayDataUpdate);
        }
    }

    @Transactional
    public void updateSituation(Integer id, BillToPaySituationUpdate billToPaySituationUpdate) {
        Optional<Bill> optionalConta = billToPayRepository.findById(id);
        if (optionalConta.isPresent()) {
            Bill bill = optionalConta.get();
            bill.updateSituation(billToPaySituationUpdate);
        }
    }
}
