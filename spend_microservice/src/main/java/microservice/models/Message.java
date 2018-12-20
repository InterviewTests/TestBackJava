package microservice.models;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Message {

    private String content;
    private String status;
    private String clientId;

    private ObjectMapper mapper = new ObjectMapper();

    public Message() { 
        this.status = "";
        this.content = "";
    }

    public Message(String content, String clientId, String status) { 
        this.content = content; 
        this.clientId = clientId;
        this.status = status;
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getClientId() { return clientId; }

    public void setClientId(String clientId) { this.clientId = clientId; }

    public String toJSONString() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }
    
}
