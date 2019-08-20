package com.santander.repository.gasto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.santander.model.Gasto;
import com.santander.model.dto.GastoDTO;
import com.santander.repository.filter.GastoFilter;

public class GastoRepositoyImpl implements GastoRepositoyQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Gasto> filtrar(GastoFilter gastoFilter, int codigoUsuario) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Gasto> criteria = builder.createQuery(Gasto.class);
		Root<Gasto> root = criteria.from(Gasto.class);

		Predicate[] predicates = criarRestricoes(gastoFilter, codigoUsuario, builder, root);
		criteria.where(predicates);

		TypedQuery<Gasto> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(GastoFilter gastoFilter, int codigoUsuario, CriteriaBuilder builder,
			Root<Gasto> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (gastoFilter.getData() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("data"), gastoFilter.getData().atStartOfDay()));
			predicates.add(builder.lessThanOrEqualTo(root.get("data"), gastoFilter.getData().atTime(LocalTime.MAX)));
		} else {
			LocalDateTime now = LocalDateTime.now();
			predicates.add(builder.lessThanOrEqualTo(root.get("data"), now));

			LocalDateTime second = now.minusSeconds(5);
			predicates.add(builder.greaterThanOrEqualTo(root.get("data"), second));
		}

		if (!StringUtils.isEmpty(codigoUsuario)) {
			predicates.add(builder.equal(root.get("codigoUsuario"), codigoUsuario));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public List<GastoDTO> buscarPorCategoria(String categoria) {
		String jpql = "select new com.santander.model.dto.GastoDTO(categoria) "
				+ "from Gasto where lower(categoria) like lower(:categoria)";

		List<GastoDTO> categoriasFiltradas = manager.createQuery(jpql, GastoDTO.class)
				.setParameter("categoria", categoria + "%").getResultList();
		return categoriasFiltradas;
	}


}
