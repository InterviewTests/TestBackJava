package com.santander.test.backend.bweninger.controller;

import com.santander.test.backend.bweninger.service.CategoriaService;
import com.santander.test.backend.bweninger.vo.CategoriaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by BWeninger on 10/01/2019.
 */
@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/api/categorias", method = RequestMethod.GET)
    public ResponseEntity<Set<CategoriaVO>> sugerirCategorias(@RequestParam(value = "categoria") String categoriaSubstr){
        return new ResponseEntity<>(service.sugerirCategorias(categoriaSubstr), HttpStatus.OK);
    }
}
