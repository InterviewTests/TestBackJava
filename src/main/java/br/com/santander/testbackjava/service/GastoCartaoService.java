/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.testbackjava.exception.CategoriaException;
import br.com.santander.testbackjava.exception.GastoCartaoException;
import br.com.santander.testbackjava.function.GastoCartaoFunction;
import br.com.santander.testbackjava.model.dto.CategoriaDTO;
import br.com.santander.testbackjava.model.dto.CategoriaGastoCartaoDTO;
import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import br.com.santander.testbackjava.model.entity.GastoCartao;
import br.com.santander.testbackjava.model.repository.IGastoCartaoRepository;
import br.com.santander.testbackjava.util.AppUtil;
import lombok.extern.log4j.Log4j2;

/**
 * Classe responsável por realizar as operações de negócio da entidade Gasto.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 17:42:09
 * @version x.x
 */
@Service
@Log4j2
public class GastoCartaoService {

	/**
	 * Atributo sistemaCredenciadoService
	 */
	@Autowired
	private SistemaCredenciadoService sistemaCredenciadoService;

	/**
	 * Atributo autenticacaoService
	 */
	@Autowired
	private AutenticacaoService autenticacaoService;

	/**
	 * Atributo validateGestaoCartaoService
	 */
	@Autowired
	private ValidateGestaoCartaoService validateGestaoCartaoService;
	
	/**
	 * Atributo categoriaService
	 */
	@Autowired
	private CategoriaService categoriaService;

	/**
	 * Atributo gastoRepository
	 */
	@Autowired
	private IGastoCartaoRepository gastoRepository;

	/**
	 * Método responsável por realizar Integração de gastos por cartão
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 19:20:43
	 * @param gastoCartaoDTO - {@link GastoCartaoDTO} - GastoCartaoDTO.
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void incluirGastoCartao(GastoCartaoDTO gastoCartaoDTO) throws GastoCartaoException {
		log.info("INICIO - Método: incluirGastoCartao");

		try {

			GastoCartao gastoCartao = GastoCartaoFunction.CONVERTER_GASTO_CARTAO_TO_ENTITY.apply(gastoCartaoDTO);
			
			categorizarAutomaticoGastoCartao(gastoCartao);

			gastoRepository.save(gastoCartao);

		} catch (Exception e) {
			log.error("Erro GastoCartaoService.incluirGastoCartao", e);
			throw new GastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: incluirGastoCartao");
	}

	/**
	 * Método responsável por realizar Categorização automatica de gasto.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 21:33:56
	 *
	 * @param gastoCartao {@link GastoCartao} gastoCartao.
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	private void categorizarAutomaticoGastoCartao(GastoCartao gastoCartao) throws GastoCartaoException {
		CategoriaDTO categoriaDTO = obterCategoriaGastoCartaoPorDescricao(gastoCartao.getDescricao());
		if (categoriaDTO != null) {
			gastoCartao.setIdCategoria(categoriaDTO.getId());
		}
	}
	
	/**
	 * Método responsável por listar gastos atuais do cliente autenticado
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 19:21:24
	 * @param codigoUsuario {@link Long} codigoUsuario.
	 * @return {@link List<GastoCartaoDTO>}
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<GastoCartaoDTO> obterGastosAtuaisCartao(Long codigoUsuario, Integer pagina, Integer totalPagina)
			throws GastoCartaoException {
		log.info("INICIO - Método: obterGastosAtuaisCartao");

		List<GastoCartaoDTO> listaGastoCartao = new ArrayList<GastoCartaoDTO>();

		try {

			// Dado que acesso como um cliente autenticado que pode visualizar os gastos do cartão
			autenticacaoService.validarSessaoCliente();

			// Então gostaria de ver meus gastos mais atuais.
			// O cliente espera ver gastos realizados a 5 segundos atrás.
			Date dataMenos5SegundosAtras = AppUtil.getDateMenos5Segundos();

			Optional<List<GastoCartao>> listaBanco = gastoRepository.findByCodigoUsuarioAndDataLessThanEqual(codigoUsuario,
					dataMenos5SegundosAtras, PageRequest.of(pagina, totalPagina));

			if (listaBanco.isPresent()) {
				listaGastoCartao.addAll(listaBanco.get().stream().map(GastoCartaoFunction.CONVERTER_GASTO_CARTAO_TO_DTO)
						.collect(Collectors.toList()));
			}

		} catch (Exception e) {
			log.error("Erro GastoCartaoService.obterGastosAtuaisCartao", e);
			throw new GastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: obterGastosAtuaisCartao");
		return listaGastoCartao;
	}

	/**
	 * Método responsável por listar gastos atuais de acordo com o filtro.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 19:21:24
	 * @param codigoUsuario {@link Long} codigoUsuario.
	 * @param codigoUsuario {@link Date} dataFiltro.
	 * @return {@link List<GastoCartaoDTO>}
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<GastoCartaoDTO> obterGastosCartaoFiltroData(Long codigoUsuario, Date dataFiltro, Integer pagina,
			Integer totalPagina) throws GastoCartaoException {
		log.info("INICIO - Método: obterGastosCartaoFiltroData");

		List<GastoCartaoDTO> listaGastoCartao = new ArrayList<GastoCartaoDTO>();

		try {

			// Dado que acesso como um cliente autenticado
			autenticacaoService.validarSessaoCliente();

			// configure o filtro de data igual a 27/03/1992
			// Então gostaria de ver meus gastos apenas deste dia.

			Optional<List<GastoCartao>> listaBanco = gastoRepository.findByCodigoUsuarioAndData(codigoUsuario, dataFiltro,
					PageRequest.of(pagina, totalPagina));

			if (listaBanco.isPresent()) {
				listaGastoCartao.addAll(listaBanco.get().stream().map(GastoCartaoFunction.CONVERTER_GASTO_CARTAO_TO_DTO)
						.collect(Collectors.toList()));
			}

		} catch (Exception e) {
			log.error("Erro GastoCartaoService.obterGastosCartaoFiltroData", e);
			throw new GastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: obterGastosCartaoFiltroData");
		return listaGastoCartao;
	}

	/**
	 * Método responsável por detalhar gasto do cartão.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 20:12:14
	 * @param id {@link String} id.
	 * @return {@link GastoCartaoDTO}
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public GastoCartaoDTO obterGastoCartaoPorId(String id) throws GastoCartaoException {
		log.info("INICIO - Método: obterGastoCartaoPorId");

		GastoCartaoDTO gastoCartao = new GastoCartaoDTO();

		try {

			// Dado que acesso como um cliente autenticado
			autenticacaoService.validarSessaoCliente();

			// acesso o detalhe de um gasto

			Optional<GastoCartao> dadoBanco = gastoRepository.findById(UUID.fromString(id));

			if (dadoBanco.isPresent()) {
				gastoCartao = GastoCartaoFunction.CONVERTER_GASTO_CARTAO_TO_DTO.apply(dadoBanco.get());
				
				if (dadoBanco.get().getIdCategoria() != null) {
					CategoriaDTO categoriaDTO = categoriaService.obterCategoriaPorId(dadoBanco.get().getIdCategoria().toString());
					gastoCartao.setCategoria(categoriaDTO);
				}
			} else {
				throw new GastoCartaoException("Detalhe gasto cartão não foi localizado, id: " + id);
			}

		} catch (GastoCartaoException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error("Erro GastoCartaoService.obterGastoCartaoPorId", e);
			throw new GastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: obterGastoCartaoPorId");
		return gastoCartao;
	}
	
	/**
	 * Método responsável por realizar atualização gastos por cartão
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 19:20:43
	 * @param categoriaGastoCartaoDTO - {@link CategoriaGastoCartaoDTO} - categoriaGastoCartaoDTO.
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void atualizarCategoriaGastoCartao(final CategoriaGastoCartaoDTO categoriaGastoCartaoDTO) throws GastoCartaoException {
		log.info("INICIO - Método: atualizarCategoriaGastoCartao");

		try {
			
			// Aplica validação das informações
			validateGestaoCartaoService.validarIncluirCategoriaGastoCartao(categoriaGastoCartaoDTO);

			// E este não possui uma categoria
			// Então devo conseguir incluir uma categoria para estee
			CategoriaDTO categoriaDTO = categoriaService.obterCategoriaPorDescricao(categoriaGastoCartaoDTO.getCategoria());
			if (categoriaDTO == null) {
				categoriaDTO = categoriaService.incluirCategoria(categoriaGastoCartaoDTO.getCategoria());
			}
			
			GastoCartaoDTO gastoCartaoDTO = obterGastoCartaoPorId(categoriaGastoCartaoDTO.getIdGastoCartao());
			
			GastoCartao gastoCartao = gastoRepository.findById(gastoCartaoDTO.getId()).get();
			gastoCartao.setIdCategoria(categoriaDTO.getId());

			gastoRepository.save(gastoCartao);

		} catch (CategoriaException e) {
			log.error(e.getMessage(), e);
			throw new GastoCartaoException(e.getMessage(), e);
		} catch (Exception e) {
			log.error("Erro GastoCartaoService.incluirGastoCartao", e);
			throw new GastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: atualizarCategoriaGastoCartao");
	}
	
	/**
	 * Método responsável por obter categoria do gasto do cartão.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 21:35:09
	 * @param descriaoGastoCartao {@link String} descriaoGastoCartao.
	 * @return {@link CategoriaDTO}
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public CategoriaDTO obterCategoriaGastoCartaoPorDescricao(String descriaoGastoCartao) throws GastoCartaoException {
		log.info("INICIO - Método: obterCategoriaGastoCartaoPorDescricao");

		CategoriaDTO categoriaDTO = null;

		try {

			Optional<List<GastoCartao>> dadoBanco = gastoRepository.findByDescricao(descriaoGastoCartao);

			if (dadoBanco.isPresent()) {
				Optional<GastoCartao> gastoCartao = dadoBanco.get().stream().filter(c -> c.getIdCategoria() != null).findFirst();
				
				if (gastoCartao.isPresent()) {
					categoriaDTO = categoriaService.obterCategoriaPorId(gastoCartao.get().getIdCategoria().toString());
				}
			}

		} catch (Exception e) {
			log.error("Erro GastoCartaoService.obterCategoriaGastoCartaoPorDescricao", e);
			throw new GastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: obterCategoriaGastoCartaoPorDescricao");
		return categoriaDTO;
	}

}
