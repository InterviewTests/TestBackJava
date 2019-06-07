package com.santander.gastosapi.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.santander.gastosapi.model.Gasto;

public interface GastosRepository extends SolrCrudRepository<Gasto, Long>{
}
