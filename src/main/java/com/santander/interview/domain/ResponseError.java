package com.santander.interview.domain;

public class ResponseError extends Response {
    int internalCode;

    public ResponseError(long statusCode, String userMessage, String internalMessage, int internalCode) {
        super(statusCode, userMessage, internalMessage);
        this.internalCode = internalCode;
    }

    public int getInternalCode() { return internalCode; }

    public void setInternalCode(int internalCode) { this.internalCode = internalCode; }
}
