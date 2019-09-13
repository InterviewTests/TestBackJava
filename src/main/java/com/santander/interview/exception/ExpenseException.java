package com.santander.interview.exception;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.enums.ResponseMessageEnum;
import org.springframework.http.HttpStatus;

public class ExpenseException extends Exception{
    private HttpStatus statusCode;
    private ResponseMessageEnum responseMessageEnum;
    private String message;

    public ExpenseException() {
        super();
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = super.getMessage();
        this.responseMessageEnum = UNKNOWN_ERROR;
    }

    public ExpenseException(HttpStatus statusCode, String message, ResponseMessageEnum responseMessageEnum) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.responseMessageEnum = responseMessageEnum;
    }

    public ExpenseException(HttpStatus statusCode, ResponseMessageEnum responseMessageEnum) {
        super();
        this.statusCode = statusCode;
        this.message = super.getMessage();
        this.responseMessageEnum = responseMessageEnum;
    }

    public HttpStatus getStatusCode() { return statusCode; }

    public void setStatusCode(HttpStatus statusCode) { this.statusCode = statusCode; }

    @Override
    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public ResponseMessageEnum getResponseMessageEnum() { return responseMessageEnum; }

    public void setResponseMessageEnum(ResponseMessageEnum responseMessageEnum) {
        this.responseMessageEnum = responseMessageEnum;
    }
}
