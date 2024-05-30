package com.totvs.desafiobackendtotvs.application.billtopay;

import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPayDataUpdate;
import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPaySituationUpdate;
import com.totvs.desafiobackendtotvs.domain.BillToPay;
import com.totvs.desafiobackendtotvs.infrastructure.BillToPayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class BillToPayService {

    BillToPayRepository billToPayRepository;

    @Autowired
    public BillToPayService(BillToPayRepository billToPayRepository) {
        this.billToPayRepository = billToPayRepository;
    }

    public Optional<BillToPay> findById(Integer id) {
        return billToPayRepository.findById(id);
    }

    @Transactional
    public void newBill(BillToPay billToPay) {
        billToPayRepository.save(billToPay);
    }


    public Page<BillToPay> findByFilters(LocalDate dataVencimento, String descricao, Pageable pageable) {
        return billToPayRepository.findByDataVencimentoAndDescricaoContaining(dataVencimento, descricao, pageable);
    }

    public BigDecimal getTotalPaid(LocalDate startDate, LocalDate endDate) {
        return billToPayRepository.sumTotalPaidBetweenDates(startDate, endDate);
    }


    @Transactional
    public void update(Integer id, BillToPayDataUpdate billToPayDataUpdate) {

        Optional<BillToPay> optionalConta = billToPayRepository.findById(id);
        if (optionalConta.isPresent()) {
            BillToPay billToPay = optionalConta.get();
            billToPay.updateData(billToPayDataUpdate);
        }
    }

    @Transactional
    public void updateSituation(Integer id, BillToPaySituationUpdate billToPaySituationUpdate) {
        Optional<BillToPay> optionalConta = billToPayRepository.findById(id);
        if (optionalConta.isPresent()) {
            BillToPay billToPay = optionalConta.get();
            billToPay.updateSituation(billToPaySituationUpdate);
        }
    }

}
