package microservice.models;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Message {

    private String content;
    private boolean status;

    private ObjectMapper mapper = new ObjectMapper();

    public Message() { }

    public Message(String content, boolean status) { 
        this.content = content; 
        this.status = status; 
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getStatus() { return status ? "success" : "failed"; }

    public void setStatus(boolean status) { this.status = status; }

    public String toJSONString() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }
}
