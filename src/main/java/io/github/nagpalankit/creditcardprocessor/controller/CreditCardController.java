package io.github.nagpalankit.creditcardprocessor.controller;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import io.github.nagpalankit.creditcardprocessor.model.CreditCardDraft;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CreditCardController {

    @GetMapping("/cards")
    public List<CreditCard> getAllCards() {
        return List.of(new CreditCard(
                "1",
                "John Doe",
                "1234-5678-9012-3456",
                5000.00,
                2000.00
        ));
    }

    @PostMapping(value = "/cards", consumes = "application/json")
    @ResponseStatus(CREATED)
    public void addCard(@Valid @RequestBody CreditCardDraft creditCardDraft) {
        // Logic to validate and save the credit card
    }
}
