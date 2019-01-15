package com.santander.test.backend.bweninger.service;

import com.santander.test.backend.bweninger.exception.UsuarioOuSenhaInvalidaException;
import com.santander.test.backend.bweninger.vo.CategoriaVO;
import com.santander.test.backend.bweninger.vo.GastoVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by BWeninger on 10/01/2019.
 */
public interface GastoService {

    List<GastoVO> listarMeusGastos(String cpf);

    List<GastoVO> filtrarGastosPorData(String cpf, LocalDateTime data);

    void cadastrarCategoria(UUID idGasto, CategoriaVO categoriaVO, String cpf) throws UsuarioOuSenhaInvalidaException;

    void cadastrarGastos(List<GastoVO> gastos, String cpf);
}
