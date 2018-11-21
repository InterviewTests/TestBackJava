package com.company.gestaogastos.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.company.gestaogastos.domain.repository.UsuarioRepository;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	
	@Transient
	private UsuarioRepository usuarioRepository;
	
	static final int USUARIOS_PAGE_SIZE = 4;
	
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "usuario", orphanRemoval = true)
	private List<Gasto> gastos = new ArrayList<Gasto>();

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public static int getUsuariosPageSize() {
		return USUARIOS_PAGE_SIZE;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

}
