package br.com.santander.app.dto;

import org.springframework.hateoas.ResourceSupport;

public class CategoryDTO extends ResourceSupport{

	private Long code;
	private String description;

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
}
