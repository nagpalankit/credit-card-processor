package io.github.nagpalankit.creditcardprocessor.exception;

import io.github.nagpalankit.creditcardprocessor.model.ApiBadRequestError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApiBadRequestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> errors = Stream.concat(
                exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage),
                exception.getBindingResult().getGlobalErrors().stream().map(ObjectError::getDefaultMessage)
        ).toList();
        ApiBadRequestError apiError = new ApiBadRequestError(errors);

        return handleExceptionInternal(exception, apiError, headers, BAD_REQUEST, request);
    }
}
