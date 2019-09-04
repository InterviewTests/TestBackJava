package br.com.santander;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.santander.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SantanderApplicationTests extends AbstractTest {
	
	@Autowired
	private BCryptPasswordEncoder e;

	@Test
	public void contextLoads() {
		System.out.println(e.encode("123"));
	}

}
