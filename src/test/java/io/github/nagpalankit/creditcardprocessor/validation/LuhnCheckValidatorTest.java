package io.github.nagpalankit.creditcardprocessor.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LuhnCheckValidatorTest {
    private final LuhnCheckValidator validator = new LuhnCheckValidator();

    @Test
    void validCardNumbers() {
        assertTrue(validator.isValid("4539 1488 0343 6467", null));
        assertTrue(validator.isValid("6011-1111-1111-1117", null));
    }

    @Test
    void invalidCardNumbers() {
        assertFalse(validator.isValid("1234 5678 9012 3456", null));
        assertFalse(validator.isValid("4539 1488 0343 6468", null));
        assertFalse(validator.isValid("6011-1111-1111-1118", null));
    }
}
