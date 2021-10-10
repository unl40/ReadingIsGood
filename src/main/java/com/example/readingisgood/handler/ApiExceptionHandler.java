package com.example.readingisgood.handler;

import com.example.readingisgood.dto.ApiExceptionDto;
import com.example.readingisgood.exception.ApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    public static final String ERROR_CODE_URI = "/help/errorCodes/";

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> handleApiException(Exception e, WebRequest request) {
        ApiException apiException = (ApiException) e;
        ApiExceptionDto error = new ApiExceptionDto();
        error.setType(ERROR_CODE_URI + ApiExceptionDto.CODE);
        error.setTitle(e.getMessage());
        error.setStatus(Integer.toString(apiException.getStatusCode()));
        error.setDetail(apiException.getDetail());
        error.setInstance(((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.valueOf(apiException.getStatusCode()));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleInternalServerError(Exception e, WebRequest request) {
        ApiExceptionDto error = new ApiExceptionDto();
        error.setType(ERROR_CODE_URI + ApiExceptionDto.CODE);
        error.setTitle("Internal Server error");
        error.setStatus(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setDetail("Unexpected error occurred. Please try again later.");
        error.setInstance(((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
