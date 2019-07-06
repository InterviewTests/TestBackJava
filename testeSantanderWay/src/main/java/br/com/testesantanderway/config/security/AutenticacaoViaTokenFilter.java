package br.com.testesantanderway.config.security;

import br.com.testesantanderway.modelo.Sistema;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    private ServicoDeToken tokenService;
    private ClienteRepository repository;

    public AutenticacaoViaTokenFilter(ServicoDeToken tokenService, ClienteRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = AutenticacaoViaTokenFilter.recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if (valido){
            autenticarCliente(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        String idCliente = tokenService.getCodigo(token);
        Sistema sistema = repository.findById(idCliente).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(sistema,
                null, sistema.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith(BEARER)){
            return null;
        }

        return token.substring(BEARER.length());
    }
}
