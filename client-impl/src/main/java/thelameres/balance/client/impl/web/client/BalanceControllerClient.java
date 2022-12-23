package thelameres.balance.client.impl.web.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import thelameres.balance.client.impl.infrastructure.feign.annotations.HandleFeignError;
import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.api.data.dtos.DeleteBalanceDto;
import thelameres.balance.server.api.web.rest.BalanceController;

import java.util.List;

@FeignClient(name = "balanceControllerClient",
        url = "${thelameres.client.server-url}"
)
public interface BalanceControllerClient extends BalanceController, AbstractClient {
    @Override
    @HandleFeignError(BalanceControllerClientExceptionHandler.class)
    ResponseEntity<List<BalanceDto>> getBalances();

    @Override
    @HandleFeignError(BalanceControllerClientExceptionHandler.class)
    ResponseEntity<BalanceDto> getBalance(Long id);

    @Override
    @HandleFeignError(BalanceControllerClientExceptionHandler.class)
    ResponseEntity<BalanceDto> newBalance(AmountDto amountDto);

    @Override
    @HandleFeignError(BalanceControllerClientExceptionHandler.class)
    ResponseEntity<BalanceDto> changeBalance(Long id, AmountDto amount);

    @Override
    @HandleFeignError(BalanceControllerClientExceptionHandler.class)
    ResponseEntity<DeleteBalanceDto> deleteBalance(Long id);
}
