package br.com.santander.service;

import java.util.List;

import br.com.santander.model.Usuario;
import br.com.santander.vo.UsuarioVO;

/**
 * Interface do serviço de usuário
 * @author AntonioJolvino
 *
 */
public interface UsuarioService {
	
	List<UsuarioVO> findAll();
	
	UsuarioVO findById(Long codigoUsuario);

	Usuario save(UsuarioVO usuario);
	
	void deleteAll();
	
	void deleteById(Long id);
}
