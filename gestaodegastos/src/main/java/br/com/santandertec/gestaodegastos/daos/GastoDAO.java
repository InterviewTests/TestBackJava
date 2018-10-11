package br.com.santandertec.gestaodegastos.daos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.santandertec.gestaodegastos.models.Gasto;
import br.com.santandertec.gestaodegastos.models.Usuario;

@Repository
@Transactional
public class GastoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Gasto> findAll(Integer codigoCliente) {
		
		Calendar dataMenosCincoSegundos = Calendar.getInstance();
		dataMenosCincoSegundos.add(Calendar.SECOND, -5);
		
		return manager.createQuery(" select g from Gasto g "
				+ " where g.usuario.id = :pCodigoCliente and g.data >= :pDataMenosCincoSegundos "
				+ " order by g.data desc ", Gasto.class)
				.setParameter("pCodigoCliente", codigoCliente)
				.setParameter("pDataMenosCincoSegundos", dataMenosCincoSegundos.getTime())
				.getResultList();
	}
	
	public List<Gasto> findByDate(Date data, Integer codigoCliente) {
		return manager.createQuery(" select g from Gasto g "
				+ " where g.usuario.id = :pCodigoCliente and date(g.data) = :pData "
				+ " order by g.data desc ", Gasto.class)
				.setParameter("pCodigoCliente", codigoCliente)
				.setParameter("pData", data)
				.getResultList();
	}
	
	public Gasto find(Integer id) {
		return manager.find(Gasto.class, id);
	}
	
	public Gasto save(Gasto gasto) {
		manager.persist(gasto);
		return gasto;
	}

	public Gasto update(Gasto gasto) {
		manager.merge(gasto);
		return gasto;
	}

	public Gasto buscarGastoComMesmaDescricaoParaCategorizarAutomaticamente(String descricao, Usuario usuario) {
		List<Gasto> gastos = manager.createQuery(" select g from Gasto g "
				+ " where g.descricao = :pDescricao "
				+ " and g.usuario.id = :pUsuario "
				+ " and g.categoria.nomeCategoria is not null ", Gasto.class)
				.setParameter("pDescricao", descricao)
				.setParameter("pUsuario", usuario.getId()).getResultList();
		
		if (gastos.isEmpty()) {
			return null;
		}
		
		return gastos.get(0);
	}
	
	public List<String> buscarCategoriasDoCliente(Integer id) {
		return manager.createQuery(" select distinct(g.categoria.nomeCategoria) from Gasto g "
				+ " where g.usuario.id = :pCodigoUsuario "
				+ " and g.categoria.nomeCategoria is not null "
				+ " order by g.categoria.nomeCategoria ", String.class)
				.setParameter("pCodigoUsuario", id).getResultList();
	}
	
}
