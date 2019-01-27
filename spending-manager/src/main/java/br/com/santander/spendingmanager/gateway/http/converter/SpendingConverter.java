package br.com.santander.spendingmanager.gateway.http.converter;

import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.gateway.http.json.SpendingItemResponseJson;
import br.com.santander.spendingmanager.gateway.http.json.SpendingResponseJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SpendingConverter implements Converter<List<Spending>, SpendingResponseJson> {

    @Override
    public SpendingResponseJson convert(final List<Spending> spendings) {
        final List<SpendingItemResponseJson> items = new ArrayList<>();

        spendings.forEach(spending -> {
            final SpendingItemResponseJson spendingItem =  SpendingItemResponseJson.builder()
                    .category(spending.getCategory())
                    .date(spending.getDate())
                    .description(spending.getDescription())
                    .id(spending.getId())
                    .value(spending.getValue())
                    .userCode(spending.getUserCode())
                    .build();
            items.add(spendingItem);
        });

        return SpendingResponseJson
                .builder().items(items).build();
    }
}
