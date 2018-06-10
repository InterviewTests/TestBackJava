package br.com.santander.card.client.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Sale {
	@Id
	@Getter
	private Long id;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	private double valor;
	
	@Getter @Setter
	private long codigousuario;
	
	@Getter @Setter
	private Date data;
	
	@Getter @Setter
	private String categoria;
}
