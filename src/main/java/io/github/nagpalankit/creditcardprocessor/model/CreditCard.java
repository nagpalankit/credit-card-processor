package io.github.nagpalankit.creditcardprocessor.model;

public record CreditCard(
        String id,
        String userName,
        String cardNumber,
        double cardLimit,
        double remainingBalance
) {
}
