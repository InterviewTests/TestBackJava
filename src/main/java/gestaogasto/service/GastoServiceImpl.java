package gestaogasto.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gestaogasto.model.Gasto;
import gestaogasto.repository.GastoRepository;

@Service
public class GastoServiceImpl implements GastoService{
	
	@Autowired
	private GastoRepository gastoRepository;

	@Override
	public List<Gasto> listaGastosMaisAtuais() {
		return gastoRepository.listaGastosMaisAtuais();
	}

	@Override
	public Gasto insert(Gasto gasto) {
		return gastoRepository.insert(gasto);
	}

	@Override
	public Gasto categorizaGasto(String idGasto, String idCategoria) {
		return gastoRepository.categorizaGasto(idGasto, idCategoria);
	}

	@Override
	public List<Gasto> listaGastosPorData(LocalDate dataGasto) {
		return gastoRepository.listaGastosPorData(dataGasto);
	}

	@Override
	public Gasto autoCategorizaGasto(Gasto gasto) {

		Gasto gastoComCategoria = buscaUltimaCategoriaUsuario(gasto.getCodigoUsuario());
		
		if(gastoComCategoria!=null) {
			String categoriaSugerida = gastoComCategoria.getCodigoCategoria();
			gasto.setCodigoCategoria(categoriaSugerida);
		}
		
		return gastoRepository.insert(gasto);
	}

	private Gasto buscaUltimaCategoriaUsuario(Integer codigoUsuario) {		
		return gastoRepository.buscaUltimaCategoriaUsuarioGasto(codigoUsuario);
	}

}
