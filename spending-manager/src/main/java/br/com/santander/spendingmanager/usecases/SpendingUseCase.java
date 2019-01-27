package br.com.santander.spendingmanager.usecases;

import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.domain.Category;
import br.com.santander.spendingmanager.gateway.SpendingGateway;
import br.com.santander.spendingmanager.gateway.http.json.SpendingCategoryJson;
import br.com.santander.spendingmanager.gateway.rabbit.producer.SpendingRabbitProducerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class SpendingUseCase {

    private SpendingRabbitProducerGateway spendingRabbitProducerGateway;
    private SpendingGateway spendingGateway;
    private CategoryUseCase categoryUseCase;

    private final int DECREMENT_FACTOR_IN_SECONDS = 360;

    @Autowired
    public SpendingUseCase(SpendingRabbitProducerGateway spendingRabbitProducerGateway,
                           SpendingGateway spendingGateway,
                           CategoryUseCase categoryUseCase)
    {
        this.spendingRabbitProducerGateway = spendingRabbitProducerGateway;
        this.spendingGateway = spendingGateway;
        this.categoryUseCase = categoryUseCase;
    }

    public void saveSpending(Spending spending) {
        spendingRabbitProducerGateway.sendMessage(spending);
    }

    public void updateCategory(SpendingCategoryJson spendingCategoryJson) {
        spendingGateway.saveCategory(spendingCategoryJson);

        Category category = Category.builder()
                .category(spendingCategoryJson.getCategory())
                .build();

        categoryUseCase.saveCategory(category);
    }

    public List<Spending> findSpendingsByUserCode(final int userCode) {
        LocalDateTime initialDate = LocalDateTime.now().minusSeconds(DECREMENT_FACTOR_IN_SECONDS);
        LocalDateTime finalDate = LocalDateTime.now();

        return spendingGateway.findSpendingsByDate(initialDate, finalDate, userCode);
    }

    public List<Spending> findSpendingsByDateAndUserCode(final LocalDate date, final int userCode) {
        LocalDateTime initialDate = date.atStartOfDay();
        LocalDateTime finalDate = date.atTime(LocalTime.MAX);

        return spendingGateway.findSpendingsByDate(initialDate, finalDate, userCode);
    }
}
