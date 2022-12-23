package thelameres.balance.server.impl.configurations.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "thelameres.balance.server")
public class BalanceServerConfigurationProperties {

    private GeneratingProperties generating = new GeneratingProperties();
    private Long quantity = 1L;

    @Data
    public static class GeneratingProperties {
        private Boolean enabled = false;
    }
}
