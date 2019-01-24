package br.com.santander.spendingmanager.gateway;

import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.gateway.cassandra.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SpendingGateway {

    private SpendingRepository spendingRepository;

    @Autowired
    public SpendingGateway(SpendingRepository spendingRepository) {
        this.spendingRepository = spendingRepository;
    }

    public List<Spending> findAll() {
        List<Spending> spendings = new ArrayList<>();
        spendingRepository.findAll().forEach(spendings::add);
        return spendings;
    }

    public Spending saveOrUpdate(Spending spending) {
        spending.setId(UUID.randomUUID());
        return spendingRepository.save(spending);
    }

    public void delete(UUID id) {
        spendingRepository.deleteById(id);
    }
}
