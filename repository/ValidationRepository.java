package br.com.altrantest.demo.repository;

import br.com.altrantest.demo.usuario.UserValid;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ValidationRepository extends PagingAndSortingRepository<UserValid, Long> {
    UserValid findByUsername(String username);
}
