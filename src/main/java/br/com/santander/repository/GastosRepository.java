package br.com.santander.repository;

import br.com.santander.domain.Gasto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GastosRepository extends PagingAndSortingRepository<Gasto, String> {

}
