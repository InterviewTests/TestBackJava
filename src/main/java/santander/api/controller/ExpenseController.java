package santander.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import santander.api.domain.Category;
import santander.api.domain.Expense;
import santander.api.domain.repository.CategoryRepository;
import santander.api.domain.repository.ExpenseRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/expense")
public class ExpenseController{


    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck()
    {
        return "{ \"isWorking\" : true }";
    }

    @PostMapping("/add")
    public Expense addExpense(@RequestBody Expense expense)
    {
        String id = String.valueOf(new Random().nextInt());
        Expense exp = new Expense(id, expense.getDescription(), expense.getValue(), expense.getUserCode(), expense.getDate(), expense.getCategory());
        expenseRepository.save(exp);
        return exp;
    }


    @GetMapping("/findAll")
    public List<Expense> getEmployees()
    {
        Iterable<Expense> result = expenseRepository.findAll();
        List<Expense> employeesList = new ArrayList<Expense>();
        result.forEach(employeesList::add);
        return employeesList;
    }

    @GetMapping(value = "/find/{userCode}/{date}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Expense>> findExpenseByUserCodeAndDate(@PathVariable Long userCode, @PathVariable String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(date);

        List<Expense> expenses = expenseRepository.findByUserCodeAndDate(userCode,d);
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public String deleteEmployee(@PathVariable String id) {
        boolean result = expenseRepository.existsById(id);
        expenseRepository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }


    @PutMapping(value = "/update", produces = "application/json; charset=utf-8")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense) {

        return expenseRepository.findById(expense.getId()).map(recordGasto -> {
            recordGasto.setCategory(expense.getCategory());

            Expense updatedExpense = expenseRepository.save(recordGasto);

            return ResponseEntity.ok().body(updatedExpense);
        }).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/addCategory")
    public Expense addCategoryAutomatically (@RequestBody Expense gasto) {

        List<Category> categories = categoryRepository.findByDescription(gasto.getCategory().getDescription());
        if (categories.size()>0){
            gasto.setCategory(categories.get(0));
        }

        return expenseRepository.save(gasto);
    }

}
