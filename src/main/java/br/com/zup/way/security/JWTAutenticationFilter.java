package br.com.zup.way.security;

import br.com.zup.way.db.postgres.model.Usuario;
import br.com.zup.way.service.UsuarioService;
import br.com.zup.way.service.security.JWTTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class JWTAutenticationFilter extends OncePerRequestFilter {

    private static final String HEADER_STRING = "Authorization";

    private JWTTokenService tokenService;

    private UsuarioService usuarioService;

    public JWTAutenticationFilter(JWTTokenService tokenService, UsuarioService usuarioService) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(httpServletRequest);
        if (tokenService.isValidToken(token)) {
            doAutentication(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void doAutentication(String token) {
        Usuario user = usuarioService.findById(tokenService.getIdByToken(token));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
    }


    private String getToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(HEADER_STRING);
        if (StringUtils.isNotBlank(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
