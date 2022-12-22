package thelameres.balance.client.impl.services;

import org.springframework.stereotype.Service;
import thelameres.balance.api.services.BalanceService;
import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.client.impl.web.client.BalanceControllerClient;

import java.util.Objects;
import java.util.Optional;

@Service
public class BalanceServiceClient implements BalanceService {

    private final BalanceControllerClient balanceControllerClient;

    public BalanceServiceClient(BalanceControllerClient balanceControllerClient) {
        this.balanceControllerClient = balanceControllerClient;
    }

    @Override
    public Optional<Long> getBalance(Long id) {
        return Objects.requireNonNull(balanceControllerClient.getBalance(id).getBody()).getAmount().describeConstable();
    }

    @Override
    public void changeBalance(Long id, Long amount) {
        balanceControllerClient.changeBalance(id, new AmountDto(amount));
    }
}
