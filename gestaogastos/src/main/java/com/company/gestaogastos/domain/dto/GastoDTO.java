package com.company.gestaogastos.domain.dto;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class GastoDTO {
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Timestamp data;
	private CategoriaDTO categoria;
	private UsuarioDTO usuario;
	
	public GastoDTO() {
		super();
	}
	
	public GastoDTO(Long id, String descricao, BigDecimal valor, Timestamp data, CategoriaDTO categoria,
			UsuarioDTO usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	@Override
    public String toString() {
        return String.format("Gasto{id=%s, descricao=%s, valor=%s, data=%s}"
        		, getId(), getDescricao(), getValor(), getData());
    }

}