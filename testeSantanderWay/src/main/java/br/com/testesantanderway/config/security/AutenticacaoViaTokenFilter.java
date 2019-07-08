package br.com.testesantanderway.config.security;

import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.modelo.Sistema;
import br.com.testesantanderway.repository.SistemaRepository;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    private ServicoDeToken tokenService;

    private SistemaRepository sistemaRepository;

    private ClienteRepository clienteRepository;

    public AutenticacaoViaTokenFilter(ServicoDeToken tokenService, SistemaRepository sistemaRepository, ClienteRepository clienteRepository) {
        this.tokenService = tokenService;
        this.sistemaRepository = sistemaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = AutenticacaoViaTokenFilter.recuperarToken(request);

        if (token != null && tokenService.isTokenValido(token)) {
            autenticar(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticar(String token) {
        String codigo = tokenService.getCodigo(token);

        UsernamePasswordAuthenticationToken authentication = null;
        Optional<Sistema> sistema = sistemaRepository.findById(codigo);
        if(sistema.isPresent()){
            authentication = new UsernamePasswordAuthenticationToken(sistema,
                    null, sistema.get().getAuthorities());
        }else{
            Optional<Cliente> cliente = clienteRepository.findById(codigo);

            if(cliente.isPresent()){
                authentication = new UsernamePasswordAuthenticationToken(sistema,
                        null, cliente.get().getAuthorities());
            }
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith(BEARER)) {
            return null;
        }

        return token.substring(BEARER.length());
    }
}
