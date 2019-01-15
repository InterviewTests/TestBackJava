package com.santander.test.backend.bweninger.service;

import com.santander.test.backend.bweninger.vo.CategoriaVO;

import java.util.Set;

/**
 * Created by BWeninger on 10/01/2019.
 */
public interface CategoriaService {

    Set<CategoriaVO> sugerirCategorias(String categoriaSubstr);
}
