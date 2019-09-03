package com.santander.interview.domain;

public class Response {
    private String statusCode;
    private String message;
    private Object data;

    public Response() { }

    public String getStatusCode() { return statusCode; }

    public void setStatusCode(String statusCode) { this.statusCode = statusCode; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Object getData() { return data; }

    public void setData(Object data) { this.data = data; }
}
