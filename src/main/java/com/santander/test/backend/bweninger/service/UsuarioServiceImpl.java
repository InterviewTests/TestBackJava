package com.santander.test.backend.bweninger.service;

import com.santander.test.backend.bweninger.exception.SenhasDiferentesException;
import com.santander.test.backend.bweninger.exception.UsuarioOuSenhaInvalidaException;
import com.santander.test.backend.bweninger.model.Usuario;
import com.santander.test.backend.bweninger.repository.UsuarioRepository;
import com.santander.test.backend.bweninger.util.CryptoUtil;
import com.santander.test.backend.bweninger.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * Created by BWeninger on 14/01/2019.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> obterPorCpf(String cpf) {
        return usuarioRepository.findById(cpf);
    }

    @Override
    public Boolean autenticar(String cpf, String senha) throws UsuarioOuSenhaInvalidaException {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(cpf);
            if(!usuario.isPresent() || !usuario.get().getSenha().equals(CryptoUtil.criptografarSenha(senha))){
                throw new UsuarioOuSenhaInvalidaException();
            } else {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            return false;
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    @Override
    public void criar(UsuarioVO usuarioVO) throws SenhasDiferentesException {
        if(CryptoUtil.validarSenha(usuarioVO.getSenha(), usuarioVO.getRepeteSenha())){
            try {
                    Usuario usuario = new Usuario();
                    usuario.setAtivo(true);
                    usuario.setEmail(usuarioVO.getEmail());
                    usuario.setNome(usuarioVO.getNome());
                    usuario.setSenha(CryptoUtil.criptografarSenha(usuarioVO.getSenha()));
                    usuario.setCpf(usuarioVO.getCpf());
                    usuarioRepository.save(usuario);
            } catch (UnsupportedEncodingException e) {
                return;
            } catch (NoSuchAlgorithmException e) {
                return;
            }
        } else {
            throw new SenhasDiferentesException();
        }
    }


}
