package br.com.altrantest.demo.repository;

import br.com.altrantest.demo.usuario.UsuarioUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<UsuarioUser,  Long> {
    public Optional<UsuarioUser> findById(Long id);



}
