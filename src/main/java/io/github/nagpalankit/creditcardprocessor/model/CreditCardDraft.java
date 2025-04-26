package io.github.nagpalankit.creditcardprocessor.model;

import io.github.nagpalankit.creditcardprocessor.validation.ValidCreditCardNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreditCardDraft(
        @NotBlank(message = "User name is mandatory.")
        String userName,

        @ValidCreditCardNumber
        String cardNumber,

        @NotNull(message = "Card limit is mandatory.")
        double cardLimit
) {
}
