package com.company.gestaogastos.domain.dto;

public class UsuarioDTO {
	private Long id;
	private String nome;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Long id, String nome) {
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
}
