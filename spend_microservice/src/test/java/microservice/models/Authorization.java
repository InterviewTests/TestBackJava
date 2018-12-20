package microservice.models;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


public class Authorization {

    private String accessToken;
    private String status;
    private String clientId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, 
                pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", 
                timezone="UTC")
    private Date expiryDate;

    public Authorization() { }

    public Authorization(String accessToken, Date expiryDate, String clientId, String status) { 
        this.accessToken = accessToken; 
        this.status = status;
        this.expiryDate = expiryDate;
        this.clientId = clientId;
    }

    public String getAccessToken() { return accessToken; }

    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public Date getExpiryDate() { return expiryDate; }

    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getClientId() { return clientId; }

    public void setClientId(String clientId) { this.clientId = clientId; }

}
