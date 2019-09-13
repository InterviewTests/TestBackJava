package com.santander.interview.domain;

public class ResponseObject extends Response {
    private Object data;

    public ResponseObject() { }

    public ResponseObject(long statusCode, String userMessage, String internalMessage, Object data) {
        super(statusCode, userMessage, internalMessage);
        this.data = data;
    }

    public Object getData() { return data; }

    public void setData(Object data) { this.data = data; }
}
