package thelameres.balance.client.impl.infrastructure.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import thelameres.balance.client.impl.web.client.ActuatorHealthResponse;
import thelameres.balance.client.impl.web.client.BalanceControllerClient;

@Component
public class BalanceServerServerHealthIndicator extends AbstractHealthIndicator {

    private final BalanceControllerClient balanceControllerClient;

    public BalanceServerServerHealthIndicator(BalanceControllerClient balanceControllerClient) {
        this.balanceControllerClient = balanceControllerClient;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        ActuatorHealthResponse health = balanceControllerClient.health();
        switch (health.status()) {
            case "UP" -> builder.up();
            case "DOWN" -> builder.down();
            default -> builder.outOfService();
        }
    }
}
