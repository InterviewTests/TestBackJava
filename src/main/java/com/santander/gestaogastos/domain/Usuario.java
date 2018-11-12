package com.santander.gestaogastos.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Immutable;

import com.santander.gestaogastos.exception.GastosException;
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
	
	@OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	@Immutable
	private List<Gasto> gastos;
	
	@Transient
	private UsuarioRepositorio usuarioRepositorio;
	
	public Usuario() {
		
	}
	
	public Usuario (UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
 	public List<Usuario> listaUsuarios(){
 		return this.usuarioRepositorio.findAll();
 	}
 	
 	public Usuario salvarUsuario(Usuario usuarioIn) throws GastosException {
 		
 		validate();
 		
 		return this.usuarioRepositorio.save(usuarioIn);
 	}
 	
	public Usuario pesquisarUsuario(Integer id) throws GastosException {
		
		if (this.usuarioRepositorio.getOne(id) == null) {
			throw new GastosException("Usuário para ser excluido não existe !");
		}
		
		return this.usuarioRepositorio.getOne(id);
	}
 	 
	public void removeUsuario(Usuario usuarioIn) throws GastosException {
		
		Usuario usuario = (Usuario) this.pesquisarUsuario(id);

		if (usuario != null) {
			throw new GastosException("Usuário para ser excluido não existe !");
		}
		
		this.usuarioRepositorio.delete(usuarioIn);
		
	}
	
	public void validate () throws GastosException {
		
		if (this.nome != null && !"".equals(this.nome)) {
			throw new GastosException(" É necessário informar o nome do Usuário");
		}
		
		if (this.role != null && !"".equals(this.role)) {
			throw new GastosException(" É necessário informar a role do Usuário");
		}
	}
}
