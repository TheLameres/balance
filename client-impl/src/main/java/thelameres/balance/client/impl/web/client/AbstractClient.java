package thelameres.balance.client.impl.web.client;

import org.springframework.web.bind.annotation.GetMapping;

public interface AbstractClient {
    @GetMapping("/actuator/health")
    ActuatorHealthResponse health();
}
