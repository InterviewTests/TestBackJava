package br.com.santander.gastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.gastos.model.Categoria;
import br.com.santander.gastos.repository.CategoriaRepository;
import br.com.santander.gastos.vo.CategoriaVO;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Override
	public List<CategoriaVO> findByDescricao(String descricao) {
		List<Categoria> listaCategorias = categoriaRepository.findCategoriasByDescricao(descricao);
		
		List<CategoriaVO> listaCategoriasVO = CategoriaVO.converter(listaCategorias);
		
		return listaCategoriasVO;
	}
}
