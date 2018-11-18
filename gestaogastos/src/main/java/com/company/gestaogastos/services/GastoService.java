package com.company.gestaogastos.services;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.gestaogastos.domain.dto.GastoDTO;

public interface GastoService {

	public Page<GastoDTO> retrieveGastos(Map<String, String> allRequestParams);

	public GastoDTO retrieveGasto(long id);

	public GastoDTO createGasto(GastoDTO gasto);
	
	public GastoDTO updateGasto(GastoDTO gasto, long id);

	public void deleteGasto(long id);

}