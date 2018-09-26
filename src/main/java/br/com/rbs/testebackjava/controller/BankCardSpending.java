package br.com.rbs.testebackjava.controller;

import br.com.rbs.testebackjava.domain.Spending;
import br.com.rbs.testebackjava.producer.SpendingProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank-card-spending")
public class BankCardSpending {

    @Autowired
    private SpendingProducer spendingProducer;

    @PostMapping(value = "/")
    public ResponseEntity<Spending> update(@RequestBody String spending) {
        spendingProducer.send(spending);
        return new ResponseEntity(HttpStatus.OK);
    }
}
