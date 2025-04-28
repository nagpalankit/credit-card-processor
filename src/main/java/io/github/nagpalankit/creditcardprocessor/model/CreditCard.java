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
    private Long cardLimit;
    private Long remainingBalance;

    public CreditCard(String userName, String cardNumber, Long cardLimit, Long remainingBalance) {
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

    public Long getCardLimit() {
        return cardLimit;
    }

    public Long getRemainingBalance() {
        return remainingBalance;
    }
}
