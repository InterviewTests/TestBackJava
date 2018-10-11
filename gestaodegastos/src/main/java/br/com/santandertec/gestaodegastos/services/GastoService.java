package br.com.santandertec.gestaodegastos.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santandertec.gestaodegastos.daos.GastoDAO;
import br.com.santandertec.gestaodegastos.daos.UsuarioDAO;
import br.com.santandertec.gestaodegastos.dtos.GastoDTO;
import br.com.santandertec.gestaodegastos.models.Gasto;
import br.com.santandertec.gestaodegastos.models.GastoLista;
import br.com.santandertec.gestaodegastos.models.Usuario;

@Service
public class GastoService {
	
	@Autowired
	private GastoDAO gastoDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public GastoLista listarGastos(Integer codigoCliente) {
		GastoLista gastoLista = new GastoLista();
		gastoLista.setGastos(this.gastoDAO.findAll(codigoCliente));
		return gastoLista;
	}
	
	public Gasto buscarGasto(Integer id) {
		return this.gastoDAO.find(id);
	}
	
	public Gasto salvarGasto(GastoDTO gastoDTO) {
		
		Gasto gasto = new Gasto();
		
		gasto.setDescricao(gastoDTO.getDescricao());
		gasto.setValor(new BigDecimal(gastoDTO.getValor()));
		
		Usuario usuario = this.usuarioDAO.find(Integer.valueOf(gastoDTO.getCodigoUsuario()));
		
		if (usuario != null) {
			gasto.setUsuario(usuario);
		} else {
			throw new RuntimeException("ERRO: Gasto não cadastrado, pois o usuário do Gasto não foi encontrado!");
		}
		
		Gasto gastoCategorizado = this.gastoDAO.buscarGastoComMesmaDescricaoParaCategorizarAutomaticamente(gastoDTO.getDescricao(), usuario);
		
		if (gastoCategorizado != null) {
			gasto.setCategoria(gastoCategorizado.getCategoria());
		}
		
		Date dataFormatada;
		try {
			dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(gastoDTO.getData());
		} catch (ParseException e) {
			throw new RuntimeException("ERRO: Gasto não cadastrado, pois não foi possível formatar a data de compra do Gasto!");
		}
		gasto.setData(dataFormatada);
		
		return this.gastoDAO.save(gasto);
		
	}

	public Gasto salvarCategoriaDoGasto(Gasto gasto) {
		return this.gastoDAO.update(gasto);
	}
	
}
