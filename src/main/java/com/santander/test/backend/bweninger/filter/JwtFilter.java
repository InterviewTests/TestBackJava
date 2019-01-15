package com.santander.test.backend.bweninger.filter;

import com.santander.test.backend.bweninger.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getRequestURI().startsWith("/api/login") || req.getRequestURI().startsWith("/api/usuarios")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = req.getHeader(JwtUtil.TOKEN_HEADER);

        if (token == null || token.trim().isEmpty()) {
            res.setStatus(401);
            return;
        }

        try {
            Jws<Claims> parser = JwtUtil.decode(token);
            System.out.println("User request: " + parser.getBody().getSubject());
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SignatureException e) {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Override
    public void destroy() {

    }
}
