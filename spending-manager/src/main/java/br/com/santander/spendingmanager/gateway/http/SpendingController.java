package br.com.santander.spendingmanager.gateway.http;

import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.gateway.http.converter.SpendingRequestConverter;
import br.com.santander.spendingmanager.gateway.http.json.SpendingRequestJson;
import br.com.santander.spendingmanager.usecases.SpendingUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController("/spending")
public class SpendingController {

    private SpendingUseCase spendingUseCase;
    private SpendingRequestConverter spendingRequestConverter;

    private final Logger log = LoggerFactory.getLogger(SpendingController.class);

    @Autowired
    public SpendingController(SpendingUseCase spendingUseCase, SpendingRequestConverter spendingRequestConverter) {
        this.spendingUseCase = spendingUseCase;
        this.spendingRequestConverter = spendingRequestConverter;
    }

    @PostMapping
    public ResponseEntity saveSpending(@Valid @RequestBody SpendingRequestJson spendingRequestJson) {
        log.info("POST on /spending with {}", spendingRequestJson.toString());

        Spending spending = spendingRequestConverter.convert(spendingRequestJson);
        spendingUseCase.saveSpending(spending);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
