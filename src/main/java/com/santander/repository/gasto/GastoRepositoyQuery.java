package com.santander.repository.gasto;

import java.util.List;

import com.santander.model.Gasto;
import com.santander.repository.filter.GastoFilter;

public interface GastoRepositoyQuery {
	public List<Gasto> filtrar(GastoFilter gastoFilter, int codigoUsuario);
}
