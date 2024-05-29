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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

}
