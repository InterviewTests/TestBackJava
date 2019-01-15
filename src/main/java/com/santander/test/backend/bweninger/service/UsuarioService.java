package com.santander.test.backend.bweninger.service;

import com.santander.test.backend.bweninger.exception.SenhasDiferentesException;
import com.santander.test.backend.bweninger.exception.UsuarioOuSenhaInvalidaException;
import com.santander.test.backend.bweninger.model.Usuario;
import com.santander.test.backend.bweninger.vo.UsuarioVO;

import java.util.Optional;

/**
 * Created by BWeninger on 10/01/2019.
 */
public interface UsuarioService {

    Optional<Usuario> obterPorCpf(String cpf);

    Boolean autenticar(String cpf, String senha) throws UsuarioOuSenhaInvalidaException;

    void criar(UsuarioVO usuarioVO) throws SenhasDiferentesException;
}
