package com.company.gestaogastos.domain;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.company.gestaogastos.domain.entity.Categoria;
import com.company.gestaogastos.domain.entity.Gasto;
import com.company.gestaogastos.domain.entity.repository.CategoriaRepository;
import com.company.gestaogastos.domain.entity.repository.GastoRepository;

public class GastoDomain {
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Integer codigousuario;
	private Timestamp data;
	private Categoria categoria;
	
	private GastoRepository gastoRepository;
	private CategoriaRepository categoriaRepository;
	
	static final int GASTOS_PAGE_SIZE = 4;
	
	public GastoDomain(GastoRepository gastoRepository, CategoriaRepository categoriaRepository) {
		this.gastoRepository = gastoRepository;
		this.categoriaRepository = categoriaRepository;
	}
	
	public GastoDomain() {
		super();
	}

	public GastoDomain(Long id, String descricao, BigDecimal valor, Integer codigousuario, Timestamp data,
			Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
		this.data = data;
		this.categoria = categoria;
	}

	public List<Gasto> retrieveAllGastos() {
		return gastoRepository.findAll();
	}

	public Gasto retrieveGasto(long id) {
		Optional<Gasto> gasto = gastoRepository.findById(id);
		if (!gasto.isPresent()) {
			return new Gasto();
			//throw new GastoNotFoundException("id-" + id);
		}
		return gasto.get();
	}

	public Page<Gasto> retrieveGastoByUser(Integer id) {
		PageRequest pageRequest = new PageRequest(0, GASTOS_PAGE_SIZE);
		Page<Gasto> gastos = gastoRepository.findByCodigousuarioOrderByDataDesc(id, pageRequest);
		return gastos;
	}

	public Page<Gasto> retrieveGastoByUserDate(Integer id, String date) {
		Page<Gasto> gastos = Page.empty();
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Date parsedDate = dateFormat.parse(date);
		    Timestamp dataInferior = new Timestamp(parsedDate.getTime());
		    System.out.println("timestamp=" + dataInferior);
		    Timestamp dataSuperior = new Timestamp(parsedDate.getTime() + (24*60*60*1000)-1);
		    System.out.println("timestamp=" + dataSuperior);
			PageRequest pageRequest = new PageRequest(0, GASTOS_PAGE_SIZE);
			gastos = gastoRepository.findByCodigousuarioOrderByDataDesc(id, dataInferior, dataSuperior, pageRequest);
		} catch(Exception e) {
		    // look the origin of excption 
		}
		return gastos;
	}

	public Gasto createGasto(Gasto gasto) {
		if (gasto == null) {
			return null;
		}
		// Verifica se a categoria foi NAO foi inserida no gasto
		if (gasto.getCategoria() != null) {
			if (gasto.getCategoria().getId() != null) {
				// Tenta categorizar automaticamente o gasto baseado na descricao do mesmo
				Optional<Categoria> categoria = categoriaRepository.findById(gasto.getCategoria().getId());
//				if (categoria != null && !categoria.isEmpty())
				if (categoria != null && categoria.isPresent())
					// Pega a categoria do primeiro gasto que tem a mesma descricao
					gasto.setCategoria(categoria.get());;
			} else if (gasto.getCategoria().getNome() != null) {
				// Tenta categorizar automaticamente o gasto baseado na descricao do mesmo
				List<Gasto> gastos = gastoRepository.findByNomeCategoria(gasto.getCodigousuario(), gasto.getCategoria().getNome());
				if (gastos != null && gastos.size() > 0)
					// Pega a categoria do primeiro gasto que tem a mesma descricao
					gasto.setCategoria(gastos.get(0).getCategoria());;
			}
		} else {
			if (gasto.getDescricao() != null) {
				// Tenta categorizar automaticamente o gasto baseado na descricao do mesmo
				List<Gasto> gastos = gastoRepository.findByDescricaoCategoria(gasto.getCodigousuario(), gasto.getDescricao());
				if (gastos != null && gastos.size() > 0)
					// Pega a categoria do primeiro gasto que tem a mesma descricao
					gasto.setCategoria(gastos.get(0).getCategoria());;
			}
		}
		Gasto savedGasto = gastoRepository.save(gasto);

		return savedGasto;
	}
    
	public Gasto updateGasto(Gasto gasto, long id) {
		Optional<Gasto> gastoOptional = gastoRepository.findById(id);
		if (!gastoOptional.isPresent())
			return null;
		gasto.setId(id);
		Gasto gastoBase = gastoRepository.save(gasto);
		return gastoBase;
	}
	
	public void deleteGasto(long id) {
		gastoRepository.deleteById(id);
	}

	public void convertGastoToGastoDomain(Gasto gasto) {
		this.id = gasto.getId();
		this.codigousuario = gasto.getCodigousuario();
		this.descricao = gasto.getDescricao();
		this.categoria = gasto.getCategoria();
		this.data = gasto.getData();
		this.valor = gasto.getValor();
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

	public Integer getCodigousuario() {
		return codigousuario;
	}

	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
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

	@Override
    public String toString() {
        return String.format("Gasto{descricao=%s, valor=%s, codigousuario=%s, data=%s}"
        		, getDescricao(), getValor(), getCodigousuario(), getData());
    }

}