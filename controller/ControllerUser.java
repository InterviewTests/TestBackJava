package br.com.altrantest.demo.controller;

import br.com.altrantest.demo.repository.UsuarioRepository;
import br.com.altrantest.demo.usuario.UsuarioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ControllerUser {
    @Autowired
    private UsuarioRepository repository;
    private UsuarioUser usuario;


    //Retorna tudo
    @GetMapping(path = "/user")
    public ResponseEntity<?>findAll(){

        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
//controle de usuario
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id")Long id,
                                         @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(userDetails);
        UsuarioUser usuario = repository.findById(id).get();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    @PostMapping(path = "/admin")
    public ResponseEntity<UsuarioUser> adcionarUsuario(@RequestBody UsuarioUser usuario){
       usuario = repository.save(usuario);
        return ResponseEntity.ok().body(usuario);
    }
    @DeleteMapping(path = "/admin/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/admin/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioUser usuario){
        repository.save(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
