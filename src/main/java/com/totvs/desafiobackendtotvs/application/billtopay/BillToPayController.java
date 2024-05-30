package com.totvs.desafiobackendtotvs.application.billtopay;

import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPayData;
import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPayDataUpdate;
import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPayList;
import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPaySituationUpdate;
import com.totvs.desafiobackendtotvs.domain.BillToPay;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bills")
public class BillToPayController {

    @Autowired
    BillToPayService billToPayService;
    @Autowired
    ImportBillsToPayService importBillsToPayService;


    @PostMapping
    public ResponseEntity createBillsToPay(@RequestBody @Valid BillToPayData dataBillToPay) {
        billToPayService.newBill(new BillToPay(dataBillToPay));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillToPay> getBillsToPayById(@PathVariable Integer id) {
        Optional<BillToPay> bill = billToPayService.findById(id);
        return bill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity<Page<BillToPayList>> getBillsToPayByFilter(@RequestParam LocalDate dataVencimento,
                                                                     @RequestParam String descricao, Pageable pageable) {
        return ResponseEntity.ok(billToPayService.findByFilters(dataVencimento, descricao, pageable).map(BillToPayList::new));
    }

    @GetMapping("/total-paid")
    public ResponseEntity<BigDecimal> getTotalPaid(@RequestParam("dataInicio") LocalDate startDate, @RequestParam("dataFim") LocalDate endDate) {
        BigDecimal totalPaid = billToPayService.getTotalPaid(startDate, endDate);
        return ResponseEntity.ok(totalPaid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillToPay> updateBill(@PathVariable("id") Integer id, @RequestBody BillToPayDataUpdate billToPayDataUpdate) {
        billToPayService.update(id, billToPayDataUpdate);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/situacao")
    public ResponseEntity<BillToPay> updateBill(@PathVariable("id") Integer id, @RequestBody BillToPaySituationUpdate billToPayDataUpdate) {
        billToPayService.updateSituation(id, billToPayDataUpdate);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/import")
    public ResponseEntity<String> importContas(@RequestParam("file") MultipartFile file) {
        try {
            importBillsToPayService.importCSV(file);
            return ResponseEntity.ok().build();
        } catch (FileUploadException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
