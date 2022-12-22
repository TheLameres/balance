package thelameres.balance.client.impl.configurations.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

@ConfigurationProperties(prefix = "thelameres.client")
public class ClientProperties {
    private String serverUrl;
}
