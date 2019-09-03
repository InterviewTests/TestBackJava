package com.santander.interview.webservice;

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
                new Response(HttpStatus.OK.value(), "Criado com sucesso", null),
                HttpStatus.OK
        );
    }

    @GetMapping("/expense/userCode/{userCode}")
    public ResponseEntity<Response> getExpenseByUserCode(@PathVariable long userCode) {
        List<Expense> expensesByUserCode = this.expenseService.findExpenseByCodigoUsuario(userCode);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), "Busca por código do usuário realizada com sucesso",
                        expensesByUserCode),
                HttpStatus.OK
        );
    }

    @GetMapping("/expense/userCode/{userCode}/date/{date}")
    public ResponseEntity<Response> getExpenseByUserCodeAndDate(@PathVariable long userCode, @PathVariable String date) {
        try {
            List<Expense> expensesByUserCodeAndDate = this.expenseService.findExpenseByCodigoUsuarioAndData(userCode, date);
            return new ResponseEntity<>(
                    new Response(HttpStatus.OK.value(), "Busca por código do usuário e pela data realizada com sucesso",
                            expensesByUserCodeAndDate),
                    HttpStatus.OK
            );
        } catch (ParseException pe){
            return new ResponseEntity<>(
                    new Response(HttpStatus.BAD_REQUEST.value(), "Data mal formatada", null),
                    HttpStatus.BAD_REQUEST
            );
        }

    }
}
