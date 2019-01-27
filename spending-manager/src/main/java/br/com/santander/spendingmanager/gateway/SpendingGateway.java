package br.com.santander.spendingmanager.gateway;

import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.gateway.cassandra.SpendingRepository;
import br.com.santander.spendingmanager.gateway.http.json.SpendingCategoryJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    public Spending create(final Spending spending) {
        spending.setId(UUID.randomUUID());
        return spendingRepository.save(spending);
    }

    public void delete(final UUID id) {
        spendingRepository.deleteById(id);
    }

    public List<Spending> findSpendingsByDate(final LocalDateTime initialDate, final LocalDateTime finalDate, final int userCode) {
        return spendingRepository.findSpendingsByDateIsGreaterThanAndDateIsLessThanAndUserCodeEquals(initialDate, finalDate, userCode);
    }

    public void saveCategory(final SpendingCategoryJson spendingCategoryJson) {
        spendingRepository.findById(spendingCategoryJson.getId())
                .ifPresent(s -> updateCategory(spendingCategoryJson, s));
    }

    private void updateCategory(final SpendingCategoryJson spendingCategoryJson, Spending spending) {
        spending.setCategory(spendingCategoryJson.getCategory());
        spendingRepository.save(spending);
    }
}
