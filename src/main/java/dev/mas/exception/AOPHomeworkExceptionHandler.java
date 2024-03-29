package dev.mas.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
@Slf4j
public class AOPHomeworkExceptionHandler {

    private static final String BAD_REQUEST_REASON = "Incorrectly made request.";
    private static final String CONSTRAINT_VIOLATION_EXCEPTION_REASON
            = "Integrity constraint has been violated.";
    private static final String NOT_FOUND_REASON = "The required object was not found.";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.warn("[HTTP STATUS 500] {} ", e.getMessage(), e);
        return makeErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(final RuntimeException e) {
        log.warn("[HTTP STATUS 500] {} ", e.getMessage(), e);
        return makeErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final ValidationException e) {
        log.warn("[HTTP STATUS 400] {} ", e.getMessage(), e);
        return makeErrorResponse(HttpStatus.BAD_REQUEST, e, BAD_REQUEST_REASON);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.warn("[HTTP STATUS 400] {} ", e.getMessage(), e);
        return makeErrorResponse(HttpStatus.BAD_REQUEST, e, BAD_REQUEST_REASON);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNumberFormatException(final NumberFormatException e) {
        log.warn("[HTTP STATUS 400] {} ", e.getMessage(), e);
        return makeErrorResponse(HttpStatus.BAD_REQUEST, e, BAD_REQUEST_REASON);
    }


    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(final ResourceNotFoundException e) {
        log.warn("[HTTP STATUS 404] {} ", e.getMessage(), e);
        return makeErrorResponse(HttpStatus.NOT_FOUND, e, NOT_FOUND_REASON);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorResponse handleConstraintViolationException(final ConstraintViolationException e) {
        log.warn("[HTTP STATUS 409] {} ", e.getMessage(), e);
        return makeErrorResponse(HttpStatus.CONFLICT, e, CONSTRAINT_VIOLATION_EXCEPTION_REASON);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        log.warn("[HTTP STATUS 409] {} ", e.getMessage(), e);
        return makeErrorResponse(HttpStatus.CONFLICT, e, CONSTRAINT_VIOLATION_EXCEPTION_REASON);
    }

    private ErrorResponse makeErrorResponse(HttpStatus httpStatus, Throwable e, String reason) {

        return new ErrorResponse(
                httpStatus,
                reason,
                e.getLocalizedMessage(),
                LocalDateTime.now().format(formatter)
        );
    }
}
