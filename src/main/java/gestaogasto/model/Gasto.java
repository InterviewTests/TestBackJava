package gestaogasto.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;


@Document
public class Gasto {
	
	@Id
	private String id;
	
	private String descricao;
	
	private Double valor;
	
	private Integer codigoUsuario;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	private String codigoCategoria;
			
	public Gasto() {
		super();
	}

	public Gasto(String id, String descricao, Double valor, Integer codigoUsuario, LocalDate data, String codigoCategoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.codigoUsuario = codigoUsuario;
		this.data = data;
		this.codigoCategoria = codigoCategoria;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}
	
	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	@Override
	public String toString() {
		return "Gasto [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", codigoUsuario=" + codigoUsuario
				+ ", data=" + data + "]";
	}

}
