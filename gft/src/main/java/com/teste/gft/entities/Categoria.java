package com.teste.gft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Categoria {
	@Getter
	@Id
	@GeneratedValue
	private Long id;
	
	@Getter
	@Setter
	private String nome;
}
