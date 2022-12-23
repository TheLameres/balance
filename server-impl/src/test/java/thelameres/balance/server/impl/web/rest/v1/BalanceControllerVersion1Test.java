package thelameres.balance.server.impl.web.rest.v1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.impl.BalanceServerAutoConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {
        BalanceServerAutoConfiguration.class,
        JacksonAutoConfiguration.class
},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
@AutoConfigureWebMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BalanceControllerVersion1Test {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public BalanceControllerVersion1Test(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    @Order(1)
    public void getBalances() throws Exception {
        ResponseEntity<BalanceDto[]> response = testRestTemplate.getForEntity("/api/v1/balance", BalanceDto[].class);
        BalanceDto[] body = response.getBody();
        assertNotNull(body);
        assertEquals(body.length, 0);
    }

    @Test
    @Order(2)
    public void newBalance() throws Exception {
        ResponseEntity<BalanceDto> response = testRestTemplate.postForEntity("/api/v1/balance",
                new AmountDto(1L),
                BalanceDto.class);

        BalanceDto body = response.getBody();
        assertNotNull(body);
        assertEquals(body.amount(), 1L);
    }

    @Test
    @Order(3)
    public void getBalance() {
    }

    @Test
    @Order(4)
    public void changeBalance() {
    }

    @Test
    @Order(5)
    public void deleteBalance() {
    }
}