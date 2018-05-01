package br.com.santander.app.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

public class ExpenseDTO extends ResourceSupport{

	private Long code;
	private String description;
	private double value;
	private Long userCode;
	private LocalDateTime date;
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

	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(final Long userCode) {
		this.userCode = userCode;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(final LocalDateTime date) {
		this.date = date;
	}
}
