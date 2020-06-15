package br.com.altrantest.demo.repository;

import br.com.altrantest.demo.gastos.GastoByUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface GastosRepository  extends PagingAndSortingRepository<GastoByUser, Long> {
    public Optional<GastoByUser> findById(Long id);


     List<GastoByUser> findByDescricao(String desc);
}