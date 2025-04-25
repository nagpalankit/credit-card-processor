package io.github.nagpalankit.creditcardprocessor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreditCardDraft(
        @NotBlank(message = "User name is mandatory.")
        String userName,

        @NotBlank(message = "Card number is mandatory.")
        @Size(min = 16, max = 19, message = "Card number value must be between 16 to 19 digits.")
        String cardNumber,

        @NotNull(message = "Card limit is mandatory.")
        double cardLimit
) {
}
