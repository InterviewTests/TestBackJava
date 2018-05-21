package br.com.resource.testbackjava.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resource.testbackjava.data.CreditCardOutgoing;
import br.com.resource.testbackjava.data.CreditCardOutgoingRepository;
import br.com.resource.testbackjava.data.Rank;
import br.com.resource.testbackjava.exception.MensagemTratadaException;
import br.com.resource.testbackjava.util.Utils;
import br.com.resource.testbackjava.vo.CreditCardOutgoingFilterVO;
import br.com.resource.testbackjava.vo.CreditCardOutgoingVO;
import br.com.resource.testbackjava.vo.FilterVO;

@Service
public class HomeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeService.class);
	
	private static final String NENHUMA_SUGESTAO = null; //"Nenhuma sugestão";

	@Autowired
	private CreditCardOutgoingRepository creditCardOutgoingRepository;

	@Autowired
	private RankService rankService;
	/**
	 * Traz os dados de gastos de cartão de crédito, segundo o filtro especificado:<br>
	 * codigo do usuário - obrigatório<br>
	 * data - dia dos gastos. se omitido, traz todos os gastos<br>
	 * 
	 * @param filter
	 * @return
	 * @throws MensagemTratadaException
	 */
	public List<CreditCardOutgoing> filtrar(FilterVO filter) throws MensagemTratadaException {

		if (filter.getCodigoUsuario() == null) {
			throw new MensagemTratadaException("Usuário inválido");
		}

		if (filter.getData() == null) {
			return filtrarTodas(filter.getCodigoUsuario());
		} else {
			return filtrarPorData(filter.getCodigoUsuario(), filter.getData());
		}

	}

	/**
	 * Filtra os gastos de cartão de crédito em uma data específica
	 * 
	 * @param codigoUsuario
	 * @param data
	 * @return
	 */
	private List<CreditCardOutgoing> filtrarPorData(Integer codigoUsuario, Date data) {
		return this.creditCardOutgoingRepository.findByDate(codigoUsuario, Utils.joinDateTime(data, 0), Utils.joinDateTime(data, 235959));
	}

	/**
	 * Traz todos os gastos de cartão de crédito do usuário indicado
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	private List<CreditCardOutgoing> filtrarTodas(Integer codigoUsuario) {
		return this.creditCardOutgoingRepository.findByUserCode(codigoUsuario);
	}
/**
 * Traz os detalhes deum gasto de cartão de crédito não categorizado, e as categorias indicadas segundo a descrição
 * @param id
 * @return
 * @throws MensagemTratadaException
 */
	public CreditCardOutgoingVO findById(String id) throws MensagemTratadaException {
		LOGGER.debug("findById: " + id);
		try {
	
			CreditCardOutgoing cco = obterPorIdString(id);
	
			String descricao = cco.getDescricao();
	
			if (descricao == null || descricao.isEmpty()) {
				throw new MensagemTratadaException("Gasto não tem descrição válida.");
			}
	
			TreeSet<Rank> categorias = new TreeSet<>(this.rankService.listarCategoriasUtilizadasPorDescricao(descricao));
	
			CreditCardOutgoingVO result = new CreditCardOutgoingVO();
			BeanUtils.copyProperties(cco, result);
			
			if (categorias == null || categorias.isEmpty()) {
				result.setCategoria(NENHUMA_SUGESTAO);
			} else {
				result.setCategoria(categorias.first().getCategoria());
				result.setCategorias(categorias);
			}
			
			return result;
		} catch (Exception e) {
			final String message = "Erro ao ver detalhes de gasto";
			LOGGER.error(message, e);
			throw new MensagemTratadaException(message );
		}
	}


	public void salvarCategoria(CreditCardOutgoingFilterVO filtro) throws MensagemTratadaException {
		LOGGER.debug("salvarCategoria: " + filtro);
		String id = filtro.getId();
		CreditCardOutgoing cco = obterPorIdString(id);
		cco.setCategoria(filtro.getCategoria());
		
		this.creditCardOutgoingRepository.save(cco);
		
		Rank rank = new Rank(cco.getDescricao(), filtro.getCategoria());
		this.rankService.save(rank);
		
	}
	
	private CreditCardOutgoing obterPorIdString(String id) throws MensagemTratadaException {
		LOGGER.debug("obterPorIdString: " + id);
		if (id == null) {
			throw new MensagemTratadaException("ID inválido");
		}
		UUID uuid = UUID.fromString(id);
		Optional<CreditCardOutgoing> opt = this.creditCardOutgoingRepository.findById(uuid);

		if (opt == null || !opt.isPresent()) {
			throw new MensagemTratadaException("Gasto não encontrado");
		}

		return opt.get();
	}
}
