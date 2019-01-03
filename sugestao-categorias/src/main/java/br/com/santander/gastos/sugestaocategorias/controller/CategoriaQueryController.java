package br.com.santander.gastos.sugestaocategorias.controller;

import br.com.santander.gastos.sugestaocategorias.service.CategoriaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/api/v1")
public class CategoriaQueryController {

    private final CategoriaQueryService categoriaQueryService;

    @Autowired
    public CategoriaQueryController(CategoriaQueryService categoriaQueryService) {
        this.categoriaQueryService = categoriaQueryService;
    }

    @GetMapping(value = "/categorias")
    public Page<String> consultarCategorias(
            @RequestParam("nome") String nome,
            Pageable pageable
    ) {
        Page<String> strings = null;

        if (nome.length() < 3) {
            strings = new PageImpl<>(new ArrayList<>());
        } else {
            strings = categoriaQueryService.consultarTodasCategorias(nome, pageable);
        }

        return strings;
    }

}
