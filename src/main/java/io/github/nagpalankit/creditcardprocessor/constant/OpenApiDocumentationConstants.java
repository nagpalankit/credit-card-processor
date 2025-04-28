package io.github.nagpalankit.creditcardprocessor.constant;

public interface OpenApiDocumentationConstants {
    String API_BAD_REQUEST_ERROR_EXAMPLE = "{\"errors\":[\"Card number must only be numeric and between 13 to 19 digits.\",\"Credit card number is not valid.\"]}";
    String CREDIT_CARD_EXAMPLE_OBJECT = "{ \"id\": 1, \"userName\": \"John Doe\", \"cardNumber\": \"4417123456789113\", \"cardLimit\": 5000, \"remainingBalance\": 0 }";
    String CREDIT_CARD_DRAFT_EXAMPLE_OBJECT = "{ \"userName\": \"John Doe\", \"cardNumber\": \"4417123456789113\", \"cardLimit\": 5000 }";
}
