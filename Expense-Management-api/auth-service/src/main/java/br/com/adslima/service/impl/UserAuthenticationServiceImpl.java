package br.com.adslima.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adslima.entity.Roles;
import br.com.adslima.entity.Users;
import br.com.adslima.service.UserAuthenticationService;
import br.com.adslima.service.UserService;


@Service
@Transactional
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		String password = user.getPassword();
		Set<Roles> roles = user.getRoles();
		List<GrantedAuthority> userGrants = new ArrayList<>();
		for (Roles role : roles) {
			GrantedAuthority userGrant = new SimpleGrantedAuthority(role.getRoleName());
			userGrants.add(userGrant);
		}
		logger.info("user roles : {}", roles);
		return new User(username, password, userGrants);
	}

}
