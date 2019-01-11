package gestaogasto.repository;

import java.time.LocalDate;
import java.util.List;
import gestaogasto.model.Gasto;

public interface GastoRepository {
	
	public List<Gasto> listaGastosMaisAtuais();
	
	public List<Gasto> listaGastosPorData(LocalDate data);	
	
	public Gasto insert(Gasto gasto);
	
	public Gasto categorizaGasto(String idGasto, String idCategoria);

	public Gasto buscaUltimaCategoriaUsuarioGasto(Integer codigoUsuario);

}
