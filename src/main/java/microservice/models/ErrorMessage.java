package microservice.models;

public class ErrorMessage {

    private String content;
    private boolean status;

    public ErrorMessage() { }

    public ErrorMessage(String content, boolean status) { 
        this.content = content; 
        this.status = status; 
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getStatus() { return status ? "success" : "failed"; }

    public void setStatus(boolean status) { this.status = status; }

}
