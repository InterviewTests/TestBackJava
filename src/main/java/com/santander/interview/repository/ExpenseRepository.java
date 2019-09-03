package com.santander.interview.repository;

import com.santander.interview.domain.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
    public List<Expense> findByCodigoUsuario(long codigoUsuario);
    public List<Expense> findByCodigoUsuarioAndDataBetween(long codigoUsuario, Date startData, Date endData);
}
