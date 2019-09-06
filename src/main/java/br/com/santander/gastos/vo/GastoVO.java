package br.com.santander.gastos.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.santander.gastos.model.Categoria;
import br.com.santander.gastos.model.Gasto;

public class GastoVO {

	private Long id;
	
	private Long idUsuario;
	
	private LocalDateTime data;
	
	private double valor;
	
	private String descricao;
	
	private Categoria categoria;

	public GastoVO(Gasto gasto){
		this.id = gasto.getId();
		this.idUsuario = gasto.getIdUsuario();
		this.data = gasto.getData();
		this.valor = gasto.getValor();
		this.descricao = gasto.getDescricao();
		this.categoria = new Categoria();
		if(gasto.getCategoria() != null){
			this.categoria.setId(gasto.getCategoria().getId());
			this.categoria.setDescricao(gasto.getCategoria().getDescricao());
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<GastoVO> converter(List<Gasto> listaGastos) {
		return listaGastos.stream().map(GastoVO::new).collect(Collectors.toList());
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
