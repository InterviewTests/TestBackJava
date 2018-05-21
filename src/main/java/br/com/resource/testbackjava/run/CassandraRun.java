package br.com.resource.testbackjava.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import br.com.resource.testbackjava.data.CreditCardOutgoing;
import br.com.resource.testbackjava.data.CreditCardOutgoingRepository;
import br.com.resource.testbackjava.data.Rank;
import br.com.resource.testbackjava.data.User;
import br.com.resource.testbackjava.data.UserRepository;
import br.com.resource.testbackjava.service.RankService;

@SpringBootApplication
@EnableCassandraRepositories("br.com.resource.testbackjava.data")
@EntityScan("br.com.resource.testbackjava.data")
public class CassandraRun implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CreditCardOutgoingRepository creditCardOutgoingRepository;
	
	@Autowired
	private RankService rankService; 
	
	@Override
	public void run(String... args) throws Exception {
		
		this.userRepository.save(new User( 666, "Admin", "admin"));
		
		User user = new User( 0, "Ricardo Gallassi", "xxx");
		List<User> users = new ArrayList<>();
		List<CreditCardOutgoing> ccos = new ArrayList<>();
		List<Rank> ranks = new ArrayList<>();
		users.add(user);
		for (int i = 1; i < 3;i++) {
			String desc = "Gasto " + i;
			String cat = "Categoria " + i;
			CreditCardOutgoing cco = new CreditCardOutgoing(desc, (10000000.66D + i)*1000/i , user.getCodigoUsuario(), new Date());
			Rank rank = new Rank(desc, cat);
			rankService.save(rank);

			ccos.add(cco);
		}
		
		for (int i = 1; i < 7;i++) {
			user = new User( i, "Fulano " + i, String.valueOf(i));
			users.add(user);
			for (int j = 0; j < i + 1; j++ ) {
				String desc = "Gasto " + j;
				String cat = "Categoria " + i;
				
				CreditCardOutgoing cco = new CreditCardOutgoing(desc, (j + 1.00000d / j+1), user.getCodigoUsuario(), new Date());
				if (j%2 == 0 ) {
					cco.setCategoria("Categoria " + j);
					Rank rank = new Rank(desc, cat);
					ranks.add(rank);
				}
				
				ccos.add(cco);
			}
		}
		
		this.userRepository.saveAll(users);
		
		this.creditCardOutgoingRepository.saveAll(ccos);
		
		this.rankService.saveAll(ranks);
		
		this.rankService.listarCategoriasUtilizadasPorDescricao("Gasto 1");
	}
}
