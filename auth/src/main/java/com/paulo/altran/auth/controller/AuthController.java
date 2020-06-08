package com.paulo.altran.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	
	@GetMapping("user")
	public Principal obterContextoDeSeguranca(Principal user) {		
		return user;
	}
	
}
