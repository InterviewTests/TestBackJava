package com.company.gestaogastos.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.company.gestaogastos.domain.dto.CategoriaDTO;
import com.company.gestaogastos.domain.repository.CategoriaRepository;

public class Categoria {
	private Long id;
	private String nome;
	private List<Gasto> gastos = new ArrayList<Gasto>();
	private CategoriaRepository categoriaRepository;
	
	static final int CATEGORIAS_PAGE_SIZE = 4;

	public Categoria() {
		super();
	}

	public Categoria(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public Categoria(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public com.company.gestaogastos.domain.entity.Categoria retrieveCategoria() {
		Optional<com.company.gestaogastos.domain.entity.Categoria> categoria = categoriaRepository.findById(this.getId());

		return categoria.get();
	}

	public Page<com.company.gestaogastos.domain.entity.Categoria> retrieveCategorias(Map<String, String> allRequestParams) {
		Page<com.company.gestaogastos.domain.entity.Categoria> categorias;
		PageRequest pageRequest = getPageRequest(allRequestParams);
		if (allRequestParams.get("nome") != null) {
			String nomeParam = allRequestParams.get("nome");
			categorias = categoriaRepository.findByNome(nomeParam, pageRequest);
		} else {
			categorias = categoriaRepository.findAllCategoria(pageRequest);
		}
		return categorias;
	}

	public void deleteCategoria() {
		categoriaRepository.deleteById(this.getId());
	}

	public com.company.gestaogastos.domain.entity.Categoria createCategoria(com.company.gestaogastos.domain.entity.Categoria categoria) {
		com.company.gestaogastos.domain.entity.Categoria savedCategoria = categoriaRepository.save(categoria);

		return savedCategoria;
	}
	
	public com.company.gestaogastos.domain.entity.Categoria updateCategoria(com.company.gestaogastos.domain.entity.Categoria categoria) {

		Optional<com.company.gestaogastos.domain.entity.Categoria> categoriaOptional = categoriaRepository.findById(categoria.getId());

		if (!categoriaOptional.isPresent())
			return null;

		com.company.gestaogastos.domain.entity.Categoria categoriaBanco = categoriaRepository.save(categoria);

		return categoriaBanco;
	}

	public PageRequest getPageRequest(Map<String, String> allRequestParams) {
		Integer offset = 0;
		Integer limit = CATEGORIAS_PAGE_SIZE;
		if (allRequestParams.get("offset") != null) {
			offset = Integer.decode(allRequestParams.get("offset"));
		}
		if (allRequestParams.get("limit") != null) {
			limit = Integer.decode(allRequestParams.get("limit"));
		}
		return PageRequest.of(offset, limit, new Sort(Sort.Direction.ASC,"nome"));
	}
	
	public void toDomain(CategoriaDTO categoria) {
		if (categoria != null) {
			this.id = categoria.getId();
			this.nome = categoria.getNome();
		}
	}

	public com.company.gestaogastos.domain.entity.Categoria toEntity(Categoria categoria) {
		com.company.gestaogastos.domain.entity.Categoria entity = null;
		if (categoria != null) {
			entity = new com.company.gestaogastos.domain.entity.Categoria();
			entity.setId(categoria.getId());
			entity.setNome(categoria.getNome());
		}
		return entity;
	}

	public com.company.gestaogastos.domain.entity.Categoria toEntity(CategoriaDTO categoria) {
		com.company.gestaogastos.domain.entity.Categoria entity = null;
		if (categoria != null) {
			entity = new com.company.gestaogastos.domain.entity.Categoria();
			entity.setId(categoria.getId());
			entity.setNome(categoria.getNome());
		}
		return entity;
	}

	public CategoriaDTO toDTO(Categoria categoria) {
		CategoriaDTO dto = null;
		if (categoria != null) {
			dto = new CategoriaDTO();
			dto.setId(categoria.getId());
			dto.setNome(categoria.getNome());
		}
		return dto;
	}

	public CategoriaDTO toDTO(com.company.gestaogastos.domain.entity.Categoria categoria) {
		CategoriaDTO dto = null;
		if (categoria != null) {
			dto = new CategoriaDTO();
			dto.setId(categoria.getId());
			dto.setNome(categoria.getNome());
		}
		return dto;
	}

	public Page<CategoriaDTO> convertPageCategoriaToPageCategoriaDTO(Page<com.company.gestaogastos.domain.entity.Categoria> categorias) {
		List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
		categorias.getContent().forEach(categoria-> {
			categoriaDTOList.add(toDTO(categoria));
		});
		Page<CategoriaDTO> pageCategoriaDTO = new PageImpl<CategoriaDTO>(categoriaDTOList, categorias.getPageable(), categorias.getContent().size());
		return pageCategoriaDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	@Override
    public String toString() {
        return String.format("Categoria{id=%s, nome=%s}"
        		, getId(), getNome());
    }

}