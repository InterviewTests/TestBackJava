package com.santander.interview.webservice;

import com.santander.interview.domain.Expense;
import com.santander.interview.domain.Response;
import com.santander.interview.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseApi {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/expenses")
    public ResponseEntity<Response> addExpense(@RequestBody Expense expense) {
        this.expenseService.addNewExpense(expense);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), "Criado com sucesso", null),
                HttpStatus.OK
        );
    }

    @GetMapping("/expense/userCode/{userCode}")
    public ResponseEntity<Response> getExpenseByUserCode(@PathVariable long userCode) {
        List<Expense> expensesByUserCode = this.expenseService.findExpenseByCodigoUsuario(userCode);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), "Criado com sucesso", expensesByUserCode),
                HttpStatus.OK
        );
    }


}
