package br.com.santander.app.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.santander.app.converter.CategoryConverter;
import br.com.santander.app.dto.CategoryDTO;
import br.com.santander.app.exception.CategoryNotFoundException;
import br.com.santander.app.model.Category;
import br.com.santander.app.objectfactory.CategoryMother;
import br.com.santander.app.repository.CategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplUnitTest {

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	@Mock
	private CategoryRepository categoryRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final String DESCRIPTION_MODEL_TEST = CategoryMother.DESCRIPTION_MODEL_TEST;

	@Test
	public void findCategorySuggestionByDescriptionTest() {
		final List<Category> listCategory= CategoryMother.getListCategoryModelPattern();
		Mockito.when(categoryRepository.findByDescriptionContainingIgnoreCase(DESCRIPTION_MODEL_TEST)).thenReturn(listCategory);
		final List<CategoryDTO> listReturn = categoryServiceImpl.findCategorySuggestionByDescription(DESCRIPTION_MODEL_TEST);

		Assert.assertEquals(listReturn.size(), 1);
		Assert.assertEquals(listCategory.get(0), CategoryConverter.fromDTO(listReturn.get(0)));
	}

	@Test
	public void findCategorySuggestionByDescriptionErrorTest() {
		exception.expect(CategoryNotFoundException.class);
		final CategoryNotFoundException exception= new CategoryNotFoundException("Categories not found with this description: "+ DESCRIPTION_MODEL_TEST);
		Mockito.when(categoryRepository.findByDescriptionContainingIgnoreCase(DESCRIPTION_MODEL_TEST)).thenThrow(exception);
		categoryServiceImpl.findCategorySuggestionByDescription(DESCRIPTION_MODEL_TEST);
	}
}
