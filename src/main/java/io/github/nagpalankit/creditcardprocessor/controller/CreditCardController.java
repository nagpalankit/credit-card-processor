package io.github.nagpalankit.creditcardprocessor.controller;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import io.github.nagpalankit.creditcardprocessor.model.CreditCardDraft;
import io.github.nagpalankit.creditcardprocessor.repository.CreditCardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CreditCardController {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @GetMapping(value = "/cards")
    public List<CreditCard> getAllCards() {
        return creditCardRepository.findAll();
    }

    @PostMapping(value = "/cards", consumes = "application/json")
    @ResponseStatus(CREATED)
    public void addCard(@Valid @RequestBody CreditCardDraft creditCardDraft) {
        CreditCard newCreditCard = new CreditCard(
                creditCardDraft.userName(),
                creditCardDraft.cardNumber(),
                creditCardDraft.cardLimit(),
                0.00
        );
        creditCardRepository.save(newCreditCard);
    }
}
