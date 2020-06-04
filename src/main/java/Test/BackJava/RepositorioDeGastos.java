package Test.BackJava;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeGastos extends CrudRepository<Gasto, Long> {

  Gasto findById(@Param("id") String id);
  
  List<Gasto> findByCodigousuario(@Param("codigousuario") Integer codigousuario);

}