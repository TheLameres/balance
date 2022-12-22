package thelameres.balance.client.impl.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import thelameres.balance.server.api.web.rest.BalanceController;

@FeignClient(name = "balanceControllerClient",
        url = "${thelameres.client.server-url}",
        fallback = BalanceControllerClientFallback.class
)
public interface BalanceControllerClient extends BalanceController {
}
