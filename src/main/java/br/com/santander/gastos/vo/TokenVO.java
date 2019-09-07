package br.com.santander.gastos.vo;

public class TokenVO {

	private String type;
	private String token;
	
	public TokenVO(String token, String type) {
		this.token = token;
		this.type = type;	
	}

	public String getType() {
		return type;
	}

	public String getToken() {
		return token;
	}
}
