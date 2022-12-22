package thelameres.balance.client.impl.web.client;

import org.springframework.http.ResponseEntity;
import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.api.data.dtos.DeleteBalanceDto;

import java.util.List;

public class BalanceControllerClientFallback implements BalanceControllerClient {
    @Override
    public ResponseEntity<List<BalanceDto>> getBalances() {
        throw new BalanceException("Can't Get Balance");
    }

    @Override
    public ResponseEntity<BalanceDto> getBalance(Long id) {
        throw new BalanceException("Can't Get Balance");
    }

    @Override
    public ResponseEntity<BalanceDto> changeBalance(Long id, AmountDto amount) {
        throw new BalanceException("Can't Get Balance");
    }

    @Override
    public ResponseEntity<BalanceDto> newBalance(AmountDto amountDto) {
        throw new BalanceException("Can't Get Balance");
    }

    @Override
    public ResponseEntity<DeleteBalanceDto> deleteBalance(Long id) {
        throw new BalanceException("Can't Get Balance");
    }
}
