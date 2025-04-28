package io.github.nagpalankit.creditcardprocessor.repository;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CreditCardRepositoryTest {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Test
    void creditCardIsSavedAndRetrieved() {
        CreditCard creditCard = new CreditCard(
                "nagpalankit",
                "1234567890123456",
                5000L,
                0L);
        creditCardRepository.save(creditCard);

        List<CreditCard> savedCreditCards = creditCardRepository.findAll();

        assertThat(savedCreditCards).isNotNull();
        assertThat(savedCreditCards).hasSize(1);
        assertThat(savedCreditCards.getFirst()).isNotNull();
        assertThat(savedCreditCards.getFirst().getId()).isNotNull();
        assertThat(savedCreditCards.getFirst().getUserName()).isEqualTo(creditCard.getUserName());
        assertThat(savedCreditCards.getFirst().getCardNumber()).isEqualTo(creditCard.getCardNumber());
        assertThat(savedCreditCards.getFirst().getCardLimit()).isEqualTo(creditCard.getCardLimit());
        assertThat(savedCreditCards.getFirst().getRemainingBalance()).isEqualTo(creditCard.getRemainingBalance());
    }
}
