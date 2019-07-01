package br.com.zup.way.service;

import br.com.zup.way.db.postgres.model.Usuario;
import br.com.zup.way.util.Exception.WayBusinessException;

public interface UsuarioService {

    Usuario findById(Long idUsuario);

    Usuario findClienteByCodigoUsuario(Long codigoUsuario) throws WayBusinessException;
}
