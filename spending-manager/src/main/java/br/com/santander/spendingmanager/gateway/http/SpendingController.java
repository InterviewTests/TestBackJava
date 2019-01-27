package br.com.santander.spendingmanager.gateway.http;

import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.domain.Category;
import br.com.santander.spendingmanager.gateway.http.converter.SpendingConverter;
import br.com.santander.spendingmanager.gateway.http.converter.SpendingRequestConverter;
import br.com.santander.spendingmanager.gateway.http.json.SpendingCategoryJson;
import br.com.santander.spendingmanager.gateway.http.json.SpendingRequestJson;
import br.com.santander.spendingmanager.gateway.http.json.SpendingResponseJson;
import br.com.santander.spendingmanager.usecases.CategoryUseCase;
import br.com.santander.spendingmanager.usecases.SpendingUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController()
public class SpendingController {

    private SpendingUseCase spendingUseCase;
    private SpendingRequestConverter spendingRequestConverter;
    private SpendingConverter spendingConverter;
    private CategoryUseCase categoryUseCase;

    private final Logger log = LoggerFactory.getLogger(SpendingController.class);

    @Autowired
    public SpendingController(SpendingUseCase spendingUseCase,
                              SpendingRequestConverter spendingRequestConverter,
                              SpendingConverter spendingConverter,
                              CategoryUseCase categoryUseCase) {
        this.spendingUseCase = spendingUseCase;
        this.spendingRequestConverter = spendingRequestConverter;
        this.spendingConverter = spendingConverter;
        this.categoryUseCase = categoryUseCase;
    }

    @PostMapping("/spending")
    public ResponseEntity saveSpending(@Valid @RequestBody SpendingRequestJson spendingRequestJson) {
        log.info("POST on /spending with {}", spendingRequestJson.toString());

        Spending spending = spendingRequestConverter.convert(spendingRequestJson);
        spendingUseCase.saveSpending(spending);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/spending")
    public ResponseEntity updateCategoryById(@Valid @RequestBody SpendingCategoryJson spendingCategoryJson) {
        log.info("PUT on /spending with {}", spendingCategoryJson.toString());

        spendingUseCase.updateCategory(spendingCategoryJson);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/spending/user/{user_code}")
    public ResponseEntity getSpendings(@PathVariable("user_code")int userCode) {

        log.info("GET /spending/user{}", userCode);

        List<Spending> spendings = spendingUseCase.findSpendingsByUserCode(userCode);
        SpendingResponseJson responseJson = spendingConverter.convert(spendings);

        log.info("GET /spending/user/{} returns {} rows", userCode, spendings.size());
        return ResponseEntity.ok(responseJson);
    }

    @GetMapping("/spending/user/{user_code}/date/{spending_date}")
    public ResponseEntity getSpendingsByDate(@PathVariable("spending_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                             @PathVariable("user_code") int userCode) {

        log.info("GET /spending/date{}", date);

        List<Spending> spendings = spendingUseCase.findSpendingsByDateAndUserCode(date, userCode);
        SpendingResponseJson responseJson = spendingConverter.convert(spendings);

        log.info("GET /spending/date{} returns {} rows", date, spendings.size());
        return ResponseEntity.ok(responseJson);
    }

    @GetMapping("/spending/categories/{category_text_part}")
    public ResponseEntity getCategories(@PathVariable("category_text_part") String categoryTextPart) {

        log.info("GET /spending/categories/{}", categoryTextPart);

        List<Category> categories = categoryUseCase.findCategoryByTextPart(categoryTextPart);
        log.info("GET /spending/categories/{} returns {} rows", categoryTextPart, 0);
        return ResponseEntity.ok(categories);
    }
}
