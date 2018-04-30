package br.com.santander.app.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "EXPENSE")
public class Expense implements Serializable{

	private static final long serialVersionUID = -8507622473380945770L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_EXPENSE", precision = 12, scale = 0)
	private Long id;

	@Column(name = "VALUE", nullable = false)
	private double value;

	@Column(name = "EXPENSE_DATE", nullable = false)
	private LocalDateTime expenseDate;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@ManyToOne(optional =  true)
	@JoinColumn(name = "ID_CATEGORY", referencedColumnName="ID_CATEGORY", nullable = true)
	private Category category;

	@ManyToOne(optional =  false)
	@JoinColumn(name = "ID_USER", referencedColumnName="ID_USER", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

	public LocalDateTime getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(final LocalDateTime expenseDate) {
		this.expenseDate = expenseDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}
}
