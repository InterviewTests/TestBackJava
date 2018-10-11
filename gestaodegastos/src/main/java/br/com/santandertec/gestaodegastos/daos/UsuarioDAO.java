package br.com.santandertec.gestaodegastos.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.santandertec.gestaodegastos.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Usuario find(Integer id) {
		return manager.find(Usuario.class, id);
	}
	
}
