import com.totvs.desafiobackendtotvs.Bill;
import com.totvs.desafiobackendtotvs.BillToPay;
import com.totvs.desafiobackendtotvs.BillToPayDataUpdate;
import com.totvs.desafiobackendtotvs.BillToPayRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BillToPayTest {


    @Mock
    private BillToPayRepository billToPayRepositoryMock;

    @InjectMocks
    private BillToPay billToPay;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreatNewBillToPay() {
        Bill bill = new Bill();

        bill.setDataVencimento(LocalDate.now());
        bill.setDataPagamento(LocalDate.now());
        bill.setValor(BigDecimal.TEN);
        bill.setDescricao("Teste de cadastro de conta");
        bill.setSituacao("Paga");

        billToPay.newBill(bill);
        verify(billToPayRepositoryMock, times(1)).save(any(Bill.class));
    }

    @Test
    void shouldFindBillToPayById() {
        Bill bill = new Bill();
        bill.setId(1);

        when(billToPayRepositoryMock.findById(1)).thenReturn(Optional.of(bill));

        Optional<Bill> foundBill = billToPay.findById(1);

        assertTrue(foundBill.isPresent());
        assertEquals(1, foundBill.get().getId());
    }

    @Test
    void shouldFindBillToPayByFilters() {
        Bill bill1 = new Bill();
        bill1.setId(1);

        Bill bill2 = new Bill();
        bill2.setId(2);

        Pageable pageable = PageRequest.of(0, 10);
        LocalDate dataVencimento = LocalDate.of(2024, 05, 29);
        String descricao = "Test";

        when(billToPayRepositoryMock.findByDataVencimentoAndDescricaoContaining(dataVencimento, descricao, pageable))
                .thenReturn(new PageImpl<>(Arrays.asList(bill1, bill2)));

        Page<Bill> byFilters = billToPay.findByFilters(dataVencimento, descricao, pageable);
        assertEquals(2, byFilters.getTotalElements());
        assertEquals(1, byFilters.getContent().get(0).getId());
        assertEquals(2, byFilters.getContent().get(1).getId());
    }


    @Test
    void shouldGetTotalPaid() {
        LocalDate startDate = LocalDate.of(2024, 5, 1);
        LocalDate endDate = LocalDate.of(2024, 5, 31);
        when(billToPayRepositoryMock.sumTotalPaidBetweenDates(startDate, endDate)).thenReturn(BigDecimal.valueOf(1000));

        BigDecimal totalPaid = billToPay.getTotalPaid(startDate, endDate);
        assertEquals(BigDecimal.valueOf(1000), totalPaid);
    }


    @Test
    void shouldUpdateBillToPay() {
        Bill existingBill = new Bill();
        existingBill.setId(1);
        existingBill.setDescricao("Old Description");

        BillToPayDataUpdate updatedBill = new BillToPayDataUpdate(LocalDate.now(), LocalDate.now(), BigDecimal.TEN, "New Description", null);

        when(billToPayRepositoryMock.findById(1)).thenReturn(Optional.of(existingBill));
//        when(billToPayRepositoryMock.save(updatedBill)).thenReturn(updatedBill);

        billToPay.update(1, updatedBill);

//        assertNotNull(result);
//        assertEquals("New Description", result.getDescricao());
        verify(billToPayRepositoryMock, times(1)).findById(1);
//        verify(billToPayRepositoryMock, times(1)).save(updatedBill);
    }

}
