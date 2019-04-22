package br.com.camaroti.alex.rest.api.expense.builder;

import java.util.Date;

import br.com.camaroti.alex.rest.api.expense.domain.Category;
import br.com.camaroti.alex.rest.api.expense.domain.Expense;

public class ExpenseBuilder {

	public static final int DEFAULT_COD  = 1;
	public static final int DEFAULT_CODUSER  = 1;
	public static final Date DEFAULT_DATE  = new Date();
	public static final String DEFAULT_DESCRIPTION  = "Expense Description Test";
	public static final double DEFAULT_VALUE  = 20.5;
	public static final Category DEFAULT_CATEGORY  = CategoryBuilder.aCategory().build();
	
    private int cod = DEFAULT_COD;
    private int codUser = DEFAULT_CODUSER;
    private Date date = DEFAULT_DATE;
    private String description = DEFAULT_DESCRIPTION;
    private double value = DEFAULT_VALUE;
    private Category category = DEFAULT_CATEGORY;

	public static ExpenseBuilder anExpense() {
    	return new ExpenseBuilder();
    }
	
    public ExpenseBuilder withCod(int cod) {
        this.cod = cod;
        return this;
    }
    
    public ExpenseBuilder withCodUser(int codUser) {
        this.codUser = codUser;
        return this;
    }
    
    public ExpenseBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
    
    public ExpenseBuilder withValue(double value) {
        this.value = value;
        return this;
    }
    
    public ExpenseBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }
    
    public ExpenseBuilder withoutCategory() {
        this.category = null;
        return this;
    }
    
    public Expense build() {
    	Expense e = new Expense();
    	e.setCod(cod);
    	e.setCodUser(codUser);
    	e.setDate(date);
    	e.setDescription(description);
    	e.setValue(value);
    	e.setCategory(category);
    	return e;
    }
    
	
}
