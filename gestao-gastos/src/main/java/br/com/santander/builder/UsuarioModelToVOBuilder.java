package br.com.santander.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.model.Usuario;
import br.com.santander.vo.UsuarioVO;

/**
 * Builder responsável por construir um objeto de VO do Usuário a partir do modelo
 * @author AntonioJolvino
 *
 */
public class UsuarioModelToVOBuilder {

	private Usuario usuarioModel;

	private List<Usuario> usuarioModelList;

	public UsuarioModelToVOBuilder(Usuario usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public UsuarioModelToVOBuilder(List<Usuario> usuarioModelList) {
		this.usuarioModelList = usuarioModelList;
	}

	public UsuarioVO build() {
		UsuarioVO usuarioVO = new UsuarioVO(usuarioModel.getCodigoUsuario(), usuarioModel.getNome(), usuarioModel.getPassword(), usuarioModel.isStatusAtividade(), usuarioModel.getDataCadastro());
		return usuarioVO;
	}

	public List<UsuarioVO> buildList() {
		List<UsuarioVO> usuarioVOList = new ArrayList<UsuarioVO>();

		for (Usuario usuario : usuarioModelList) {
			usuarioVOList
					.add(new UsuarioVO(usuario.getCodigoUsuario(), usuario.getNome(), usuario.getPassword(), usuario.isStatusAtividade(), usuario.getDataCadastro()));
		}

		return usuarioVOList;
	}
}
