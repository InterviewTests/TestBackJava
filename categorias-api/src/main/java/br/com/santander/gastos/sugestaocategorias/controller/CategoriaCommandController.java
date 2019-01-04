package br.com.santander.gastos.sugestaocategorias.controller;

import br.com.santander.gastos.sugestaocategorias.dto.AdicionarCategoriaRequest;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDTO;
import br.com.santander.gastos.sugestaocategorias.service.CategoriaCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/categorias/v1")
public class CategoriaCommandController {

    private final CategoriaCommandService categoriaCommandService;

    @Autowired
    public CategoriaCommandController(CategoriaCommandService categoriaCommandService) {
        this.categoriaCommandService = categoriaCommandService;
    }

    @PostMapping(value = "/")
    public CategoriaDTO adicionarCategoria(
            @RequestBody @Valid AdicionarCategoriaRequest adicionarGastoRequest
    ) {
        return categoriaCommandService.adicionarCategoria(adicionarGastoRequest);
    }

}
