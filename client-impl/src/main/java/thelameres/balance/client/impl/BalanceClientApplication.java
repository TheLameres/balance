package thelameres.balance.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import thelameres.balance.client.impl.configurations.properties.ClientProperties;

@AutoConfiguration
@ComponentScans(
        @ComponentScan(basePackages = "thelameres.balance.client")
)
@EnableFeignClients
@EnableConfigurationProperties({ClientProperties.class})
@Slf4j
public class BalanceClientApplication {
}
