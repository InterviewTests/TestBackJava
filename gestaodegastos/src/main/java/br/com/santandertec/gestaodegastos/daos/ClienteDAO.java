package br.com.santandertec.gestaodegastos.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.santandertec.gestaodegastos.models.Cliente;

@Repository
public class ClienteDAO implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		List<Cliente> clientes = manager.createQuery(" select c from Cliente c where c.email = :pEmail ", Cliente.class)
				.setParameter("pEmail", email)
				.getResultList();
		
		if (clientes.isEmpty()) {
			throw new UsernameNotFoundException("Cliente com e-mail '" + email + "' não foi encontrado!");
		}
		
		return clientes.get(0);
	}
	
}
