package br.com.camaroti.alex.rest.api.expense.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

public @Data class Category {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int cod;
	private String name;
	
	public Category() {
		super();
	}
	
	
	
	
}
