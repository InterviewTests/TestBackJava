package gestaogasto.service;

import java.time.LocalDate;
import java.util.List;
import gestaogasto.model.Gasto;

public interface GastoService {
	
	public List<Gasto> listaGastosMaisAtuais();	
	
	public Gasto insert(Gasto gasto);
	
	public Gasto categorizaGasto(String idGasto, String idCategoria);

	public Object listaGastosPorData(LocalDate data);	
	
	public Gasto autoCategorizaGasto(Gasto gasto);

}
