package io.santander.gastos.service;

import io.santander.gastos.dto.ClassificationDTO;
import io.santander.gastos.mapper.ClassificationMapper;
import io.santander.gastos.repository.ClassificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClassificationService {

    private final ClassificationRepository classificationRepository;
    private final ClassificationMapper classificationMapper;

    @Cacheable("classification")
    public List<ClassificationDTO> getAllClassificatios(String text) {
        return classificationMapper.toDTOList(classificationRepository.findByText("%" + text + "%"));
    }
}
