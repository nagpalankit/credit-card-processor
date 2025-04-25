package io.github.nagpalankit.creditcardprocessor;

import io.github.nagpalankit.creditcardprocessor.controller.CreditCardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IntegrationTests {
    @Autowired
    private CreditCardController creditCardController;

    @Test
    void contextLoads() {
        assertThat(creditCardController).isNotNull();
    }
}
