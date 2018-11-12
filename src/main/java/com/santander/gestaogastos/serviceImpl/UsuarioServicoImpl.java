package com.santander.gestaogastos.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.santander.gestaogastos.domain.Usuario;
import com.santander.gestaogastos.exception.GastosException;
import com.santander.gestaogastos.repository.UsuarioRepositorio;
import com.santander.gestaogastos.service.UsuarioServico;

public class UsuarioServicoImpl implements UsuarioServico<Usuario> {
	
	@Autowired
 	private UsuarioRepositorio usuarioRepositorio;
	

	@Override
	public Usuario salvarUsuario(Usuario usuarioIn) throws GastosException {
		Usuario usuario = new Usuario(usuarioRepositorio);
		
		return usuario.salvarUsuario(usuarioIn);
	}

	@Override
	public List<Usuario> listaUsuario() {
		Usuario usuario = new Usuario(usuarioRepositorio);
		
		return usuario.listaUsuarios();
	}

	@Override
	public <T> Object pesquisarUsuario(Integer id) throws GastosException {
		Usuario usuario = new Usuario(usuarioRepositorio);

		return usuario.pesquisarUsuario(id);
	}

	@Override
	public void removeUsuario(Usuario usuarioIn) throws GastosException {
		Usuario usuario = new Usuario(usuarioRepositorio);

		usuario.removeUsuario(usuarioIn);
		
	}



}
