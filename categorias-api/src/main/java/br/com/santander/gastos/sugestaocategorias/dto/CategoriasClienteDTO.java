package br.com.santander.gastos.sugestaocategorias.dto;

import java.util.List;

public class CategoriasClienteDTO {
    private Long id;

    private List<CategoriaDTO> categorias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }
}
