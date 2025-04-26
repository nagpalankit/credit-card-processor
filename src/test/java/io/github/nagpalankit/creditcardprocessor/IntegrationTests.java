package io.github.nagpalankit.creditcardprocessor;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class IntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void oneCardIsAddedSuccessfully() {
        String apiUrl = "http://localhost:" + port + "/cards";

        // Verify no cards are present
        ResponseEntity<CreditCard[]> getCardsResponse = this.restTemplate.getForEntity(apiUrl, CreditCard[].class);
        assertThat(getCardsResponse.getStatusCode().value()).isEqualTo(200);
        assertThat(getCardsResponse.getBody()).isEmpty();

        // Add a card
        CreditCard newCard = new CreditCard(
                "nagpalankit",
                "1234-5678-9012-3456",
                5000.00,
                0.00
        );
        ResponseEntity<Void> addCardResponse = this.restTemplate.postForEntity(apiUrl, newCard, Void.class);
        assertThat(addCardResponse.getStatusCode().value()).isEqualTo(201);

        // Verify one card is present
        ResponseEntity<CreditCard[]> secondGetCardsResponse = this.restTemplate.getForEntity(apiUrl, CreditCard[].class);
        assertThat(secondGetCardsResponse.getStatusCode().value()).isEqualTo(200);
        assertThat(secondGetCardsResponse.getBody()).hasSize(1);
        assertThat(secondGetCardsResponse.getBody()[0].getUserName()).isEqualTo("nagpalankit");
    }
}
