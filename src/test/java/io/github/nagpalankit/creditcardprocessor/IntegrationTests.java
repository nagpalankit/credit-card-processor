package io.github.nagpalankit.creditcardprocessor;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import io.github.nagpalankit.creditcardprocessor.model.CreditCardDraft;
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
        CreditCardDraft newCard = new CreditCardDraft(
                "nagpalankit",
                "4417-1234-5678-9113",
                5000.00
        );
        ResponseEntity<CreditCard> addCardResponse = this.restTemplate.postForEntity(apiUrl, newCard, CreditCard.class);
        assertThat(addCardResponse.getStatusCode().value()).isEqualTo(201);
        assertThat(addCardResponse.getHeaders().getLocation()).isNotNull();
        assertThat(addCardResponse.getBody()).isNotNull();
        assertThat(addCardResponse.getBody().getId()).isNotNull();
        assertThat(addCardResponse.getHeaders().getLocation().toString()).isEqualTo(apiUrl + "/" + addCardResponse.getBody().getId());
        assertThat(addCardResponse.getBody().getUserName()).isEqualTo("nagpalankit");

        // Verify one card is present
        ResponseEntity<CreditCard[]> secondGetCardsResponse = this.restTemplate.getForEntity(apiUrl, CreditCard[].class);
        assertThat(secondGetCardsResponse.getStatusCode().value()).isEqualTo(200);
        assertThat(secondGetCardsResponse.getBody()).isNotEmpty();
        assertThat(secondGetCardsResponse.getBody()).hasSize(1);
        assertThat(secondGetCardsResponse.getBody()[0].getId()).isEqualTo(addCardResponse.getBody().getId());
    }
}
