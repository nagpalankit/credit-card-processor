package io.github.nagpalankit.creditcardprocessor.repository;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CreditCardRepositoryTest {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testSaveAndFind() {
        CreditCard creditCard = new CreditCard(
                "nagpalankit",
                "1234-5678-9012-3456",
                5000.00,
                0.00);
        testEntityManager.persist(creditCard);
        testEntityManager.flush();

        List<CreditCard> savedCreditCards = creditCardRepository.findAll();

        assertThat(savedCreditCards).isNotNull();
        assertThat(savedCreditCards).hasSize(1);
        assertThat(savedCreditCards.getFirst().getUserName()).isEqualTo(creditCard.getUserName());
        assertThat(savedCreditCards.getFirst().getCardNumber()).isEqualTo(creditCard.getCardNumber());
        assertThat(savedCreditCards.getFirst().getCardLimit()).isEqualTo(creditCard.getCardLimit());
        assertThat(savedCreditCards.getFirst().getRemainingBalance()).isEqualTo(creditCard.getRemainingBalance());
    }
}
