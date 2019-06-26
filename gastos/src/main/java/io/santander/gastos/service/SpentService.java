package io.santander.gastos.service;

import io.santander.gastos.commons.DateParser;
import io.santander.gastos.domain.Spent;
import io.santander.gastos.dto.SpentDTO;
import io.santander.gastos.mapper.SpentMapper;
import io.santander.gastos.repository.SpentRepository;
import io.santander.gastos.vo.GastoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SpentService {

    private final SpentRepository spentRepository;
    private final SpentMapper spentMapper;
    private final DateParser dateParser;

    public PageImpl<SpentDTO> buscaTodosOsGastoPorCliente(final Long userCode, String cardNumber, final GastoVO vo, final Pageable pageable) {
        Page<Spent> spentPage = null;
        spentPage = spentRepository.findAllWithFilters(
                userCode,
                cardNumber,
                Optional.ofNullable(vo.getDescricao()).orElse(null),
                Optional.ofNullable(vo.getValor()).orElse(null),
                Optional.ofNullable(vo.getData()).orElse(null),
                pageable);
        return new PageImpl<>(this.spentMapper.toDTOList(spentPage.getContent()), pageable, spentPage.getTotalElements());
    }

    public List<SpentDTO> getAllspents() {
        return spentMapper.toDTOList(spentRepository.findAll());
    }
}
