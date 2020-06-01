package br.com.gft.clientes.model.enums;

public enum TipoUsuario {
	ADMIN(0, "Administrador"),
	USER(1, "Usuario");
	
	private Integer cod;
	private String descricao;
	
	private TipoUsuario(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String descricao() {
		return descricao;
	}
	
	public static TipoUsuario tipoEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (TipoUsuario x : TipoUsuario.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Valor inválido" + cod);
	}
}
