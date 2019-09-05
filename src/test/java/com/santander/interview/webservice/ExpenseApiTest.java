package com.santander.interview.webservice;

import com.santander.interview.domain.Expense;
import com.santander.interview.domain.Response;
import com.santander.interview.service.ExpenseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExpenseApiTest {
    private static final String DESCRICAO = "descricao";
    private static final Double VALOR = 198.23;
    private static final long CODIGO_USUARIO = 129;
    private static final Date DATA = new Date();

    @InjectMocks
    ExpenseApi expenseApi = new ExpenseApi();

    @Mock
    ExpenseService expenseService;

    Expense expense;

    String uuid;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.uuid = UUID.randomUUID().toString();
        this.expense = new Expense();
        this.expense.setId(uuid);
        this.expense.setDescricao(DESCRICAO);
        this.expense.setValor(VALOR);
        this.expense.setCodigoUsuario(CODIGO_USUARIO);
        this.expense.setData(DATA);
        this.expense.setCategory(null);
    }

    @Test
    public void addExpenseTest() {
        ResponseEntity<Response> response = this.expenseApi.addExpense(this.expense);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getStatusCode(), HttpStatus.OK.value());
        Assert.assertNull(response.getBody().getData());
    }

    @Test
    public void getExpenseByUserCodeTest() {
        long userCode = CODIGO_USUARIO;
        List<Expense> list = new ArrayList<>();
        list.add(this.expense);

        Mockito.when(expenseService.findExpensesByCodigoUsuario(userCode)).thenReturn(list);

        ResponseEntity<Response> response = this.expenseApi.getExpenseByUserCode(userCode);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getStatusCode(), HttpStatus.OK.value());
        Assert.assertEquals(response.getBody().getData(), list);
    }

    @Test
    public void getExpenseByUserCodeAndDateTest() throws ParseException {
        long userCode = CODIGO_USUARIO;
        String date = DATA.toString();
        List<Expense> list = new ArrayList<>();
        list.add(this.expense);

        Mockito.when(this.expenseService.findExpensesByCodigoUsuarioAndData(userCode, date)).thenReturn(list);
        ResponseEntity<Response> response = this.expenseApi.getExpenseByUserCodeAndDate(userCode, date);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getStatusCode(), HttpStatus.OK.value());
        Assert.assertEquals(response.getBody().getData(), list);
    }

    @Test
    public void getExpenseByUserCodeAndDate_WithDataIncorrectTest() throws ParseException {
        long userCode = CODIGO_USUARIO;
        String date = "121251";
        Mockito.when(this.expenseService.findExpensesByCodigoUsuarioAndData(userCode, date))
                .thenThrow(ParseException.class);
        ResponseEntity<Response> response = this.expenseApi.getExpenseByUserCodeAndDate(userCode, date);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(response.getBody().getStatusCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void updateExpenseTest() {
        String id = this.uuid;
        ResponseEntity<Response> response = this.expenseApi.updateExpense(id, expense);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getStatusCode(), HttpStatus.OK.value());
    }
}
