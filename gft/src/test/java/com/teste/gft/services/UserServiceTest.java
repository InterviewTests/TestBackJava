package com.teste.gft.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.teste.gft.entities.User;



@Transactional
@Commit
@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTest {
	@Autowired
	UserService service = new UserService();

	@Test
	void testCriacaoUsuario() {
		User user = new User();
		user.setUsername("Guilherme");
		user.setPassword("senha");

		assertEquals(user, service.criarUsuario(user));
		System.out.println();

	}

	@Test
	void testAutenticacaoUser() {
		User user = new User();
		user.setUsername("Guilherme");
		user.setPassword("senha");
		
		assertEquals(1, service.autenticar(user));
		
	}

}
