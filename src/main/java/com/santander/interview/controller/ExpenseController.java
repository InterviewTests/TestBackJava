package com.santander.interview.controller;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Expense;
import com.santander.interview.domain.Response;
import com.santander.interview.exception.ExpenseException;
import com.santander.interview.service.ExpenseService;
import com.santander.interview.utils.ExpenseManagementUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ExpenseManagementUtils.responseWithoutData(ADD_EXPENSE_SUCCESS, HttpStatus.OK);
    }

    @ApiOperation("Buscar gastos por usuário")
    @GetMapping("/expense/userCode/{userCode}")
    public ResponseEntity<?> getExpenseByUserCode(
            @ApiParam(value = "Código do cliente", required = true) @PathVariable long userCode
    ) {
        List<Expense> expensesByUserCode = this.expenseService.findExpensesByUserCode(userCode);

        return ExpenseManagementUtils.responseWithData(SEARCH_EXPENSE_BY_USER_CODE_SUCCESS,
                                                        HttpStatus.OK, expensesByUserCode);
    }

    @ApiOperation("Buscar gastos por usuário e data")
    @GetMapping("/expense/userCode/{userCode}/date/{date}")
    public ResponseEntity<?> getExpenseByUserCodeAndDate(
            @ApiParam(value = "Código do cliente", required = true) @PathVariable long userCode,
            @ApiParam(value = "Data a ser pesquisada", required = true) @PathVariable String date
    ) {
        List<Expense> expensesByUserCodeAndDate;
        try {
            expensesByUserCodeAndDate = this.expenseService.findExpensesByUserCodeAndDate(userCode, date);
            return ExpenseManagementUtils.responseWithData(SEARCH_EXPENSE_BY_USER_CODE_AND_DATE_SUCCESS,
                                                            HttpStatus.OK, expensesByUserCodeAndDate);
        } catch (ExpenseException ee){
            return ExpenseManagementUtils.responseWithError(ee.getResponseMessageEnum(), ee.getStatusCode());
        }
    }

    @ApiOperation("Atualizar gasto")
    @PutMapping("/expense/{id}")
    public ResponseEntity<?> updateExpense(
            @ApiParam(value = "ID do gasto a ser atualizado", required = true) @PathVariable String id,
            @ApiParam(value = "Gasto atualizado", required = true) @RequestBody Expense expense
    ) {
        try {
            this.expenseService.updateExpense(id, expense);
            return ExpenseManagementUtils.responseWithoutData(UPDATE_EXPENSE_SUCCESS, HttpStatus.OK);
        } catch (ExpenseException ee) {
            return ExpenseManagementUtils.responseWithError(ee.getResponseMessageEnum(), ee.getStatusCode());
        }
    }
}
