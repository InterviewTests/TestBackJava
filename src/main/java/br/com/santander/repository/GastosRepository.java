package br.com.santander.repository;

import br.com.santander.domain.Gasto;
import org.springframework.data.repository.CrudRepository;

public interface GastosRepository extends CrudRepository<Gasto, String> {

}
