package com.santander.interview.domain;

public class Response {
    private long statusCode;
    private String message;
    private Object data;

    public Response() { }

    public Response(long statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public long getStatusCode() { return statusCode; }

    public void setStatusCode(long statusCode) { this.statusCode = statusCode; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Object getData() { return data; }

    public void setData(Object data) { this.data = data; }
}
