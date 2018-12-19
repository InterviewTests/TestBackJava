package microservice.util;

import microservice.models.Authorization;
import microservice.models.Client;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;


public final class AuthRequester {

    public static Authorization authenticate(String authenticationURL, String username, String password) {
        HttpEntity<Client> request = new HttpEntity<>(new Client(username, password));
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<Authorization> authResponse = restTemplate.postForEntity(authenticationURL, request, Authorization.class);
        return authResponse.getBody();
    }

}