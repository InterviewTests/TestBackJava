package br.com.adslima.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adslima.entity.Users;
import br.com.adslima.service.impl.UserServiceImpl;


@RestController
@RequestMapping(value = "/users")
public class UserController {
  
  @Autowired
  private UserServiceImpl userServiceImpl;
  
  @GetMapping("/authenticate")
  public ResponseEntity<Principal> user(Principal user) {
   return ResponseEntity.<Principal>ok(user);
  }
  
  @GetMapping
  public ResponseEntity<Users> getUserByUsername(Principal principal){
    Users user = userServiceImpl.findByUsername(principal.getName());
    return ResponseEntity.<Users>ok(user);    
  }
}
