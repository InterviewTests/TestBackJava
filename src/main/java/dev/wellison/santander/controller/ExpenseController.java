package dev.wellison.santander.controller;

import dev.wellison.santander.domain.Expense;
import dev.wellison.santander.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wellison.santander.service.ExpenseService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController{


    @Autowired
    ExpenseService expenseService;
    @Autowired
    CategoryService categoryService;


    @PostMapping("/add")
    public Expense addExpense(@RequestBody Expense expense)
    {
        return expenseService.saveExpense(expense);
    }


    @GetMapping("/findAll")
    public List<Expense> getAllExpenses()
    {
        return expenseService.getAllEmployees();
    }

    @GetMapping(value = "/find/{userCode}/{date}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Expense>> findExpenseByUserCodeAndDate(@PathVariable Long userCode, @PathVariable String date) throws ParseException {

        List<Expense> expenses = expenseService.findExpenseByUserCodeAndDate(userCode,date);
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public String deleteExpense(@PathVariable String id) {
        return expenseService.deleteExpense(id);
    }

    @PutMapping(value = "/update", produces = "application/json; charset=utf-8")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense request) {

        Expense result = expenseService.updateExpense(request);
        return (result!=null)?ResponseEntity.ok().body(result):ResponseEntity.notFound().build();
    }


    @PostMapping("/addCategory")
    public Expense addCategoryAutomatically (@RequestBody Expense expense) {

        return expenseService.addCategoryAutomatically(expense);
    }

}
