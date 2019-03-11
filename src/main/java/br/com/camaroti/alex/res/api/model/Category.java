package br.com.camaroti.alex.res.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Category {
	
	public Category() {
		
	}
	
	public Category(String name) {
		this.name = name;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int cod;
	private String name;
}
