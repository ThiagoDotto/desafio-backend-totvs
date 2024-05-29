import com.totvs.desafiobackendtotvs.Bill;
import com.totvs.desafiobackendtotvs.BillToPay;
import com.totvs.desafiobackendtotvs.BillToPayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void testFindById() {
        Bill bill = new Bill();
        bill.setId(1);

        when(billToPayRepositoryMock.findById(1)).thenReturn(Optional.of(bill));

        Optional<Bill> foundBill = billToPay.findById(1);

        assertTrue(foundBill.isPresent());
        assertEquals(1, foundBill.get().getId());
    }
}
