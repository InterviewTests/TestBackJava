package com.santander.interview.controller;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Expense;
import com.santander.interview.domain.Response;
import com.santander.interview.service.ExpenseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/expense-management")
@Api(value = "Gasto")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @ApiOperation(value = "Adicionar gasto")
    @PostMapping("/expenses")
    public ResponseEntity<Response> addExpense(
            @ApiParam(value = "Novo gasto", required = true) @RequestBody Expense expense
    ) {
        this.expenseService.addNewExpense(expense);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), ADD_EXPENSE_SUCCESS.getMessage(), null),
                HttpStatus.OK
        );
    }

    @ApiOperation("Buscar gastos por usuário")
    @GetMapping("/expense/userCode/{userCode}")
    public ResponseEntity<Response> getExpenseByUserCode(
            @ApiParam(value = "Código do cliente", required = true) @PathVariable long userCode
    ) {
        List<Expense> expensesByUserCode = this.expenseService.findExpensesByUserCode(userCode);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), SEARCH_EXPENSE_BY_USER_CODE_SUCCESS.getMessage(),
                        expensesByUserCode),
                HttpStatus.OK
        );
    }

    @ApiOperation("Buscar gastos por usuário e data")
    @GetMapping("/expense/userCode/{userCode}/date/{date}")
    public ResponseEntity<Response> getExpenseByUserCodeAndDate(
            @ApiParam(value = "Código do cliente", required = true) @PathVariable long userCode,
            @ApiParam(value = "Data a ser pesquisada", required = true) @PathVariable String date
    ) {
        try {
            List<Expense> expensesByUserCodeAndDate = this.expenseService.findExpensesByUserCodeAndDate(userCode, date);
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

    @ApiOperation("Atualizar gasto")
    @PutMapping("/expense/{id}")
    public ResponseEntity<Response> updateExpense(
            @ApiParam(value = "ID do gasto a ser atualizado", required = true) @PathVariable String id,
            @ApiParam(value = "Gasto atualizado", required = true) @RequestBody Expense expense
    ) {
        this.expenseService.updateExpense(id, expense);

        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), UPDATE_EXPENSE_SUCCESS.getMessage(), null),
                HttpStatus.OK
        );
    }
}
