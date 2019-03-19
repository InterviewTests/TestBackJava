package br.com.adslima.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.adslima.dto.ExpenseManagementQueryDTO;
import br.com.adslima.model.ExpenseCategory;
import br.com.adslima.model.ExpenseManagement;

/**
 * 
 * @author andrews.silva
 *
 */
public class ExpenseManagementConvert {

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public static ExpenseManagement convertToModel(final ExpenseManagementQueryDTO dto) {

		final ExpenseManagement cardExpense = new ExpenseManagement();
		final ExpenseCategory category = new ExpenseCategory(dto.getCategory().toString());

		cardExpense.setCategory(category);
		cardExpense.setId(dto.getId());
		cardExpense.setDescription(dto.getDescription());
		cardExpense.setUserCode(dto.getUserCode());
		cardExpense.setDate(dto.getDate());
		cardExpense.setValue(dto.getValue());

		return cardExpense;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public static ExpenseManagementQueryDTO toConvertFromModelSummary(final ExpenseManagement model) {
		final ExpenseManagementQueryDTO communsDto = new ExpenseManagementQueryDTO();
		communsDto.setId(model.getId());
		if (model.getCategory() != null) {
			communsDto.setCategory(model.getCategory());
		}
		communsDto.setDescription(model.getDescription());
		communsDto.setUserCode(model.getUserCode());
		communsDto.setDate(model.getDate());
		communsDto.setValue(model.getValue());
		return communsDto;
	}

	public static List<ExpenseManagementQueryDTO> toConvertFromModel(final List<ExpenseManagement> listModel) {
		return listModel.stream().map(ExpenseManagementConvert::toConvertFromModelSummary).collect(Collectors.toList());
	}

}