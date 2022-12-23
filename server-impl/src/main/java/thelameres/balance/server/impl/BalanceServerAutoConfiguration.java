package thelameres.balance.server.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import thelameres.balance.server.impl.configurations.properties.BalanceServerConfigurationProperties;
import thelameres.balance.server.impl.data.entities.Balance;
import thelameres.balance.server.impl.data.repositories.BalanceRepository;

import java.util.stream.LongStream;

@Slf4j
@AutoConfiguration(after = {
        JpaRepositoriesAutoConfiguration.class,
        WebMvcAutoConfiguration.class
})
@EnableJpaRepositories(basePackages = "thelameres.balance.server.impl")
@EntityScan(basePackages = "thelameres.balance.server.impl.data.entities")
@ComponentScans(
        @ComponentScan(basePackages = "thelameres.balance.server.impl")
)
@EnableConfigurationProperties({BalanceServerConfigurationProperties.class})
public class BalanceServerAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "thelameres.balance.server.generating", name = "enabled", havingValue = "true")
    public ApplicationRunner applicationRunner(BalanceRepository balanceRepository,
                                               BalanceServerConfigurationProperties balanceServerConfigurationProperties) {
        return args -> LongStream.range(1, balanceServerConfigurationProperties.getQuantity())
                .parallel()
                .mapToObj(value -> {
                    Balance balance = new Balance();
                    balance.setId(value);
                    balance.setAmount(0L);
                    return balance;
                })
                .forEach(balanceRepository::save);
    }
}
