package br.com.santander.app.dto;

import java.util.Date;

public class ExpenseDTO {

	private Long id;
	private String description;
	private double value;
	private Integer userCode;
	private Date expenseDate;

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
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(final Integer userCode) {
		this.userCode = userCode;
	}
	public Date getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(final Date expenseDate) {
		this.expenseDate = expenseDate;
	}
}
