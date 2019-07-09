package br.com.testesantanderway.config.security;

import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.modelo.Sistema;
import br.com.testesantanderway.repository.SistemaRepository;
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
    private SistemaRepository sistemaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Sistema> sistema = sistemaRepository.findByEmail(username);

        if (sistema.isPresent()) {
            return sistema.get();
        }

        Optional<Cliente> usuario = clienteRepository.findByEmail(username);

        if (usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos");
    }
}