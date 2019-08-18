package com.santander.repository;

import com.santander.model.Gasto;

public interface GastoRepositoy {

	public Gasto save(Gasto gasto);

	public Integer count();


}
