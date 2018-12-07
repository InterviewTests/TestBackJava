package br.com.santander.card.sale.http.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.santander.card.sale.http.dto.CreateSaleRequest;
import br.com.santander.card.sale.http.dto.CreateSaleResponse;

@RunWith(SpringRunner.class)
public class SaleControllerTest {
	
	@Mock
	private SaleController saleController;
	
	private final static String DATE = "";
	
	@Test
	public void insert_nagativeValue() throws Exception {
		CreateSaleRequest request = new CreateSaleRequest();
		request.setCodigousuario(100);
		request.setData(DATE);
		request.setDescription("roupa");
		request.setValor(-100.00);
		ResponseEntity<CreateSaleResponse> responseEntity = ResponseEntity.badRequest().build();
		Mockito.when(saleController.insert(request)).thenReturn(responseEntity);
		
		ResponseEntity<CreateSaleResponse> r = saleController.insert(request);
		assertEquals(r.getStatusCode().value(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insert_withoutUser() throws Exception {
		CreateSaleRequest request = new CreateSaleRequest();
		request.setData(DATE);
		request.setDescription("roupa");
		request.setValor(100.00);
		ResponseEntity<CreateSaleResponse> responseEntity = ResponseEntity.badRequest().build();
		Mockito.when(saleController.insert(request)).thenReturn(responseEntity);
		
		ResponseEntity<CreateSaleResponse> r = saleController.insert(request);
		assertEquals(r.getStatusCode().value(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insert_withoutDescription() throws Exception {
		CreateSaleRequest request = new CreateSaleRequest();
		request.setCodigousuario(100);
		request.setData(DATE);
		request.setDescription("roupa");
		ResponseEntity<CreateSaleResponse> responseEntity = ResponseEntity.badRequest().build();
		Mockito.when(saleController.insert(request)).thenReturn(responseEntity);
		
		ResponseEntity<CreateSaleResponse> r = saleController.insert(request);
		assertEquals(r.getStatusCode().value(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insert_withoutDate() throws Exception {
		CreateSaleRequest request = new CreateSaleRequest();
		request.setCodigousuario(100);
		request.setDescription("roupa");
		request.setValor(100.00);
		ResponseEntity<CreateSaleResponse> responseEntity = ResponseEntity.badRequest().build();
		Mockito.when(saleController.insert(request)).thenReturn(responseEntity);
		
		ResponseEntity<CreateSaleResponse> r = saleController.insert(request);
		assertEquals(r.getStatusCode().value(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insert_successful() throws Exception {
		CreateSaleRequest request = new CreateSaleRequest();
		request.setCodigousuario(100);
		request.setData(DATE);
		request.setDescription("roupa");
		request.setValor(100.00);
		ResponseEntity<CreateSaleResponse> responseEntity = ResponseEntity.badRequest().build();
		Mockito.when(saleController.insert(request)).thenReturn(responseEntity);
		
		ResponseEntity<CreateSaleResponse> r = saleController.insert(request);
		assertEquals(r.getStatusCode().value(), HttpStatus.CREATED);
	}
	
	
	
}
