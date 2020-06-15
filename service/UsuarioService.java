package br.com.altrantest.demo.service;


import br.com.altrantest.demo.repository.UsuarioRepository;
import br.com.altrantest.demo.usuario.UsuarioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public UsuarioUser consultar(Long id){
        UsuarioUser result = repository.findById(id).get();
        return result;
    }


}
