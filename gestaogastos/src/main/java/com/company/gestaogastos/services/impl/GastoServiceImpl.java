package com.company.gestaogastos.services.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.company.gestaogastos.domain.Gasto;
import com.company.gestaogastos.domain.Usuario;
import com.company.gestaogastos.domain.dto.GastoDTO;
import com.company.gestaogastos.domain.repository.CategoriaRepository;
import com.company.gestaogastos.domain.repository.GastoRepository;
import com.company.gestaogastos.services.GastoService;

@Service
public class GastoServiceImpl implements GastoService {

	@Autowired
	private GastoRepository gastoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Page<GastoDTO> retrieveGastos(Map<String, String> allRequestParams) {
		Gasto gasto = new Gasto(gastoRepository, categoriaRepository);
		if (allRequestParams.get("codusuario") != null) {
			Long usuarioId = Long.decode(allRequestParams.get("codusuario"));
			gasto.setUsuario(new Usuario(usuarioId, null));
		}
		if (allRequestParams.get("data") != null) {
			String data = allRequestParams.get("data");
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Date parsedDate;
			try {
				parsedDate = dateFormat.parse(data);
				gasto.setData(new Timestamp(parsedDate.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Page<GastoDTO> gastos = gasto.convertPageGastoToPageGastoDTO(gasto.retrieveGastos(allRequestParams));
		return gastos;
	}

	@Override
	public GastoDTO retrieveGasto(long id) {
		Gasto gasto = new Gasto(gastoRepository, categoriaRepository);
		gasto.setId(id);
		Gasto gastoBase = gasto.retrieveGasto();
		return gasto.convertGastoToGastoDTO(gastoBase);
	}

	@Override
	public GastoDTO createGasto(GastoDTO gastoDTO) {
		Gasto gasto = new Gasto(gastoRepository, categoriaRepository);
		gasto.convertGastoDTOToGasto(gastoDTO);
		Gasto gastoBase = gasto.createGasto();
		return gasto.convertGastoToGastoDTO(gastoBase);
	}
    
	@Override
	public GastoDTO updateGasto(GastoDTO gastoDTO, long id) {
		Gasto gasto = new Gasto(gastoRepository, categoriaRepository);
		gasto.convertGastoDTOToGasto(gastoDTO);
		Gasto gastoBase = gasto.updateGasto();
		return gasto.convertGastoToGastoDTO(gastoBase);
	}

	@Override
	public void deleteGasto(long id) {
		Gasto gasto = new Gasto(gastoRepository, categoriaRepository);
		gasto.setId(id);
		gasto.deleteGasto();
	}
	
	@Override
	public boolean equals(Object o) {
		return true;
	}

}