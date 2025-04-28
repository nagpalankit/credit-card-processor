package io.github.nagpalankit.creditcardprocessor.model;

import java.util.Collections;
import java.util.List;

public class ApiBadRequestError {
    private List<String> errors;

    public ApiBadRequestError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors == null ? Collections.emptyList() : errors;
    }
}
