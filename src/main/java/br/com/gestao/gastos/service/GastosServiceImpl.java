package br.com.gestao.gastos.service;

import java.time.LocalDateTime;
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
	public List<Gastos> listaDeGastos(int codigousuario) {
		List<Gastos> gastos = gastosRepository.findAllByCodigoUsuario(codigousuario, new Sort(Direction.DESC, "_id"));
		return gastos;
	}

	@Override
	public Gastos save(Gastos gastos) {
		gastos.setId(ObjectId.get());
		gastos.setData(LocalDateTime.now());
		Categorias categoria = new Categorias();
		categoria = getCategoria(gastos);
		if(categoria != null && categoria.getCategoria().equals(gastos.getDescricao())){
			gastos.setIdCategoria(new ObjectId(categoria.getId()));
		}else{
			if(gastos.getDescricao() != null){
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
	
	public Categorias getCategoria(Gastos gastos){
		return categeoriasRepository.findByNomeCategoria(gastos.getDescricao());
	}

}
