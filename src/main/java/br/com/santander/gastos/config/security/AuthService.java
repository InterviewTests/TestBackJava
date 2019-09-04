package br.com.santander.gastos.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.santander.gastos.model.User;
import br.com.santander.gastos.repository.UserRepository;


@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByEmail(email);
		
		if(user.isPresent()){
			return user.get();
		}

		throw new UsernameNotFoundException("User not found.");
	}

}
