package br.com.adslima.components;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.adslima.ExpenseManagementQueryApplication;
import br.com.adslima.model.Category;
import br.com.adslima.model.ExpenseManagement;
import br.com.adslima.repository.CategoryRepository;
import br.com.adslima.repository.ExpenseManagementRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpenseManagementQueryApplication.class)
@ActiveProfiles("test")
public class ExpenseManagementEventProcessTest {

	private static final int NUMBER_PAR_PAG = 10;

	@MockBean
	private ExpenseManagementRepository repository;

	@MockBean
	private CategoryRepository categoryRepository;

	@Before
	public void setUp() throws Exception {

		BDDMockito.given(this.categoryRepository.findByDescription(Mockito.anyString()))
				.willReturn(new ArrayList<Category>());

		BDDMockito.given(this.categoryRepository.save(Mockito.any(Category.class))).willReturn(new Category());

		BDDMockito
				.given(this.repository.findExpensesCardsByUserCode((Mockito.anyInt()), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<ExpenseManagement>(new ArrayList<ExpenseManagement>()));

		BDDMockito.given(this.repository.findExpensesCardsByUserCode(Mockito.anyInt()))
				.willReturn(new ArrayList<ExpenseManagement>());

		BDDMockito.given(this.repository.save(Mockito.any(ExpenseManagement.class)))
				.willReturn(new ExpenseManagement());
	}

	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		Page<ExpenseManagement> expense = this.repository.findExpensesCardsByUserCode(Integer.valueOf(1),
				PageRequest.of(0, NUMBER_PAR_PAG, Direction.DESC, "id"));

		assertNotNull(expense);
	}

	@Test
	public void testPersistirLancamento() {
		ExpenseManagement expense = this.repository.save(new ExpenseManagement());

		assertNotNull(expense);
	}

}