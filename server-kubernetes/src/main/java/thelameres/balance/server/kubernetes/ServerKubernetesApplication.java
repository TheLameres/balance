package thelameres.balance.server.kubernetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;

@SpringBootApplication(exclude = {
        HazelcastAutoConfiguration.class
})
public class ServerKubernetesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerKubernetesApplication.class, args);
    }
}
