package com.totvs.desafiobackendtotvs;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bills")
public class BillToPayController {

    BillToPay billToPay;

    @Autowired
    public BillToPayController(BillToPay billToPay) {
        this.billToPay = billToPay;
    }

    @PostMapping
    public ResponseEntity<Bill> createBillsToPay(@RequestBody @Valid DataBillToPay dataBillToPay) {
        billToPay.newBill(new Bill(dataBillToPay));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillsToPayById(@PathVariable Integer id) {
        Optional<Bill> bill = billToPay.findById(id);
        return bill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<BillToPayList>> getBillsToPayByFilter(@RequestParam LocalDate dataVencimento,
                                                                     @RequestParam String descricao, Pageable pageable) {
        return ResponseEntity.ok(billToPay.findByFilters(dataVencimento, descricao, pageable).map(BillToPayList::new));
    }

    @GetMapping("/total-paid")
    public ResponseEntity<BigDecimal> getTotalPaid(@RequestParam("dataInicio") LocalDate startDate, @RequestParam("dataFim") LocalDate endDate) {
        BigDecimal totalPaid = billToPay.getTotalPaid(startDate, endDate);
        return ResponseEntity.ok(totalPaid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable("id") Integer id, @RequestBody BillToPayDataUpdate billToPayDataUpdate) {
        billToPay.update(id, billToPayDataUpdate);
        return ResponseEntity.ok().build();
    }
}
