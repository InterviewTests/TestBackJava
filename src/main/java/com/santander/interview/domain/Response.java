package com.santander.interview.domain;

public class Response {
    private long statusCode;
    private String userMessage;
    private String internalMessage;

    public Response() { }

    public Response(long statusCode, String userMessage, String internalMessage) {
        this.statusCode = statusCode;
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
    }

    public long getStatusCode() { return statusCode; }

    public void setStatusCode(long statusCode) { this.statusCode = statusCode; }

    public String getUserMessage() { return userMessage; }

    public void setUserMessage(String userMessage) { this.userMessage = userMessage; }

    public String getInternalMessage() { return internalMessage; }

    public void setInternalMessage(String internalMessage) { this.internalMessage = internalMessage; }

}
