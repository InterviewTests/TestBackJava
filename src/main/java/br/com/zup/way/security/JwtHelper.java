package br.com.zup.way.security;

import br.com.zup.way.db.postgres.model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtHelper {
    public static Long getCodigoUsuario() {
        return ((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
