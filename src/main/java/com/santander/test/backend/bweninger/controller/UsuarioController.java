package com.santander.test.backend.bweninger.controller;

import com.santander.test.backend.bweninger.exception.SenhasDiferentesException;
import com.santander.test.backend.bweninger.exception.UsuarioOuSenhaInvalidaException;
import com.santander.test.backend.bweninger.model.Usuario;
import com.santander.test.backend.bweninger.service.UsuarioService;
import com.santander.test.backend.bweninger.util.JwtUtil;
import com.santander.test.backend.bweninger.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/api/usuarios", method = RequestMethod.POST)
    public ResponseEntity<UsuarioVO> primeiroAcesso(@RequestBody UsuarioVO usuarioVO) {
        Optional<Usuario> usuario = usuarioService.obterPorCpf(usuarioVO.getCpf());
        if (usuario.isPresent() && usuario.get().getAtivo()) {
            usuarioVO.setMsg("CPF já possui cadastro");
            return new ResponseEntity<>(usuarioVO, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                usuarioService.criar(usuarioVO);
                usuarioVO.setMsg("Usuario criado com sucesso.");
                return new ResponseEntity<>(usuarioVO, HttpStatus.CREATED);
            } catch (SenhasDiferentesException e) {
                usuarioVO.setMsg("As senhas digitadas não correspondem");
                return new ResponseEntity<>(usuarioVO, HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<UsuarioVO> login(@RequestBody UsuarioVO usuarioVO) {
        try {
            String cpf = usuarioVO.getCpf();
            String senha = usuarioVO.getSenha();
            usuarioService.autenticar(cpf, senha);
            String token = JwtUtil.create(cpf);
            usuarioVO = new UsuarioVO();
            usuarioVO.setToken(token);
            ResponseEntity<UsuarioVO> responseEntity = new ResponseEntity<>(usuarioVO, HttpStatus.OK);
            return responseEntity;
        } catch (UsuarioOuSenhaInvalidaException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}