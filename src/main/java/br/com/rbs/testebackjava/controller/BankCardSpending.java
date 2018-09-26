package br.com.rbs.testebackjava.controller;

import br.com.rbs.testebackjava.domain.Spending;
import br.com.rbs.testebackjava.producer.SpendingProducer;
import br.com.rbs.testebackjava.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-card-spending")
public class BankCardSpending {

    @Autowired
    private SpendingProducer spendingProducer;

    @Autowired
    private SpendingRepository spendingRepository;

    @PostMapping(value = "/")
    public ResponseEntity<Spending> insert(@RequestBody String spending) {
        spendingProducer.send(spending);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/", params = {"id", "page", "size"})
    public List<Spending> listByUser(@RequestParam("id") Long id,
                                     @RequestParam("page") int page,
                                     @RequestParam("size") int size) {

        Pageable pageRequest = new PageRequest(page, size);

        Page<Spending> resultPage = spendingRepository.findByCodigoUsuario(id, pageRequest);
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException();
        }
        return resultPage.getContent();
    }
}
