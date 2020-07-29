package br.com.santander.testbackjava.incluirgastocartao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ IncluirGastoCartaoCodigoUsuarioInvalidoTest.class, IncluirGastoCartaoDescricaoInvalidoTest.class,
		IncluirGastoCartaoValorInvalidoTest.class, IncluirGastoCartaoValidaSistemaCredenciadoTest.class,
		IncluirGastoCartaoProducerTest.class, IncluirGastoCartaoConsumerTest.class })
public class IncluirGastoCartaoServiceAllTest {

}
