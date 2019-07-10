package io.santander.gastos.service;

import io.santander.gastos.dto.ClassificationDTO;
import io.santander.gastos.mapper.ClassificationMapper;
import io.santander.gastos.repository.ClassificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClassificationService {

    private final ClassificationRepository classificationRepository;
    private final SpentService spentService;
    private final ClassificationMapper classificationMapper;

    public List<ClassificationDTO> getAllClassificatios(String text) {
        return classificationMapper.toDTOList(classificationRepository.findByText("%" + text + "%"));
    }

    @Transactional
    public void getClassificationByDescription(final Long userCode, final String description) {
        spentService.getSpentClassification(userCode, description);
    }

    public ClassificationDTO getClassification(final List<Long> cards, final String description) {
        return classificationMapper.toDTO(classificationRepository.findSpendClassification(cards, description));
    }

    public ClassificationDTO getById(Long id) {
        return classificationMapper.toDTO(classificationRepository.findById(id).get());
    }
}
