package br.com.camaroti.alex.rest.api.expense.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "category")
@AllArgsConstructor
public @Data class Category {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int cod;
	private String name;
	
	public Category() {
		super();
	}
	
	
	
	
}
