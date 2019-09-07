package com.santander.interview.webservice;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Expense;
import com.santander.interview.domain.Response;
import com.santander.interview.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class ExpenseApi {

    @Autowired
    ExpenseService expenseService;

    @PostMapping("/expenses")
    public ResponseEntity<Response> addExpense(@RequestBody Expense expense) {
        this.expenseService.addNewExpense(expense);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), ADD_EXPENSE_SUCCESS.getMessage(), null),
                HttpStatus.OK
        );
    }

    @GetMapping("/expense/userCode/{userCode}")
    public ResponseEntity<Response> getExpenseByUserCode(@PathVariable long userCode) {
        List<Expense> expensesByUserCode = this.expenseService.findExpensesByCodigoUsuario(userCode);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), SEARCH_EXPENSE_BY_USER_CODE_SUCCESS.getMessage(),
                        expensesByUserCode),
                HttpStatus.OK
        );
    }

    @GetMapping("/expense/userCode/{userCode}/date/{date}")
    public ResponseEntity<Response> getExpenseByUserCodeAndDate(@PathVariable long userCode, @PathVariable String date) {
        try {
            List<Expense> expensesByUserCodeAndDate = this.expenseService.findExpensesByCodigoUsuarioAndData(userCode, date);
            return new ResponseEntity<>(
                    new Response(HttpStatus.OK.value(), SEARCH_EXPENSE_BY_USER_CODE_AND_DATE_SUCCESS.getMessage(),
                            expensesByUserCodeAndDate),
                    HttpStatus.OK
            );
        } catch (ParseException pe){
            return new ResponseEntity<>(
                    new Response(HttpStatus.BAD_REQUEST.value(), ERROR_BADLY_FORMATTED_DATE.getMessage(), null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/expense/{id}")
    public ResponseEntity<Response> updateExpense(@PathVariable String id, @RequestBody Expense expense) {
        this.expenseService.updateExpense(id, expense);

        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), UPDATE_EXPENSE_SUCCESS.getMessage(), null),
                HttpStatus.OK
        );
    }
}
