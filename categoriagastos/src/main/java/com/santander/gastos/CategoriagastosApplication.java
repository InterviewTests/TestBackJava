package com.santander.gastos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.santander.gastos.domain.Cartao;
import com.santander.gastos.domain.Categoria;
import com.santander.gastos.domain.Cliente;
import com.santander.gastos.domain.Conta;
import com.santander.gastos.domain.enuns.TipoConta;
import com.santander.gastos.repositories.CategoriaRepository;
import com.santander.gastos.repositories.ClienteRepository;
import com.santander.gastos.utils.CreditCardNumberGenerator;

@SpringBootApplication
public class CategoriagastosApplication implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository clirepo;
	
	@Autowired
	private CategoriaRepository catrepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CategoriagastosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		addClientesContasCartoes();
		addCategorias();
		
	}
	
	private void addCategorias() {
		List<Categoria> listCats = new ArrayList<Categoria>();
		
		for(int i=1;i<=10; i++) {
			String catnome = RandomStringUtils.randomAlphabetic(10);
			
			Categoria cat = new Categoria();
			cat.setNome(catnome);
			
			listCats.add(cat);
		}
		
		listCats.add(new Categoria("Entretenimento"));
		listCats.add(new Categoria("Alimentação"));
		listCats.add(new Categoria("Transporte"));
		listCats.add(new Categoria("Viagens"));
		listCats.add(new Categoria("Artigos esportivos"));
		listCats.add(new Categoria("Eletrodomesticos"));
		
		catrepo.saveAll(listCats);
		
	}
	
 	private void addClientesContasCartoes() {
		
		// Cria n clientes clientes
		List<Cliente> listcli = new ArrayList<Cliente>();
		
		for (int i=1; i <= 50; i++) {
			Random random = new Random();
			
			String nome = RandomStringUtils.randomAlphabetic(10);
			String email = RandomStringUtils.randomAlphabetic(11) + "@" +RandomStringUtils.randomAlphabetic(11) + ".com" ;
			String telefone = Integer.toString(i) ;
			String doc = Integer.toString(i);
			
			Cliente cli = new Cliente(null , nome , telefone, doc, pe.encode( "changeit" + i));
			
			cli.setEmail(email);
			
			Conta conta = new Conta(TipoConta.PESSOAFISICA,cli );
			
			conta.setAgencia( random.nextInt((9999 - 1111) + i) );
			
			conta.setNumero( random.nextInt((9999999 - 1111111) + i) );
			
			List<Cartao> cartoes = new ArrayList<Cartao>();
			
			for (int y=0 ; y <= 2; y++) {
				
				String numerocartao = (new CreditCardNumberGenerator()).generate("51559"+y, 16) ;
				
			    double limite = random.nextInt((9999 - 1111) + i);
			    Cartao cartao = new Cartao(null, numerocartao, limite  );
			    cartao.setConta(conta);
				cartoes.add( cartao );
			}
			
			conta.setCartoes(cartoes);
			cli.getContas().add(conta);
			listcli.add(cli);
		}
		
		clirepo.saveAll(listcli);
		
	}
}
