import com.totvs.desafiobackendtotvs.Bill;
import com.totvs.desafiobackendtotvs.BillToPay;
import com.totvs.desafiobackendtotvs.BillToPayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest
public class BillToPayTest {

    BillToPay billToPay;
    BillToPayRepository billToPayRepositoryMock;

    @BeforeEach
    public void setup() {

        billToPayRepositoryMock = mock(BillToPayRepository.class);
        billToPay = new BillToPay(billToPayRepositoryMock);
    }

    @Test
    public void shouldCreatNewBillToPay() {
        Bill bill = new Bill();
        bill.setId(anyLong());
        bill.setDataVencimento(LocalDate.now());
        bill.setDataPagamento(LocalDate.now());
        bill.setValor(BigDecimal.TEN);
        bill.setDescricao("Teste de cadastro de conta");
        bill.setSituacao("Paga");

        billToPay.newBill(bill);
        verify(billToPayRepositoryMock, times(1)).save(any(Bill.class));
    }

    @Test
    public void shouldDontCreatNewBillToPayWithExistingId() {

        Bill bill = new Bill();
        bill.setId(1L);

        Bill newBill = new Bill();
        newBill.setId(1L);

        when(billToPayRepositoryMock.findByID(any())).thenReturn(Optional.of(newBill));

        BillToPay billToPay = new BillToPay(billToPayRepositoryMock);
        assertThrows(IllegalArgumentException.class, () -> {
            billToPay.newBill(bill);
        });

        verify(billToPayRepositoryMock, times(1)).findByID(any());
    }

}
