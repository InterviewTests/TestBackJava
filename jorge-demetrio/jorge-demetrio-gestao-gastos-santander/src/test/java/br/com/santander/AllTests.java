package br.com.santander;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.santander.cartao.dao.impl.UsuarioDAOJPATest;
import br.com.santander.cartao.service.impl.GastosServiceImplTest;
import br.com.santander.cartao.service.impl.LoginServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({ UsuarioDAOJPATest.class, GastosServiceImplTest.class, LoginServiceImplTest.class, GastosServiceImplTest.class })
public class AllTests {

}
