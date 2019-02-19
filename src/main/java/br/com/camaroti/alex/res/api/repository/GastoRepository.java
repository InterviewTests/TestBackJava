package br.com.camaroti.alex.res.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.camaroti.alex.res.api.model.Gasto;

@Repository
public interface GastoRepository extends CrudRepository<Gasto, Integer> {

}
