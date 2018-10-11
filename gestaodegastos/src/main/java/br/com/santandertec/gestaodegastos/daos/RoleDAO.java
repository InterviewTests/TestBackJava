package br.com.santandertec.gestaodegastos.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.santandertec.gestaodegastos.models.Role;

@Repository
@Transactional
public class RoleDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Role> buscarTodas() {
		return manager.createQuery(" select r from Role r order by r.id ", Role.class).getResultList();
	}
	
	public void gravar(Role role) {
		manager.persist(role);
	}
	
}
