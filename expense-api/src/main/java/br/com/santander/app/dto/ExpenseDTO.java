package br.com.santander.app.dto;

import java.time.ZonedDateTime;

public class ExpenseDTO {

	private Long id;
	private String description;
	private double value;
	private Long idUser;
	private ZonedDateTime expenseDate;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(final Long idUser) {
		this.idUser = idUser;
	}

	public ZonedDateTime getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(final ZonedDateTime expenseDate) {
		this.expenseDate = expenseDate;
	}
}
