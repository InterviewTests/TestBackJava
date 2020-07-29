/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.function;

import java.util.UUID;
import java.util.function.Function;

import br.com.santander.testbackjava.model.dto.CategoriaDTO;
import br.com.santander.testbackjava.model.entity.Categoria;

/**
 * Functions para o objeto Categoria.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 20:22:16
 * @version x.x
 */
public final class CategoriaFunction {

	/**
	 * Atributo CONVERTER_CATEGORIA_TO_DTO
	 */
	public final static Function<Categoria, CategoriaDTO> CONVERTER_CATEGORIA_TO_DTO = new Function<Categoria, CategoriaDTO>() {

		@Override
		public CategoriaDTO apply(Categoria entrada) {
			CategoriaDTO saida = new CategoriaDTO();
			if (entrada != null) {
				saida.setDescricao(entrada.getDescricao());
				saida.setId(entrada.getId());
			}
			return saida;
		}

	};

	/**
	 * Atributo CONVERTER_CATEGORIA_TO_ENTITY
	 */
	public final static Function<CategoriaDTO, Categoria> CONVERTER_CATEGORIA_TO_ENTITY = new Function<CategoriaDTO, Categoria>() {

		@Override
		public Categoria apply(CategoriaDTO entrada) {
			Categoria saida = new Categoria();
			if (entrada != null) {
				if (entrada.getId() != null) {
					saida.setId(entrada.getId());
				}
				saida.setDescricao(entrada.getDescricao());
			}
			return saida;
		}

	};
	
	/**
	 * Atributo CONVERTER_ID_CATEGORIA_TO_DTO
	 */
	public final static Function<UUID, CategoriaDTO> CONVERTER_ID_CATEGORIA_TO_DTO = new Function<UUID, CategoriaDTO>() {

		@Override
		public CategoriaDTO apply(UUID entrada) {
			CategoriaDTO saida = new CategoriaDTO();
			if (entrada != null) {
				saida.setId(entrada);
			}
			return saida;
		}

	};
	
	/**
	 * Atributo CONVERTER_NOME_CATEGORIA_TO_ENTITY
	 */
	public final static Function<String, Categoria> CONVERTER_NOME_CATEGORIA_TO_ENTITY = new Function<String, Categoria>() {

		@Override
		public Categoria apply(String entrada) {
			Categoria saida = new Categoria();
			if (entrada != null) {
				saida.setDescricao(entrada);
			}
			return saida;
		}

	};

}
