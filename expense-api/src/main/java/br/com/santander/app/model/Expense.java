package br.com.santander.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EXPENSE")
public class Expense implements Serializable{

	private static final long serialVersionUID = -8507622473380945770L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "DESCRIPTION", nullable = false, length = 200)
	private String description;

	@Column(name = "VALUE", nullable = false)
	private double value;

	@Column(name = "USER_CODE", nullable = false)
	private Integer userCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPENSE_DATE", nullable = false)
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
