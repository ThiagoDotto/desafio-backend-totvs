package com.totvs.desafiobackendtotvs.application.billtopay;

import com.totvs.desafiobackendtotvs.domain.BillToPay;
import com.totvs.desafiobackendtotvs.infrastructure.BillToPayRepository;
import com.totvs.desafiobackendtotvs.domain.Situation;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImportBillsToPayService {

    BillToPayRepository billToPayRepository;

    @Autowired
    public ImportBillsToPayService(BillToPayRepository billToPayRepository) {
        this.billToPayRepository = billToPayRepository;
    }

    @Transactional
    public void importCSV(MultipartFile file) throws FileUploadException {
        List<BillToPay> billsToPay = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                List<String> list = Arrays.asList(data);
                if (!list.contains("data_vencimento")) {
                    BillToPay billToPay = new BillToPay();
                    billToPay.setDataVencimento(LocalDate.parse(data[0]));
                    billToPay.setDataPagamento(data[1].isEmpty() ? null : LocalDate.parse(data[1]));
                    billToPay.setValor(new BigDecimal(data[2]));
                    billToPay.setDescricao(data[3]);
                    billToPay.setSituation(Situation.valueOf(data[4]));
                    billsToPay.add(billToPay);
                }
            }
        }catch (Exception e) {
            throw new FileUploadException("Erro ao importar contas");
        }
        billToPayRepository.saveAll(billsToPay);
    }
}
