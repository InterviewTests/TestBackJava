package microservice.models;


public class Message {

    private String content;
    private boolean status;

    public Message() { }

    public Message(String content, boolean status) { 
        this.content = content; 
        this.status = status; 
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getStatus() { return status ? "success" : "failed"; }

    public void setStatus(boolean status) { this.status = status; }

}
