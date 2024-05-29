package com.totvs.desafiobackendtotvs;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contas")
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
}
