package io.santander.gastos.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ClassificationController.ClASSIFICATION_ENDPOINT)
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClassificationController {
    public static final String ClASSIFICATION_ENDPOINT = "/classicacao";

    @GetMapping
    List<String> buscaCassificacao() {
        return null;
    }
}
