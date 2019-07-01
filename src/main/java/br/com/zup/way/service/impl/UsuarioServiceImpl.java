package br.com.zup.way.service.impl;

import br.com.zup.way.db.postgres.model.Usuario;
import br.com.zup.way.db.postgres.repository.UsuarioRepository;
import br.com.zup.way.service.UsuarioService;
import br.com.zup.way.util.Exception.WayBusinessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Integer CLIENTE = 1;

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não Encontrado"));
    }

    @Override
    public Usuario findClienteByCodigoUsuario(Long codigoUsuario) throws WayBusinessException {
        return usuarioRepository.findByIdAndTipo(codigoUsuario, CLIENTE)
                .orElseThrow(() -> new WayBusinessException("Usuario não Encontrado"));
    }
}
