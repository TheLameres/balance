package thelameres.balance.server.impl.services;

import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.api.data.dtos.DeleteBalanceDto;

import java.util.List;
import java.util.Optional;

public interface BalanceServerInternalService {
    List<BalanceDto> getBalances();
    Optional<BalanceDto> getBalance(Long id);
    BalanceDto saveBalance(BalanceDto balanceDto);
    BalanceDto saveBalance(AmountDto amountDto);
    Optional<BalanceDto> changeBalance(BalanceDto balanceDto);
    Optional<BalanceDto> changeBalance(Long id, AmountDto amountDto);
    DeleteBalanceDto deleteBalance(Long id);
}
