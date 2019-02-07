package br.com.gestao.gastos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.gestao.gastos.model.Categorias;
import br.com.gestao.gastos.model.Gastos;
import br.com.gestao.gastos.repository.CategoriasRepository;
import br.com.gestao.gastos.repository.GastosRepository;

@Service
public class GastosServiceImpl implements GastosService {

	private GastosRepository gastosRepository;
	private CategoriasRepository categeoriasRepository;

	@Autowired
	public GastosServiceImpl(GastosRepository gastosRepository, CategoriasRepository categoriasRepository) {
		this.gastosRepository = gastosRepository;
		this.categeoriasRepository = categoriasRepository;
	}

	@Override
	public List<Gastos> listaDeGastos(int codigousuario, String data) {
		List<Gastos> gastos = new ArrayList<>();
		if (data == null) {
			gastos = gastosRepository.findAllByCodigoUsuario(codigousuario, new Sort(Direction.DESC, "data"));
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataInicio = LocalDate.parse(data, formatter);
			LocalDate dataFim = dataInicio.plusDays(1);
			gastos = gastosRepository.findAllByCodigoUsuarioData(codigousuario, dataInicio, dataFim,
					new Sort(Direction.DESC, "data"));
		}
		return gastos;
	}

	@Override
	public Gastos detalheGasto(int codigousuario, ObjectId id) {
		Gastos gastos = new Gastos();
		gastos = gastosRepository.findByIdAndCodigoUsuario(id, codigousuario);
		return gastos;
	}

	@Override
	public Gastos criarGasto(Gastos gastos) {
		gastos.setId(ObjectId.get());
		// gastos.setData(LocalDateTime.now());
		return validaGasto(gastos);
	}

	@Override
	public Gastos alterarGasto(Gastos gastos) {
		return validaGasto(gastos);
	}

	public Gastos validaGasto(Gastos gastos) {
		Categorias categoria = new Categorias();
		categoria = getCategoria(gastos);
		if (categoria != null && categoria.getCategoria().equals(gastos.getDescricao())) {
			gastos.setIdCategoria(new ObjectId(categoria.getId()));
		} else {
			if (gastos.getDescricao() != null) {
				categoria = new Categorias();
				ObjectId idCategoria = ObjectId.get();
				categoria.setId(idCategoria);
				categoria.setCategoria(gastos.getDescricao());
				categeoriasRepository.save(categoria);
				gastos.setIdCategoria(idCategoria);
			}
		}
		gastosRepository.save(gastos);
		return null;
	}

	public Categorias getCategoria(Gastos gastos) {
		return categeoriasRepository.findByNomeCategoria(gastos.getDescricao());
	}

}
