package br.com.santander.builder;

import br.com.santander.model.Usuario;
import br.com.santander.vo.UsuarioVO;
/**
 * Builder responsável por construir um objeto de Modelo do Usuário a partir do VO
 * @author AntonioJolvino
 *
 */
public class UsuarioVoToModelBuilder {
	
	
	private UsuarioVO usuarioVO;
	
	public UsuarioVoToModelBuilder (UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
	
	public Usuario build() {
		Usuario usuarioModel = new Usuario(usuarioVO.getCodigoUsuario(), usuarioVO.getNome(), usuarioVO.getPassword(), usuarioVO.isStatusAtividade(), usuarioVO.getDataCadastro());
		return usuarioModel;
	}
	
}
