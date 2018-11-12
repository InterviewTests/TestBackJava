package com.santander.gestaogastos.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.santander.gestaogastos.repository.UsuarioRepositorio;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String role;
	
	private UsuarioRepositorio usuarioRepositorio;
	
	public Usuario() {
		
	}
	
	public Usuario (UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
 	public List<Usuario> listaUsuarios(){
 		return this.usuarioRepositorio.findAll();
 	}
 	
 	public Usuario salvarUsuario(Usuario usuarioIn) {
 		
 		return this.usuarioRepositorio.save(usuarioIn);
 	}
 	
	public Usuario pesquisarUsuario(Integer id) {
		
		return this.usuarioRepositorio.getOne(id);
	}
 	 
	public void removeUsuario(Usuario usuarioIn) {
		this.usuarioRepositorio.delete(usuarioIn);
		
	}
}
