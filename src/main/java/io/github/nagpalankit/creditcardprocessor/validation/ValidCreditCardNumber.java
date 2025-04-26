package io.github.nagpalankit.creditcardprocessor.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank(message = "Card number is mandatory.")
@Size(min = 16, max = 19, message = "Card number value must be between 16 to 19 digits.")
@Pattern(
        regexp = "^(\\d{4}-\\d{4}-\\d{4}-\\d{4}|\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}|\\d{16})$",
        message = "Card number must be a in the format XXXX-XXXX-XXXX-XXXX or XXXX XXXX XXXX XXXX or XXXXXXXXXXXXXXXX, where X is a digit."
)
@Documented
@Constraint(validatedBy = {LuhnCheckValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidCreditCardNumber {
    String message() default "Invalid credit card number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
