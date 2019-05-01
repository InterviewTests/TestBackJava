package br.com.brunots.testes.everis.facade;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
