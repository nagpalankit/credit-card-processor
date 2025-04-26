package io.github.nagpalankit.creditcardprocessor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Entity
public class CreditCard {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String cardNumber;
    private double cardLimit;
    private double remainingBalance;

    public CreditCard(String userName, String cardNumber, double cardLimit, double remainingBalance) {
        this.userName = userName;
        this.cardNumber = cardNumber;
        this.cardLimit = cardLimit;
        this.remainingBalance = remainingBalance;
    }

    public CreditCard() {

    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getCardLimit() {
        return cardLimit;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }
}
