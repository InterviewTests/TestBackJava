package gestaogasto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Categoria {
	
	@Id
	private String id;
	private String descricaoCategoria;	
		
	public Categoria() {
		super();
	}

	public Categoria(String id, String descricaoCategoria) {
		super();
		this.id = id;
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}	

}
