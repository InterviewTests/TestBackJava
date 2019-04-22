package br.com.camaroti.alex.rest.api.expense.builder;

import br.com.camaroti.alex.rest.api.expense.domain.Category;

public class CategoryBuilder {

	public static final int DEFAULT_COD = 1;
	public static final String DEFAULT_NAME = "Category Name";
    
	private int cod = DEFAULT_COD;
	private String name = DEFAULT_NAME;
    
    public static CategoryBuilder aCategory() {
    	return new CategoryBuilder();
    }
    
    public CategoryBuilder withCod(int cod) {
        this.cod = cod;
        return this;
    }
    
    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }
    
    public Category build() {
    	Category c = new Category();
    	c.setCod(cod);
    	c.setName(name);
    	return c;
    }
    
}
