package io.santander.gastos.mapper;

import io.santander.gastos.commons.AbstractMapper;
import io.santander.gastos.domain.Classification;
import io.santander.gastos.dto.ClassificationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassificationMapper extends AbstractMapper<Classification, ClassificationDTO> {

    @Override
    public ClassificationDTO toDTO(Classification entity) {
        return super.toDTO(entity);
    }

    @Override
    public Classification toEntity(ClassificationDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<ClassificationDTO> toDTOList(List<Classification> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public List<Classification> toEntityList(List<ClassificationDTO> dto) {
        return super.toEntityList(dto);
    }


}
