package io.santander.gastos.web.controller;

import io.santander.gastos.service.ClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(ClassificationController.ClASSIFICATION_ENDPOINT)
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClassificationController {
    public static final String ClASSIFICATION_ENDPOINT = "/classification";

    private final ClassificationService classificationService;

    @GetMapping
    Set<String> buscaCassificacao(@RequestParam @Valid String searchText) {
        return classificationService.getAllClassificatios(searchText);
    }
}
