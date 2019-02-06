package br.com.gestao.gastos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.gestao.gastos.model.Gastos;

public interface GastosRepository extends MongoRepository<Gastos, String> {

}
