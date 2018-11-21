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
import com.company.gestaogastos.domain.entity.Categoria;
import com.company.gestaogastos.domain.repository.CategoriaRepository;

public class CategoriaDomain {
	private Long id;
	private String nome;
	private List<GastoDomain> gastos = new ArrayList<GastoDomain>();
	private CategoriaRepository categoriaRepository;
	
	static final int CATEGORIAS_PAGE_SIZE = 4;

	public CategoriaDomain() {
		super();
	}

	public CategoriaDomain(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public CategoriaDomain(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Categoria retrieveCategoria() {
		Optional<Categoria> categoria = categoriaRepository.findById(this.getId());

		return categoria.get();
	}

	public Page<Categoria> retrieveCategorias(Map<String, String> allRequestParams) {
		Page<Categoria> categorias;
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

	public Categoria createCategoria(Categoria categoria) {
		Categoria savedCategoria = categoriaRepository.save(categoria);

		return savedCategoria;
	}
	
	public Categoria updateCategoria(Categoria categoria) {

		Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoria.getId());

		if (!categoriaOptional.isPresent())
			return null;

		Categoria categoriaBanco = categoriaRepository.save(categoria);

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
	
	public Categoria toEntity(CategoriaDomain categoria) {
		Categoria entity = null;
		if (categoria != null) {
			entity = new Categoria();
			entity.setId(categoria.getId());
			entity.setNome(categoria.getNome());
		}
		return entity;
	}

	public Categoria toEntity(CategoriaDTO categoria) {
		Categoria entity = null;
		if (categoria != null) {
			entity = new Categoria();
			entity.setId(categoria.getId());
			entity.setNome(categoria.getNome());
		}
		return entity;
	}

	public CategoriaDTO toCategoriaDTO(Categoria categoria) {
		CategoriaDTO dto = null;
		if (categoria != null) {
			dto = new CategoriaDTO();
			dto.setId(categoria.getId());
			dto.setNome(categoria.getNome());
		}
		return dto;
	}

	public Page<CategoriaDTO> toPageCategoriaDTO(Page<Categoria> categorias) {
		List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
		categorias.getContent().forEach(categoria-> {
			categoriaDTOList.add(toCategoriaDTO(categoria));
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

	public List<GastoDomain> getGastos() {
		return gastos;
	}

	public void setGastos(List<GastoDomain> gastos) {
		this.gastos = gastos;
	}

	@Override
    public String toString() {
        return String.format("Categoria{id=%s, nome=%s}"
        		, getId(), getNome());
    }

}