package br.com.santander.spendingmanager.gateway.http.converter;

import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.gateway.http.json.SpendingRequestJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpendingRequestConverter implements Converter<SpendingRequestJson, Spending> {

    @Override
    public Spending convert(final SpendingRequestJson spendingRequestJson) {

        return Spending.builder()
                .description(spendingRequestJson.getDescription())
                .userCode(spendingRequestJson.getUserCode())
                .value(spendingRequestJson.getValue())
                .date(spendingRequestJson.getDate())
                .build();
    }
}
