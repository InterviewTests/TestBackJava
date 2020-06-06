package com.teste.gft.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Gasto {

	@Getter
	@Id
	@GeneratedValue
	private Long id;
	@Getter
	@Setter
	private String descricao;
	@Getter
	@Setter
	private Double valor;
	@Getter
	@Setter
	private Long codigoUsuario;
	@Getter
	@Setter
	private LocalDateTime data;
	@Getter
	@Setter
	@ManyToOne
	private Categoria categoria;
}
