package br.com.camaroti.alex.res.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Gasto {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	private String descricao;
	private double valor;
	private int codigousuario;
	private Date data;
}
