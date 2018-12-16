package microservice.controllers;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import microservice.services.SystemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.http.MediaType;
import microservice.models.Message;
import microservice.models.System;
import microservice.models.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class SystemController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/system", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> inserNewSystem(UriComponentsBuilder builder,
                                    @Valid @RequestBody System system) 
                                    throws URISyntaxException, InterruptedException, ExecutionException {

        CompletableFuture<System> systemFuture = systemService.register(system);
        System storedSystem = systemFuture.get();
        return ResponseEntity
                    .created(new URI(builder.toUriString() + "/" + storedSystem.get_id()))
                    .body(new Message("system " + system.getUsername() + " successfully registered", true));
    }

    @RequestMapping(value = "/system/authentication", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> authenticateSystem(@Valid @RequestBody System system) 
                                throws InterruptedException, ExecutionException {

        CompletableFuture<?> systemFuture = systemService.authenticate(system);
        Object result = systemFuture.get();
        if (result.getClass() == Authorization.class)
            return ResponseEntity.ok(result);
        else
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/system/authorization", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> authorizeSystem(@RequestHeader("Authorization") String accessToken) 
                                throws InterruptedException, ExecutionException {

        CompletableFuture<Message> systemFuture = systemService.authorize(accessToken);
        Message msg = systemFuture.get();
        if (msg.getStatus().equals("success"))
            return ResponseEntity.ok(msg);
        else
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
    }

}
