package microservice.controllers;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import microservice.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import microservice.models.Authorization;
import microservice.models.Message;
import microservice.models.User;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> inserNewUser(UriComponentsBuilder builder,
                                    @Valid @RequestBody User user) 
                                    throws URISyntaxException, InterruptedException, ExecutionException {

        CompletableFuture<User> userFuture = userService.insertNewUser(user);
        User storedUser = userFuture.get();
        return ResponseEntity
                    .created(new URI(builder.toUriString() + storedUser.get_id()))
                    .body(new Message("user " + user.getUsername() + " successfully registered", true));
    }

    @RequestMapping(value = "/user/authorize", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> authorizeUser(@Valid @RequestBody User user) 
                                throws URISyntaxException, InterruptedException, ExecutionException {

        CompletableFuture<?> userFuture = userService.authorizeUser(user);
        Object result = userFuture.get();
        if (result.getClass() == Authorization.class)
            return ResponseEntity.ok(result);
        else
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/user/authenticate", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> authenticateUser(@RequestHeader("Authorization") String accessToken) 
                                throws URISyntaxException, InterruptedException, ExecutionException {

        CompletableFuture<Message> userFuture = userService.authenticateUser(accessToken);
        Message msg = userFuture.get();
        if (msg.getStatus().equals("success"))
            return ResponseEntity.ok(msg);
        else
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
    }

}
