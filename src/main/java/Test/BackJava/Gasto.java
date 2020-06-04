package Test.BackJava;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Gasto {
	
	private @Id @GeneratedValue Long id;
	private String descricao;
	private Double valor;
	private Integer codigousuario;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date data;
	private String categoria;
	
	public Gasto(String descricao, Double valor, Integer codigousuario, Date data) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
		this.data = data;
	}
	
	public Gasto() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValor() {
		return valor;
	}

	public Integer getCodigousuario() {
		return codigousuario;
	}

	public Date getData() {
		return data;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "Gasto [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", codigousuario=" + codigousuario
				+ ", data=" + data + ", categoria=" + categoria + "]";
	}

	public static String arrayToString(Iterable<Gasto> gastos) {
		String retorno = "";
		for (Gasto gasto : gastos) {
			retorno += gasto + "\n";
		}
		return retorno;
	}
	
}