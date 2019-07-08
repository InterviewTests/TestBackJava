package io.santander.gastos.service;

import io.santander.gastos.repository.ClassificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClassificationService {

    private final ClassificationRepository classificationRepository;

    @Cacheable("classification")
    public Set<String> getAllClassificatios(String text) {
        return classificationRepository.findByText("%"+text+"%");

    }
}
