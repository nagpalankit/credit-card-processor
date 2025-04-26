package io.github.nagpalankit.creditcardprocessor.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;

public class LuhnCheckValidator implements ConstraintValidator<ValidCreditCardNumber, String> {
    @Override
    public boolean isValid(@NotNull String cardNumber, ConstraintValidatorContext context) {
        String sanitizedCardNumber = cardNumber.replaceAll("[^0-9]", "");
        int sum = 0;
        boolean alternate = false;

        for (int i = sanitizedCardNumber.length() - 1; i >= 0; i--) {
            int digitToEvaluate = Character.getNumericValue(sanitizedCardNumber.charAt(i));

            if (alternate) {
                digitToEvaluate *= 2;
                if (digitToEvaluate > 9) {
                    digitToEvaluate -= 9;
                }
            }
            sum += digitToEvaluate;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }
}
