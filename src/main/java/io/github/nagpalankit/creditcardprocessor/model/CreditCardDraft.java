package io.github.nagpalankit.creditcardprocessor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreditCardDraft {
    @NotBlank(message = "User name is mandatory.")
    private final String userName;

    @NotBlank(message = "Card number is mandatory.")
    @Size(min = 16, max = 19, message = "Card number value must be between 16 to 19 digits.")
    private final String cardNumber;

    @NotNull(message = "Card limit is mandatory.")
    private final double cardLimit;

    public CreditCardDraft(String userName, String cardNumber, double cardLimit) {
        this.userName = userName;
        this.cardNumber = cardNumber;
        this.cardLimit = cardLimit;
    }
}
