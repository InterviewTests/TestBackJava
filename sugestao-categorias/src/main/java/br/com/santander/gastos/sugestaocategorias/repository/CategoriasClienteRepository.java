package br.com.santander.gastos.sugestaocategorias.repository;

import br.com.santander.gastos.sugestaocategorias.dto.CategoriasCliente;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface CategoriasClienteRepository extends SolrCrudRepository<CategoriasCliente, Long> {



}