package br.com.adslima.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.adslima.ExpenseManagementCommandApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpenseManagementCommandApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ExpenseManagementControllerTest {

	@Test
	public void teste() {

	}

}
