package br.com.santander.repository;

import br.com.santander.domain.Gasto;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GastosRepository extends PagingAndSortingRepository<Gasto, String> {

    List<Gasto> findAllByCodigoUsuario(long codigoUsuario);
}
