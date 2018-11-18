package com.company.gestaogastos.domain;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.company.gestaogastos.domain.dto.CategoriaDTO;
import com.company.gestaogastos.domain.dto.GastoDTO;
import com.company.gestaogastos.domain.dto.UsuarioDTO;
import com.company.gestaogastos.domain.repository.CategoriaRepository;
import com.company.gestaogastos.domain.repository.GastoRepository;

@Entity
public class Gasto {
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Timestamp data;
	
//	@ManyToOne
//    //@JoinColumn(name="fk_usuariogasto")
//	private Usuario usuario;
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	private Categoria categoria;
	
	@Transient
	private GastoRepository gastoRepository;
	@Transient
	private CategoriaRepository categoriaRepository;
	
	static final int GASTOS_PAGE_SIZE = 4;
	
	public Gasto(GastoRepository gastoRepository, CategoriaRepository categoriaRepository) {
		this.gastoRepository = gastoRepository;
		this.categoriaRepository = categoriaRepository;
	}
	
	public Gasto() {
		super();
	}

	public Gasto(Long id, String descricao, BigDecimal valor, Timestamp data, Usuario usuario, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.usuario = usuario;
		this.categoria = categoria;
	}

	public Page<GastoDTO> retrieveAllGastos(PageRequest pageRequest) {
		return convertPageGastoToPageGastoDTO(gastoRepository.findAllGastos(pageRequest));
	}
//	public List<Gasto> retrieveAllGastos() {
//		return gastoRepository.findAll();
//	}

	public Page<Gasto> retrieveGastos(Map<String, String> allRequestParams) {
		PageRequest pageRequest = getPageRequest(allRequestParams);
		if (this.getUsuario() != null && this.getUsuario().getId() != null) {
			Long codusuario = this.getUsuario().getId();
			if (this.getData() != null) {
				return retrieveGastoByUserDate(codusuario, allRequestParams.get("data"), pageRequest);
			} else {
				return retrieveGastoByUser(codusuario, pageRequest);
			}
		}
		return gastoRepository.findAllGastos(pageRequest);
	}

	public Gasto retrieveGasto() {
		Optional<Gasto> gasto = gastoRepository.findById(this.id);
		if (!gasto.isPresent()) {
			return null;
		}
		return gasto.get();
	}

	public Page<Gasto> retrieveGastoByUser(Long id, PageRequest pageRequest) {
		Page<Gasto> gastos = gastoRepository.findByUsuarioIdOrderByDataDesc(id, pageRequest);
		return gastos;
	}

	public Page<Gasto> retrieveGastoByUserDate(Long id, String date, PageRequest pageRequest) {
		Page<Gasto> gastos = Page.empty();
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Date parsedDate = dateFormat.parse(date);
		    Timestamp dataInferior = new Timestamp(parsedDate.getTime());
		    System.out.println("timestamp=" + dataInferior);
		    Timestamp dataSuperior = new Timestamp(parsedDate.getTime() + (24*60*60*1000)-1);
		    System.out.println("timestamp=" + dataSuperior);
			gastos = gastoRepository.findByCodigousuarioOrderByDataDesc(id, dataInferior, dataSuperior, pageRequest);
		} catch(Exception e) {
		    // look the origin of excption 
		}
		return gastos;
	}

	public Gasto createGasto() {
		// Verifica se a categoria foi NAO foi inserida no gasto
		if (this.getCategoria() != null) {
			if (this.getCategoria().getId() != null) {
				// Tenta categorizar automaticamente o gasto baseado na descricao do mesmo
				Optional<Categoria> categoria = categoriaRepository.findById(this.getCategoria().getId());
//				if (categoria != null && !categoria.isEmpty())
				if (categoria != null && categoria.isPresent())
					// Pega a categoria do primeiro gasto que tem a mesma descricao
					this.setCategoria(categoria.get());;
			} else if (this.getCategoria().getNome() != null) {
				// Tenta categorizar automaticamente o gasto baseado na descricao do mesmo
				List<Gasto> gastos = gastoRepository.findByNomeCategoria(
						this.getUsuario().getId(), this.getCategoria().getNome(), 
							PageRequest.of(0, GASTOS_PAGE_SIZE, new Sort(Sort.Direction.DESC,"data"))).getContent();
				if (gastos != null && gastos.size() > 0)
					// Pega a categoria do primeiro gasto que tem a mesma descricao
					this.setCategoria(gastos.get(0).getCategoria());;
			}
		} else {
			if (this.getDescricao() != null) {
				// Tenta categorizar automaticamente o gasto baseado na descricao do mesmo
				List<Gasto> gastos = gastoRepository.findByDescricaoCategoria(
						this.getUsuario().getId(), this.getDescricao(), 
							PageRequest.of(0, GASTOS_PAGE_SIZE, new Sort(Sort.Direction.DESC,"data"))).getContent();
				if (gastos != null && gastos.size() > 0)
					// Pega a categoria do primeiro gasto que tem a mesma descricao
					this.setCategoria(gastos.get(0).getCategoria());;
			}
		}
		Gasto savedGasto = gastoRepository.save(this);

		return savedGasto;
	}
    
	public Gasto updateGasto() {
		Optional<Gasto> gastoOptional = gastoRepository.findById(this.getId());
		if (!gastoOptional.isPresent())
			return null;
		Gasto savedGasto = gastoRepository.save(this);
		return savedGasto;
	}
	
	public void deleteGasto() {
		gastoRepository.deleteById(this.getId());
	}

	public void convertGastoDTOToGasto(GastoDTO gasto) {
		this.id = gasto.getId();
		this.descricao = gasto.getDescricao();
		this.data = gasto.getData();
		this.valor = gasto.getValor();
		if (gasto.getCategoria() == null) {
			this.categoria = null;
		} else {
			this.categoria = new Categoria(gasto.getCategoria().getId(), gasto.getCategoria().getNome());
		}
		if (gasto.getUsuario() == null) {
			this.usuario = null;
		} else {
			this.usuario = new Usuario(gasto.getUsuario().getId(), gasto.getUsuario().getNome());
		}
	}

	public GastoDTO convertGastoToGastoDTO() {
		GastoDTO gastoDTO = new GastoDTO();
		gastoDTO.setId(this.id);
		gastoDTO.setDescricao(this.descricao);
		gastoDTO.setData(this.data);
		gastoDTO.setValor(this.valor);
		if (this.categoria == null) {
			gastoDTO.setCategoria(null);
		} else {
			gastoDTO.setCategoria(new CategoriaDTO(this.categoria.getId(), this.categoria.getNome()));
		}
		if (this.usuario == null) {
			gastoDTO.setUsuario(null);
		} else {
			gastoDTO.setUsuario(new UsuarioDTO(this.usuario.getId(), this.usuario.getNome()));
		}
		
		return gastoDTO;
	}
	
	public GastoDTO convertGastoToGastoDTO(Gasto gasto) {
		GastoDTO gastoDTO = new GastoDTO();
		gastoDTO.setId(gasto.getId());
		gastoDTO.setDescricao(gasto.getDescricao());
		gastoDTO.setData(gasto.getData());
		gastoDTO.setValor(gasto.getValor());
		if (gasto.getCategoria() == null) {
			gastoDTO.setCategoria(null);
		} else {
			gastoDTO.setCategoria(new CategoriaDTO(gasto.getCategoria().getId(), gasto.getCategoria().getNome()));
		}
		return gastoDTO;
	}
	
	public Page<GastoDTO> convertPageGastoToPageGastoDTO(Page<Gasto> gastos) {
		List<GastoDTO> gastoDTOList = new ArrayList<>();
		gastos.getContent().forEach(gasto-> {
			gastoDTOList.add(convertGastoToGastoDTO(gasto));
		});
		Page<GastoDTO> pageGastoDTO = new PageImpl<GastoDTO>(gastoDTOList, gastos.getPageable(), gastos.getContent().size());
		return pageGastoDTO;
	}

	public Page<GastoDTO> convertPageGastoToPageGastoDTO2(Page<Gasto> gastos) {
		Page<GastoDTO> dtoPage = gastos.map(new Function<Gasto, GastoDTO>() {
		    @Override
		    public GastoDTO apply(Gasto entity) {
		    	GastoDTO dto = convertGastoToGastoDTO(entity);
		        return dto;
		    }
		});
		return dtoPage;
	}

	public PageRequest getPageRequest(Map<String, String> allRequestParams) {
		Integer offset = 0;
		Integer limit = GASTOS_PAGE_SIZE;
		if (allRequestParams.get("offset") != null) {
			offset = Integer.decode(allRequestParams.get("offset"));
		}
		if (allRequestParams.get("limit") != null) {
			limit = Integer.decode(allRequestParams.get("limit"));
		}
		return PageRequest.of(offset, limit, new Sort(Sort.Direction.DESC,"data"));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
    public String toString() {
//        return String.format("Gasto{descricao=%s, valor=%s, codigousuario=%s, data=%s}"
//        		, getDescricao(), getValor(), getUsuario(), getData());
		return null;
    }

}