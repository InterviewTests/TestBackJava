package br.com.testesantanderway.config.security;

import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> clientePorEmail = repository.findByEmail(username);

        if (clientePorEmail.isPresent()){
            return clientePorEmail.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos");
    }
}