package br.com.santander.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.dtos.GastoDTO;
import br.com.santander.entities.Gasto;
import br.com.santander.repositories.GastoRepository;

@Service
public class GastoService {

//	Integer codigoCliente = 1;
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(GastoService.class);
	
	@Autowired
	private GastoRepository gastoRepository;
	
	public GastoDTO cadastrar(GastoDTO gastoDTO, Integer codigousuario) {
		Gasto gasto = new Gasto();
		BeanUtils.copyProperties(gastoDTO, gasto, "uuid");
		gasto.setCodigousuario(codigousuario);
		List<Gasto> gastosSalvos = gastosPorDescricao(gasto.getDescricao(), codigousuario);
		if(gastosSalvos.size() > 0) {
			if(gastosSalvos.get(0).getCategoria() != null) {
				gasto.setCategoria(gastosSalvos.get(0).getCategoria());
			}
		}
		if(gasto.getData() == null) {
			gasto.setData(new Date());
		}
		BeanUtils.copyProperties(gastoRepository.save(gasto), gastoDTO);
		return gastoDTO;
	}
	
	public Gasto salve(Gasto gasto, Integer codigousuario) {
		gasto.setCodigousuario(codigousuario);
		return gastoRepository.save(gasto);
	}
	
	
	public List<GastoDTO> listar(Integer codigousuario){
		List<GastoDTO> gastosDTO = new ArrayList<>();
		List<Gasto> gastos = gastoRepository.findAllByCodigousuario(codigousuario);
		gastos.forEach(g -> {
			GastoDTO gastoDTO = new GastoDTO();
			BeanUtils.copyProperties(g, gastoDTO);
			gastosDTO.add(gastoDTO);
		});
		return gastosDTO;
	}
	
	public List<GastoDTO> listarPorData(String data, Integer codigousuario){
		if(dataValida(data)) {
			int dia = Integer.parseInt(data.split("/")[0]);
			int mes = Integer.parseInt(data.split("/")[1]);
			int ano = Integer.parseInt(data.split("/")[2]);
			Calendar inicio = GregorianCalendar.getInstance();
			inicio.set(ano, mes-1, dia, 0, 0);
			
			Calendar fim = GregorianCalendar.getInstance();
			fim.set(ano, mes-1, dia, 23, 59);
			
			List<GastoDTO> gastosDTO = new ArrayList<>();
			List<Gasto> gastos = gastoRepository.findAllByDataBetweenAndCodigousuario(inicio.getTime(), fim.getTime(), codigousuario);
			gastos.forEach(g -> {
				GastoDTO gastoDTO = new GastoDTO();
				BeanUtils.copyProperties(g, gastoDTO);
				gastosDTO.add(gastoDTO);
			});
			return gastosDTO;
		}
		return null;
		
	}
	
	public GastoDTO getDTO(String uuid, Integer codigousuario) {
		Optional<Gasto> gasto = gastoRepository.findByUuidAndCodigousuario(uuid, codigousuario);
		GastoDTO gastoDTO = new GastoDTO();
		BeanUtils.copyProperties(gasto.get(), gastoDTO);
		return gastoDTO;
	}
	
	private Gasto get(String uuid, Integer codigousuario) {
		Optional<Gasto> gasto = gastoRepository.findByUuidAndCodigousuario(uuid, codigousuario);
		return gasto.get();
	}
	
	/**
	 * Método de uso interno(private) para recuperar os gastos por uma descriç~ao
	 * @author valdeci
	 * @param descricao
	 * @param codigousuario
	 * @return
	 */
	private List<Gasto> gastosPorDescricao(String descricao, Integer codigousuario){
		return gastoRepository.findAllByLikeDescricaoAndCodigousuario(descricao, codigousuario);
	}
	
	/**
	 * Esse método altera a categoria de um gasto que foi 
	 * @author valdeci
	 * @param categoria
	 * @param uuid
	 * @param codigousuario
	 * @return
	 */
	public GastoDTO alterarCategoria(String categoria, String uuid, Integer codigousuario) {
		Gasto gasto = get(uuid, codigousuario);
		gasto.setCategoria(categoria);
		GastoDTO gastoDTO = new GastoDTO();
		salve(gasto, codigousuario);
		BeanUtils.copyProperties(gasto, gastoDTO);
		return gastoDTO;
	}
	
	/**
	 * Método de uso interno para fazer algumas validações de data. Esse método não foi copiado da internet. O mesmo
	 * foi elaborado por mim
	 * @author valdeci
	 * @param data
	 * @return
	 */
	private Boolean dataValida(String data) {
    	if(data == null) {
    		ResponseUtil.mensagemErro("Valor da data está Null");
    		return false;
    	}else {
    		char separador = data.charAt(2);
        	if(separador != '-' && separador != '/') {
        		ResponseUtil.mensagemErro("A "+ data +" não está no padrão brasileiro");
        		return false;
        	}else {
        		if(separador == '/') {
        			int dia = Integer.parseInt(data.split("/")[0]);
            		int mes = Integer.parseInt(data.split("/")[1]);
            		int ano = Integer.parseInt(data.split("/")[2]);
            		if(dia > 30) {
            			ResponseUtil.mensagemErro("A "+ data +" está inválida");
            			return false;
            		}
            		if(mes < 1 || mes > 12) {
            			ResponseUtil.mensagemErro("A "+ data +" está inválida");
            			return false;
            		}
        		}else {
        			int dia = Integer.parseInt(data.split("-")[0]);
            		int mes = Integer.parseInt(data.split("-")[1]);
            		int ano = Integer.parseInt(data.split("-")[2]);
            		if(dia > 30) {
            			ResponseUtil.mensagemErro("A "+ data +" está inválida");
            			return false;
            		}
            		if(mes < 1 || mes > 12) {
            			ResponseUtil.mensagemErro("A "+ data +" está inválida");
            			return false;
            		}
        		}
        	}
    	}
    	return true;
    	
    }
	
	public List<String> pesquisarCategorias(String categoria, Integer codigousuario){
		return gastoRepository.findCategoraiasByCodigousuarioAndLikeCategoria(codigousuario, "%"+categoria+"%");
	}
}























