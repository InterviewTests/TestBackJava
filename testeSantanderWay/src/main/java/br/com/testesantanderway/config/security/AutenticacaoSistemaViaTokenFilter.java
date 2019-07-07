package br.com.testesantanderway.config.security;

import br.com.testesantanderway.modelo.Sistema;
import br.com.testesantanderway.modelo.Usuario;
import br.com.testesantanderway.repository.SistemaRepository;
import br.com.testesantanderway.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    @Autowired
    private ServicoDeToken tokenService;

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = AutenticacaoViaTokenFilter.recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if (valido){
            autenticarUsuario(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarUsuario(String token) {
        String codigoUsuario = tokenService.getCodigo(token);
        Usuario usuario = usuarioRepository.findById(codigoUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,
                null, Arrays.asList(() -> "USUARIO"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void autenticarSistema(String token) {
        String codigoSistema = tokenService.getCodigo(token);
        Sistema sistema = sistemaRepository.findById(codigoSistema).get();
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
