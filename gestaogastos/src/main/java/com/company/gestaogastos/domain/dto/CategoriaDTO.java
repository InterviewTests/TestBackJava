package com.company.gestaogastos.domain.dto;

public class CategoriaDTO {
	private Long id;
	private String nome;
	
	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Long id, String nome) {
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

	@Override
    public String toString() {
        return String.format("Categoria{id=%s, nome=%s}"
        		, getId(), getNome());
    }

}