package br.com.gft.gastos.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.gastos.dto.GastoDTO;
import br.com.gft.gastos.model.Categoria;
import br.com.gft.gastos.model.Gasto;
import br.com.gft.gastos.repository.CategoriaRepository;
import br.com.gft.gastos.repository.GastoRepository;

@Service
public class GastoService {

	@Autowired
	private GastoRepository gastoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	Logger logger = LoggerFactory.getLogger(GastoService.class);

	public Gasto setGasto(GastoDTO gasto) {

		try {

			Gasto novoGasto = new Gasto();
			novoGasto.setDescricao(gasto.getDescricao());
			novoGasto.setCodigoUsuario(gasto.getCodigoUsuario());
			novoGasto.setValor(gasto.getValor());
			novoGasto.setData(gasto.getData());

			Categoria categoria = categoriaRepository.findByCategoria(gasto.getCategoria().getCategoria());
				
			if (categoria == null) {
				logger.info("Categoria não encontrada, id: " + gasto.getCategoria().getCategoria());
				categoria = gasto.getCategoria();
			}
			
			novoGasto.setCategoria(categoria);
			novoGasto.setCliente(gasto.getCliente());

			gastoRepository.save(novoGasto);
			return novoGasto;

		} catch (Exception exceptionGasto) {
			logger.error("Erro: " + exceptionGasto.getMessage());
			return null;
		}

	}

	public List<Gasto> lista() {
		List<Gasto> listaGasto = gastoRepository.findAll();

		return listaGasto;
	}

	public List<Gasto> listaPorIdCliente(Long id) {
		List<Gasto> list = gastoRepository.findByCliente(id);

		return list;
	}
	
	public List<Gasto> listaPorIdClienteEData(Long id, Date data) {
		List<Gasto> list = gastoRepository.findByClienteAndData(id,data);

		return list;
	}
}
