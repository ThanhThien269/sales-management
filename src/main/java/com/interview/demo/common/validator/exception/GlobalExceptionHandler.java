package com.interview.demo.common.validator.exception;

import com.interview.demo.constant.enumuration.MessageCode;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.api.response.FixedLocaleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CompletionException.class)
    public ResponseEntity<ApiResponse> handleCompletionException(CompletionException ex) {
        Throwable cause = ex.getCause() != null ? ex.getCause() : ex;

        if (cause instanceof DataNotFoundException dnf) {
            return handleDataNotFound(dnf);
        }
        if (cause instanceof IllegalArgumentException iae) {
            return handleIllegalArgument(iae);
        }

        log.error("Unhandled CompletionException", cause);
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, MessageCode.INTERNAL_SERVER_ERROR, cause.getMessage());
    }


    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiResponse> handleDataNotFound(DataNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, MessageCode.DATA_NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return buildError(HttpStatus.BAD_REQUEST, MessageCode.INVALID_REQUEST_PARAMETERS, ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildError(HttpStatus.BAD_REQUEST, MessageCode.INVALID_REQUEST_PARAMETERS, errors);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception", ex);
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, MessageCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    }



    private ResponseEntity<ApiResponse> buildError(HttpStatus status, MessageCode code, String debugMessage) {
        ApiResponse response = ApiResponse.builder()
                .result("error")
                .localeMessage(new FixedLocaleMessage(code))
                .errors(debugMessage)
                .status(status)
                .build();
        return new ResponseEntity<>(response, status);
    }
}

