package thelameres.balance.client.local;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import thelameres.balance.api.services.BalanceService;

@SpringBootApplication
@Slf4j
public class ClientLocalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientLocalApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(BalanceService balanceService) {
        return args -> {
            log.info("Balance 1: {}", balanceService.getBalance(1L));

        };
    }
}
