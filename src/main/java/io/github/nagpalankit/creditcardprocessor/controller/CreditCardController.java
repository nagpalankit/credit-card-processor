package io.github.nagpalankit.creditcardprocessor.controller;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import io.github.nagpalankit.creditcardprocessor.model.CreditCardDraft;
import io.github.nagpalankit.creditcardprocessor.repository.CreditCardRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "cards", description = "Credit card management operations")
@RestController
public class CreditCardController {
    private final static String CREDIT_CARD_EXAMPLE_OBJECT = "{ \"id\": 1, \"userName\": \"John Doe\", \"cardNumber\": \"4417123456789113\", \"cardLimit\": 5000, \"remainingBalance\": 0 }";
    private final static String CREDIT_CARD_DRAFT_EXAMPLE_OBJECT = "{ \"userName\": \"John Doe\", \"cardNumber\": \"4417123456789113\", \"cardLimit\": 5000 }";

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Operation(summary = "Gets all saved cards")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cards retrieved successfully",
                            content = @Content(examples = @ExampleObject(value = CREDIT_CARD_EXAMPLE_OBJECT))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error, try again later",
                            content = @Content(schema = @Schema())
                    ),
            }
    )
    @GetMapping(value = "/cards")
    public List<CreditCard> getAllCards() {
        return creditCardRepository.findAll();
    }

    @Operation(summary = "Add a new card")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Card created successfully",
                            content = @Content(examples = @ExampleObject(value = CREDIT_CARD_EXAMPLE_OBJECT))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid card details provided"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error, try again later",
                            content = @Content(schema = @Schema())
                    ),
            }
    )
    @PostMapping(value = "/cards")
    @ResponseStatus(CREATED)
    @ResponseBody
    public ResponseEntity<CreditCard> addCard(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = CREDIT_CARD_DRAFT_EXAMPLE_OBJECT)))
            @Valid @RequestBody CreditCardDraft creditCardDraft
    ) {
        CreditCard newCreditCard = new CreditCard(
                creditCardDraft.userName(),
                creditCardDraft.cardNumber(),
                creditCardDraft.cardLimit(),
                0L
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
