/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.testbackjava.exception.CategoriaException;
import br.com.santander.testbackjava.exception.GastoCartaoException;
import br.com.santander.testbackjava.function.CategoriaFunction;
import br.com.santander.testbackjava.model.dto.CategoriaDTO;
import br.com.santander.testbackjava.model.entity.Categoria;
import br.com.santander.testbackjava.model.repository.ICategoriaRepository;
import lombok.extern.log4j.Log4j2;

/**
 * Classe responsável por realizar as operações de negócio da entidade Categoria.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 20:38:18
 * @version x.x
 */
@Service
@Log4j2
public class CategoriaService {

	/**
	 * Atributo categoriaRepository
	 */
	@Autowired
	private ICategoriaRepository categoriaRepository;

	/**
	 * Método responsável por listar sugestão de categorias de acordo com a descricao informada.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 20:38:56
	 * @param descricao {@link String} descricao.
	 * @return {@link List<CategoriaDTO>}
	 * @throws CategoriaException @link CategoriaException}
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<CategoriaDTO> obterSugestaoCategoriaByDescricao(String descricao) throws CategoriaException {
		log.info("INICIO - Método: obterSugestaoCategoriaByDescricao");

		List<CategoriaDTO> lista = new ArrayList<CategoriaDTO>();

		try {
			
			//Então uma lista de sugestões de categoria deve ser exibida, estas baseadas em categorias já informadas por outro usuários.

			Optional<List<Categoria>> listaBanco = categoriaRepository.findByDescricaoContains(descricao);

			if (listaBanco.isPresent()) {
				lista.addAll(
						listaBanco.get().stream().map(CategoriaFunction.CONVERTER_CATEGORIA_TO_DTO).collect(Collectors.toList()));
			}

		} catch (Exception e) {
			log.error("Erro CategoriaService.obterSugestaoCategoriaByDescricao", e);
			throw new CategoriaException(e.getMessage(), e);
		}

		log.info("FIM - Método: obterSugestaoCategoriaByDescricao");
		return lista;
	}
	
	/**
	 * Método responsável por incluir categoria.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 21:19:53
	 * @param descricaoCategoria - {@link String} - categoria.
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public CategoriaDTO incluirCategoria(String descricaoCategoria) throws GastoCartaoException {
		log.info("INICIO - Método: incluirCategoria");
		
		CategoriaDTO categoriaDTO = null;

		try {

			Categoria categoria = CategoriaFunction.CONVERTER_NOME_CATEGORIA_TO_ENTITY.apply(descricaoCategoria);

			categoria = categoriaRepository.save(categoria);
			
			categoriaDTO = CategoriaFunction.CONVERTER_CATEGORIA_TO_DTO.apply(categoria);

		} catch (Exception e) {
			log.error("Erro CategoriaService.incluirCategoria", e);
			throw new GastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: incluirCategoria");
		return categoriaDTO;
	}
	
	/**
	 * Método responsável por detalhar categoria.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 20:12:14
	 * @param id {@link String} id.
	 * @return {@link List<CategoriaDTO>}
	 * @throws CategoriaException @link CategoriaException}
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public CategoriaDTO obterCategoriaPorId(String id) throws CategoriaException {
		log.info("INICIO - Método: obterCategoriaPorId");

		CategoriaDTO categoria = new CategoriaDTO();

		try {

			Optional<Categoria> dadoBanco = categoriaRepository.findById(UUID.fromString(id));

			if (dadoBanco.isPresent()) {
				categoria = CategoriaFunction.CONVERTER_CATEGORIA_TO_DTO.apply(dadoBanco.get());
			} else {
				throw new CategoriaException("Detalhe categoria não foi localizado, id: " + id);
			}

		} catch (CategoriaException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error("Erro CategoriaService.obterCategoriaPorId", e);
			throw new CategoriaException(e.getMessage(), e);
		}

		log.info("FIM - Método: obterCategoriaPorId");
		return categoria;
	}
	
	/**
	 * Método responsável por obter categoria pela descricao
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 21:17:21
	 * @param descricao {@link String} descricao.
	 * @return {@link List<CategoriaDTO>}
	 * @throws CategoriaException @link CategoriaException}
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public CategoriaDTO obterCategoriaPorDescricao(String descricao) throws CategoriaException {
		log.info("INICIO - Método: obterCategoriaPorDescricao");

		CategoriaDTO categoria = new CategoriaDTO();

		try {

			Optional<Categoria> dadoBanco = categoriaRepository.findByDescricao(descricao);

			if (dadoBanco.isPresent()) {
				categoria = CategoriaFunction.CONVERTER_CATEGORIA_TO_DTO.apply(dadoBanco.get());
			}

		} catch (Exception e) {
			log.error("Erro CategoriaService.obterCategoriaPorDescricao", e);
			throw new CategoriaException(e.getMessage(), e);
		}

		log.info("FIM - Método: obterCategoriaPorDescricao");
		return categoria;
	}

}
