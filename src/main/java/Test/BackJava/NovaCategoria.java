package Test.BackJava;

public class NovaCategoria {
	
	private Long idgasto;
	private String categoria;
	
	public NovaCategoria(Long idgasto, String categoria) {
		super();
		this.idgasto = idgasto;
		this.categoria = categoria;
	}
	
	public NovaCategoria() {
		super();
	}

	public Long getIdgasto() {
		return idgasto;
	}

	public void setIdgasto(Long idgasto) {
		this.idgasto = idgasto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "NovaCategoria [idgasto=" + idgasto + ", categoria=" + categoria + "]";
	}
	
}