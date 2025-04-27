package io.github.nagpalankit.creditcardprocessor.controller;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import io.github.nagpalankit.creditcardprocessor.model.CreditCardDraft;
import io.github.nagpalankit.creditcardprocessor.repository.CreditCardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping(value = "/cards")
    @ResponseStatus(CREATED)
    @ResponseBody
    public ResponseEntity<CreditCard> addCard(@Valid @RequestBody CreditCardDraft creditCardDraft) {
        CreditCard newCreditCard = new CreditCard(
                creditCardDraft.userName(),
                creditCardDraft.cardNumber(),
                creditCardDraft.cardLimit(),
                0.00
        );

        CreditCard savedCard = creditCardRepository.save(newCreditCard);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCard.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity
                .status(CREATED)
                .headers(headers)
                .body(savedCard);
    }
}
