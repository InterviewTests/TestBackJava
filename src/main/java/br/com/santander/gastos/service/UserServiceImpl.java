package br.com.santander.gastos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.santander.gastos.model.User;
import br.com.santander.gastos.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String getUsuarioLogado() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			 return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
		
	}

	@Override
	public Optional<User> findByEmail(String userName) {

		return userRepository.findByEmail(userName);
	}

}
