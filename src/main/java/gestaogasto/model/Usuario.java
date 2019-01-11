package gestaogasto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {
	
	@Id
	private Long codigoUsuario;
	
	private String nomeUsuario;
	
	private Categoria categoria;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(Long codigoUsuario, String nomeUsuario, Categoria categoria) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nomeUsuario = nomeUsuario;
		this.categoria = categoria;
	}

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Usuario [codigoUsuario=" + codigoUsuario + ", nomeUsuario=" + nomeUsuario + ", categoria=" + categoria
				+ "]";
	}	

}
