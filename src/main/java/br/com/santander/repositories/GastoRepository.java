package br.com.santander.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.santander.entities.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, String> {

	List<Gasto> findAllByCodigousuario(Integer codigo);
	
	@Query("select g from Gasto as g where g.data between ?1 and ?2 and g.codigousuario = ?3")
	List<Gasto> findAllByDataBetweenAndCodigousuario(Date dataInicio, Date dataFim, Integer codigo);
	
	Optional<Gasto> findByUuidAndCodigousuario(String uuid, Integer codigoUsuario);
	
	@Query("select g.categoria from Gasto as g where g.codigousuario = ?1 and g.categoria like ?2")
	List<String> findCategoraiasByCodigousuarioAndLikeCategoria(Integer codigousuario, String categoria);
	
	@Query("select g from Gasto as g where g.descricao like ?1  AND g.codigousuario = ?2")
	List<Gasto> findAllByLikeDescricaoAndCodigousuario(String descricao, Integer codigoUsuario);
}
