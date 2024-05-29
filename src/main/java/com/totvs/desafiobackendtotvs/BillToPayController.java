package com.totvs.desafiobackendtotvs;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Bill> createConta(@RequestBody @Valid DataBillToPay dataBillToPay) {
        billToPay.newBill(new Bill(dataBillToPay));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getContaById(@PathVariable Integer id) {
        Optional<Bill> bill = billToPay.findById(id);
        return bill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
