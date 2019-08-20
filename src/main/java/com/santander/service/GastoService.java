package com.santander.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.santander.model.Gasto;
import com.santander.repository.GastoRepositoy;
import com.santander.repository.filter.GastoFilter;

@Service
public class GastoService implements IGastoService {

	@Autowired
	private GastoRepositoy gastosRepository;

	public GastoService(GastoRepositoy gastoRepository) {
		this.gastosRepository = gastoRepository;
	}

	@Override
	public Gasto salvar(Gasto gasto) {
		return gastosRepository.save(gasto);
	}

	@Override
	public List<Gasto> filtrar(GastoFilter filter, int codigoUsuario) {
		return gastosRepository.filtrar(filter, codigoUsuario);
	}

	@Override
	public Gasto alterarCategoria(Long idGasto, String categoria) {

		Gasto gastoSalvo = this.gastosRepository.findById(idGasto)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		if (!StringUtils.isEmpty(gastoSalvo.getCategoria())) {
			throw new IllegalArgumentException("voce nao pode alterar esse campo");
		}

		gastoSalvo.setCategoria(categoria);
		return gastosRepository.save(gastoSalvo);
	}

}
