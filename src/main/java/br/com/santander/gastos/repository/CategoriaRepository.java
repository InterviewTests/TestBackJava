package br.com.santander.gastos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.santander.gastos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByDescricao(String descricao);

	@Query("SELECT c FROM Categoria c WHERE UPPER(c.descricao) LIKE CONCAT('%',UPPER(:descricao),'%')")
	List<Categoria> findCategoriasByDescricao(@Param("descricao") String descricao);
}
