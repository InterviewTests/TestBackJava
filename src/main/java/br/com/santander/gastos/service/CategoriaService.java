package br.com.santander.gastos.service;

import java.util.List;

import br.com.santander.gastos.vo.CategoriaVO;


public interface CategoriaService {

	List<CategoriaVO> findByDescricao(String descricao);

}
