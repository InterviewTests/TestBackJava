package br.com.resource.testbackjava.vo;

public class UserVO {

	private Integer codigoUsuario;
	private String nome;
	private Boolean isLogado;
	private Boolean isAdmin;

	public UserVO() {}
	
	public UserVO(Integer codigoUsuario, String nome, Boolean isLogado, Boolean isAdmin) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nome = nome;
		this.isLogado = isLogado;
		this.isAdmin = isAdmin;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getIsLogado() {
		return isLogado;
	}

	public void setIsLogado(Boolean isLogado) {
		this.isLogado = isLogado;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "UserVO [codigoUsuario=" + codigoUsuario + ", nome=" + nome + ", isLogado=" + isLogado + ", isAdmin=" + isAdmin + "]";
	}

}
