package com.santander.test.backend.bweninger.service;

import com.santander.test.backend.bweninger.repository.CategoriaRepository;
import com.santander.test.backend.bweninger.vo.CategoriaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by BWeninger on 11/01/2019.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    public CategoriaServiceImpl() {
    }

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Set<CategoriaVO> sugerirCategorias(String categoriaSubstr) {
        return categoriaRepository.findAll().stream().filter(
                c -> c.getDescricao().startsWith(categoriaSubstr)
        ).map(c -> {
                    CategoriaVO vo = new CategoriaVO();
                    vo.setDescricao(c.getDescricao());
                    return vo;
                }
        ).collect(Collectors.toSet());
    }
}
