package br.com.adslima.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.adslima.converter.ExpenseManagementConvert;
import br.com.adslima.dto.ExpenseManagementQueryDTO;
import br.com.adslima.handler.ExpenseManagementHandler;
import br.com.adslima.model.Category;
import br.com.adslima.model.ExpenseManagement;
import br.com.adslima.repository.CategoryRepository;
import br.com.adslima.repository.ExpenseManagementRepository;
import br.com.adslima.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api-queries")
public class ExpenseManagementQueryController {

	private static final int NUMBER_PAR_PAG = 25;
	private static final long NUMBER_FIVE = 5;

	@Autowired
	private ExpenseManagementRepository repository;

	@Autowired
	CategoryRepository categoryRepository;

	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Response<Iterable<ExpenseManagement>>> getAll() {

		Response<Iterable<ExpenseManagement>> response = new Response<Iterable<ExpenseManagement>>();

		Iterable<ExpenseManagement> cardsExpenses = repository.findAll();
		response.setData(cardsExpenses);

		return ResponseEntity.ok().body(response);
	}

	/**
	 * 
	 * @param userCode
	 * @param pag
	 * @param ord
	 * @param dir
	 * @return
	 */
	@GetMapping("/{userCode}/expense-menagement")
	public ResponseEntity<Response<Page<ExpenseManagementQueryDTO>>> findByUserCode(
			@PathVariable final Integer userCode, @RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "date") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		log.info("Searching for user expenses: {}", userCode);

		Response<Page<ExpenseManagementQueryDTO>> response = new Response<Page<ExpenseManagementQueryDTO>>();

		Page<ExpenseManagement> cardExpenses = new ExpenseManagementHandler(repository).findLastExpensesCardsByUserCode(
				userCode, LocalDateTime.now().minusSeconds(NUMBER_FIVE),
				PageRequest.of(pag, NUMBER_PAR_PAG, Direction.valueOf(dir), ord));

		Page<ExpenseManagementQueryDTO> communsDto = cardExpenses
				.map(cardExpense -> ExpenseManagementConvert.toConvertFromModelSummary(cardExpense));

		response.setData(communsDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param dto
	 * @param pag
	 * @param ord
	 * @param dir
	 * @return
	 */
	@GetMapping("/expense-menagement")
	public ResponseEntity<Response<Page<ExpenseManagementQueryDTO>>> findByDateFilter(
			final ExpenseManagementQueryDTO dto, @RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("");

		Response<Page<ExpenseManagementQueryDTO>> response = new Response<Page<ExpenseManagementQueryDTO>>();

		Page<ExpenseManagement> cardExpenses = new ExpenseManagementHandler(repository).findExpensesCardsByFilter(
				dto.getUserCode(), dto.getDate(), PageRequest.of(pag, NUMBER_PAR_PAG, Direction.valueOf(dir), ord));

		Page<ExpenseManagementQueryDTO> communsDto = cardExpenses
				.map(cardExpense -> ExpenseManagementConvert.toConvertFromModelSummary(cardExpense));
		response.setData(communsDto);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param searchTerm
	 * @param pag
	 * @param ord
	 * @param dir
	 * @return
	 */
	@GetMapping("/{categoryDescription}/categories")
	public ResponseEntity<FacetPage<Category>> findBySuggestionCategory(@PathVariable final String categoryDescription,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		log.info("Buscando sugestões de categorias.." + categoryDescription);
		FacetPage<Category> categories = this.categoryRepository.findByDescriptionAndFacetOnCategories(
				categoryDescription, PageRequest.of(pag, NUMBER_PAR_PAG, Direction.valueOf(dir), ord));

		return ResponseEntity.ok(categories);
	}
}