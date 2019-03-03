package br.com.camaroti.alex.res.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
public @Data class Expense {
	
	public Expense() {
		
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int cod;
	private String description;
	private double value;
	private int codUser;
	private Date date;
}
