package br.com.santander.app.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.santander.app.converter.ExpenseConverter;
import br.com.santander.app.data.repository.CategoryRepository;
import br.com.santander.app.data.repository.ExpenseRepository;
import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.exception.OptimisticLockException;
import br.com.santander.app.model.Category;
import br.com.santander.app.model.Expense;
import br.com.santander.app.objectfactory.CategoryMother;
import br.com.santander.app.objectfactory.ExpenseMother;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseServiceImplUnitTest {

	@InjectMocks
	private ExpenseServiceImpl expenseServiceImpl;

	@Mock
	private ExpenseRepository expenseRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void insertWithExistingCategoryTest() {
		final ExpenseDTO dto =ExpenseMother.getExpenseDTOPattern();
		final Category category= CategoryMother.getCategoryModelWithIdPattern();

		final Expense expenseModel= ExpenseConverter.fromDTO(dto);
		expenseModel.setCategory(category);

		Mockito.when(categoryRepository.findByDescriptionEqualsIgnoreCase(dto.getDescription())).thenReturn(category);
		Mockito.when(expenseRepository.save(expenseModel)).thenReturn(expenseModel);

		final ExpenseDTO dtoReturn = expenseServiceImpl.insert(dto);
		Assert.assertEquals(expenseModel, ExpenseConverter.fromDTO(dtoReturn));
		Assert.assertEquals(expenseModel.getCategory().getDescription(), ExpenseConverter.fromDTO(dtoReturn).getCategory().getDescription());
	}

	@Test
	public void insertWithCategoryNonexistentTest() {
		final ExpenseDTO dto =ExpenseMother.getExpenseDTOPattern();
		final Category categoryWithoutId= CategoryMother.getCategoryModelwithoutIdPattern();
		final Category categoryWithId= CategoryMother.getCategoryModelWithIdPattern();

		final Expense expenseModel= ExpenseConverter.fromDTO(dto);
		expenseModel.setCategory(categoryWithId);

		Mockito.when(categoryRepository.findByDescriptionEqualsIgnoreCase(dto.getDescription())).thenReturn(null);
		Mockito.when(categoryRepository.save(categoryWithoutId)).thenReturn(categoryWithId);
		Mockito.when(expenseRepository.save(expenseModel)).thenReturn(expenseModel);

		final ExpenseDTO dtoReturn = expenseServiceImpl.insert(dto);
		Assert.assertEquals(expenseModel, ExpenseConverter.fromDTO(dtoReturn));
		Assert.assertEquals(expenseModel.getCategory().getDescription(), ExpenseConverter.fromDTO(dtoReturn).getCategory().getDescription());
	}

	@Test
	public void updateWithExistingCategoryTest() {
		final ExpenseDTO dto =ExpenseMother.getExpenseDTOPattern();
		final Category category= CategoryMother.getCategoryModelWithIdPattern();

		final Expense expenseModel= ExpenseConverter.fromDTO(dto);
		expenseModel.setCategory(category);
		final Optional<Expense> optionalExpense = Optional.of(expenseModel);

		Mockito.when(categoryRepository.findByDescriptionEqualsIgnoreCase(dto.getDescription())).thenReturn(category);
		Mockito.when(expenseRepository.save(expenseModel)).thenReturn(expenseModel);
		Mockito.when(expenseRepository.findById(expenseModel.getId())).thenReturn(optionalExpense);

		final ExpenseDTO dtoReturn = expenseServiceImpl.update(dto);
		Assert.assertEquals(expenseModel, ExpenseConverter.fromDTO(dtoReturn));
		Assert.assertEquals(expenseModel.getCategory().getDescription(), ExpenseConverter.fromDTO(dtoReturn).getCategory().getDescription());
	}

	@Test
	public void updateWithExistingCategoryAndOptimisticLockErrorTest() {
		exception.expect(OptimisticLockException.class);
		final ExpenseDTO dto =ExpenseMother.getExpenseDTOPattern();

		final Category category= CategoryMother.getCategoryModelWithIdPattern();

		final Expense expenseModel= ExpenseConverter.fromDTO(dto);
		expenseModel.setCategory(category);
		final Optional<Expense> optionalExpense = Optional.of(expenseModel);

		dto.setVersion(1);
		Mockito.when(categoryRepository.findByDescriptionEqualsIgnoreCase(dto.getDescription())).thenReturn(category);
		Mockito.when(expenseRepository.findById(expenseModel.getId())).thenReturn(optionalExpense);

		expenseServiceImpl.update(dto);
	}
}
