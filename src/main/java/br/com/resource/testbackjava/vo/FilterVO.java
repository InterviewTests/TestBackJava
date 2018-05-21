package br.com.resource.testbackjava.vo;

import java.io.Serializable;
import java.util.Date;

public class FilterVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoUsuario;
	private Date data;

	public FilterVO() {
	}

	public FilterVO(Integer codigoUsuario, Date data) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.data = data;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FilterVO [codigoUsuario=" + codigoUsuario + ", data=" + data + "]";
	}

}
