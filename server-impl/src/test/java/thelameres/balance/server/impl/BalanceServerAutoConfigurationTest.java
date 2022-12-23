package thelameres.balance.server.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        HibernateJpaAutoConfiguration.class
})
public class BalanceServerAutoConfigurationTest {
    @Test
    public void contextLoads() {
    }
}