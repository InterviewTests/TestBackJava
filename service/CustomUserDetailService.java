package br.com.altrantest.demo.service;

import br.com.altrantest.demo.repository.ValidationRepository;
import br.com.altrantest.demo.usuario.UserValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class CustomUserDetailService implements UserDetailsService {
    private  ValidationRepository validationRepository;
    @Autowired
    public CustomUserDetailService(ValidationRepository validationRepository) {
        this.validationRepository = validationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserValid user = Optional.ofNullable(validationRepository.findByUsername(username)).orElseThrow(()->new UsernameNotFoundException("User not Found"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        return new User(user.getUsername(),user.getPassword(),user.isAdmin()? authorityListAdmin : authorityListUser);
    }
}
