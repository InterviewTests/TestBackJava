package com.santander.service;

import java.util.List;

import com.santander.model.Gasto;
import com.santander.repository.filter.GastoFilter;

public interface IGastoService {

	public Gasto salvar(Gasto gasto);

	public List<Gasto> filtrar(GastoFilter filter, int codigoUsuario);

	public Gasto alterarCategoria(Long idGasto, String categoria);

}
