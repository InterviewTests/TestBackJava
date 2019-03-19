package br.com.adslima.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.adslima.model.ExpenseCategory;


/**
 * @author andrews.silva
 *
 */
@Converter
public class ExpenseManagementCategoryConverter implements AttributeConverter<ExpenseCategory, String> {

	public String convertToDatabaseColumn(ExpenseCategory category) {
		return category == null ? null : category.toString();
	}

	public ExpenseCategory convertToEntityAttribute(String category) {
		return category == null ? null : ExpenseCategory.of(category);
	}

}
