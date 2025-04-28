package io.github.nagpalankit.creditcardprocessor.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {LuhnCheckValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidCreditCardNumber {
    String message() default "Credit card number is not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
