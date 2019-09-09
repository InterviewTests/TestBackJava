package com.santander.interview.domain;

public class ResponsePost {
    private long statusCode;
    private String userMessage;
    private String internalMessage;
    private Object data;

    public ResponsePost() { }

    public ResponsePost(long statusCode, String userMessage, String internalMessage, Object data) {
        this.statusCode = statusCode;
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
        this.data = data;
    }

    public long getStatusCode() { return statusCode; }

    public void setStatusCode(long statusCode) { this.statusCode = statusCode; }

    public String getUserMessage() { return userMessage; }

    public void setUserMessage(String userMessage) { this.userMessage = userMessage; }

    public String getInternalMessage() { return internalMessage; }

    public void setInternalMessage(String internalMessage) { this.internalMessage = internalMessage; }

    public Object getData() { return data; }

    public void setData(Object data) { this.data = data; }
}
