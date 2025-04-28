package io.github.nagpalankit.creditcardprocessor.model;

import io.github.nagpalankit.creditcardprocessor.validation.ValidCreditCardNumber;
import jakarta.validation.constraints.*;

public record CreditCardDraft(
        @NotBlank(message = "User name is mandatory.")
        @Size(min = 3, max = 50, message = "Name must be at least 3 and at most 20 characters long.")
        String userName,

        @NotBlank(message = "Card number is mandatory.")
        @Size(min = 13, max = 19, message = "Card number value must be between 13 to 19 digits.")
        @Pattern(
                regexp = "^(\\d{13,19})$",
                message = "Card number must only be numeric and between 13 to 19 digits."
        )
        @ValidCreditCardNumber
        String cardNumber,

        @NotNull(message = "Card limit is mandatory.")
        @Min(value = 1000, message = "Minimum card limit should be greater than or equal to 1000.")
        @Max(value = 10000, message = "Maximum card limit should be less than or equal to 10000.")
        Long cardLimit
) {
}
