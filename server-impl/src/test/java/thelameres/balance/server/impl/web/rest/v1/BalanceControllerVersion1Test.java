package thelameres.balance.server.impl.web.rest.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.impl.BalanceServerAutoConfiguration;

@SpringBootTest(classes = {
        BalanceServerAutoConfiguration.class,
        JacksonAutoConfiguration.class
},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
public class BalanceControllerVersion1Test {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public BalanceControllerVersion1Test(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    public void getBalances() throws Exception {
    }

    @Test
    public void newBalance() throws Exception {
    }

    @Test
    public void getBalance() {
    }

    @Test
    public void changeBalance() {
    }

    @Test
    public void deleteBalance() {
    }
}