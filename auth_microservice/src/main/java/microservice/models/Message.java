package microservice.models;


public class Message {

    private String content;
    private boolean status;
    private String clientId; 

    public Message() { }

    public Message(String content, String clientId, boolean status) { 
        this.content = content; 
        this.clientId = clientId;
        this.status = status; 
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getStatus() { return status ? "success" : "failed"; }

    public void setStatus(boolean status) { this.status = status; }

    public String getClientId() { return clientId; }

    public void setClientId(String clientId) { this.clientId = clientId; }

}
