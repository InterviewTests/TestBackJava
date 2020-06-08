package com.paulo.altran.gasto.dto;

import java.text.ParseException;
import java.time.LocalDateTime;

import com.paulo.altran.gasto.model.Gasto;

import lombok.Data;	

@Data
public class GastoCadastroDTO {

	private String descricao;
	private Double valor;
	private Long codigousuario;		
	private LocalDateTime data;

	public Gasto convertToGasto() throws ParseException {			
		Gasto gasto = Gasto.builder().descricao(this.descricao).valor(this.valor).codigoUsuario(this.codigousuario)
				.data(this.data).build();
		return gasto;
	}
}
