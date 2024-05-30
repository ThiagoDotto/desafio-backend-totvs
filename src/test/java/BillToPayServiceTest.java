import com.totvs.desafiobackendtotvs.application.billtopay.BillToPayService;
import com.totvs.desafiobackendtotvs.application.billtopay.dto.BillToPayDataUpdate;
import com.totvs.desafiobackendtotvs.domain.BillToPay;
import com.totvs.desafiobackendtotvs.domain.Situation;
import com.totvs.desafiobackendtotvs.infrastructure.BillToPayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BillToPayServiceTest {


    @Mock
    private BillToPayRepository billToPayRepositoryMock;

    @InjectMocks
    private BillToPayService billToPayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreatNewBillToPay() {
        BillToPay billToPay = new BillToPay();

        billToPay.setDataVencimento(LocalDate.now());
        billToPay.setDataPagamento(LocalDate.now());
        billToPay.setValor(BigDecimal.TEN);
        billToPay.setDescricao("Teste de cadastro de conta");
        billToPay.setSituation(Situation.PAGA);

        billToPayService.newBill(billToPay);
        verify(billToPayRepositoryMock, times(1)).save(any(BillToPay.class));
    }

    @Test
    void shouldFindBillToPayById() {
        BillToPay billToPay = new BillToPay();
        billToPay.setId(1);

        when(billToPayRepositoryMock.findById(1)).thenReturn(Optional.of(billToPay));

        Optional<BillToPay> foundBill = billToPayService.findById(1);

        assertTrue(foundBill.isPresent());
        assertEquals(1, foundBill.get().getId());
    }

    @Test
    void shouldFindBillToPayByFilters() {
        var billToPay1 = new BillToPay();
        billToPay1.setId(1);

        var billToPay2 = new BillToPay();
        billToPay2.setId(2);

        Pageable pageable = PageRequest.of(0, 10);
        LocalDate dataVencimento = LocalDate.of(2024, 05, 29);
        String descricao = "Test";

        when(billToPayRepositoryMock.findByDataVencimentoAndDescricaoContaining(dataVencimento, descricao, pageable))
                .thenReturn(new PageImpl<>(Arrays.asList(billToPay1, billToPay2)));

        Page<BillToPay> byFilters = billToPayService.findByFilters(dataVencimento, descricao, pageable);
        assertEquals(2, byFilters.getTotalElements());
        assertEquals(1, byFilters.getContent().get(0).getId());
        assertEquals(2, byFilters.getContent().get(1).getId());
    }


    @Test
    void shouldGetTotalPaid() {
        LocalDate startDate = LocalDate.of(2024, 5, 1);
        LocalDate endDate = LocalDate.of(2024, 5, 31);
        when(billToPayRepositoryMock.sumTotalPaidBetweenDates(startDate, endDate)).thenReturn(BigDecimal.valueOf(1000));

        BigDecimal totalPaid = billToPayService.getTotalPaid(startDate, endDate);
        assertEquals(BigDecimal.valueOf(1000), totalPaid);
    }


    @Test
    void shouldUpdateBillToPay() {
        BillToPay existingBill = new BillToPay();
        existingBill.setId(1);
        existingBill.setDescricao("Old Description");

        BillToPayDataUpdate updatedBill = new BillToPayDataUpdate(LocalDate.now(), LocalDate.now(), BigDecimal.TEN, "New Description", null);

        when(billToPayRepositoryMock.findById(1)).thenReturn(Optional.of(existingBill));

        billToPayService.update(1, updatedBill);

        verify(billToPayRepositoryMock, times(1)).findById(1);
    }

}
