package br.com.santander.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.builder.UsuarioModelToVOBuilder;
import br.com.santander.builder.UsuarioVoToModelBuilder;
import br.com.santander.model.Usuario;
import br.com.santander.repository.UsuarioRepository;
import br.com.santander.service.UsuarioService;
import br.com.santander.vo.UsuarioVO;

/**
 * Classe de implementação do serviço de usuário
 * @author AntonioJolvino
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<UsuarioVO> findAll() {
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		
		List<UsuarioVO> usuariosVO = new  ArrayList<UsuarioVO>();
		if(usuarios != null && !usuarios.isEmpty()) {
			usuariosVO.addAll(new UsuarioModelToVOBuilder(usuarios).buildList());
		}
		return usuariosVO;
	}
	
	@Override
	public UsuarioVO findById(Long codigoUsuario) {
		Usuario usuarioModel = usuarioRepository.findById(codigoUsuario).orElse(null);
		
		UsuarioVO usuarioVO = null;
		
		if(usuarioModel != null) {
			usuarioVO = new UsuarioModelToVOBuilder(usuarioModel).build();
		}
		
		return usuarioVO;
	}
	
	@Override
	public Usuario save(UsuarioVO usuario) {
		return usuarioRepository.save(new UsuarioVoToModelBuilder(usuario).build());
	}

	@Override
	public void deleteAll() {
		usuarioRepository.deleteAll();
		
	}

	@Override
	public void deleteById(Long codigoUsuario) {
		usuarioRepository.deleteById(codigoUsuario);		
	}
}
