package br.com.santander.app.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

public class ExpenseDTO extends ResourceSupport{

	private Long code;
	private String description;
	private double value;
	private Long idUser;
	private LocalDateTime expenseDate;
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(final Long code) {
		this.code = code;
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

	public LocalDateTime getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(final LocalDateTime expenseDate) {
		this.expenseDate = expenseDate;
	}
}
