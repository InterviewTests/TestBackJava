package br.com.santander.gastos.service;

import java.util.List;
import java.util.Optional;

import br.com.santander.gastos.model.Gasto;

public interface GastoService {

	List<Gasto> findByIdUsuarioOrderByDataDesc(Long id);

	List<Gasto> findByDate(Long id, String data);

	Optional<Gasto> findById(Long userId, Long gastoId);

	void save(Gasto novoGasto);

}
