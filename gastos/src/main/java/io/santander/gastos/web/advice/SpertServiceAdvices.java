package io.santander.gastos.web.advice;

import io.santander.gastos.enumerators.ErrorMessages;
import io.santander.gastos.exceptions.NonexistentCardException;
import io.santander.gastos.exceptions.MissingCardException;
import io.santander.gastos.vo.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

import static java.time.Instant.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
@Log4j2
public class SpertServiceAdvices {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingFieldException(final MissingServletRequestParameterException e) {
        log.error(ErrorMessages.MISSING_PARAMETER.getErrorMessage(e.getParameterName()));
        return status(BAD_REQUEST)
                .body(this.constructErrorResponse(BAD_REQUEST, ErrorMessages.MISSING_PARAMETER.getErrorMessage(e.getParameterName())));
    }

    @ExceptionHandler(MissingCardException.class)
    public ResponseEntity<ErrorResponse> handleMissingCardException(final MissingCardException e) {
        log.error(ErrorMessages.MISSING_USER_CARD.getErrorMessage(e.getMessage()));
        return status(NOT_FOUND)
                .body(this.constructErrorResponse(NOT_FOUND, ErrorMessages.MISSING_USER_CARD.getErrorMessage(e.getMessage())));
    }

    @ExceptionHandler(NonexistentCardException.class)
    public ResponseEntity<ErrorResponse> handleInexistentCardException(final NonexistentCardException e) {
        log.error(ErrorMessages.NONEXISTENT_CARD.getErrorMessage(e.getMessage()));
        return status(NOT_FOUND)
                .body(this.constructErrorResponse(NOT_FOUND, ErrorMessages.NONEXISTENT_CARD.getErrorMessage(e.getMessage())));
    }

    private ErrorResponse constructErrorResponse(final HttpStatus httpStatus, final String... messages) {
        return ErrorResponse.builder()
                .timestamp(now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .messages(Arrays.asList(messages))
                .build();
    }
}
