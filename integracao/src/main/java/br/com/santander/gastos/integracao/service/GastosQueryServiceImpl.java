package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.entity.GastoEntity;
import br.com.santander.gastos.integracao.entity.QGastoEntity;
import br.com.santander.gastos.integracao.mappers.GastosMapper;
import br.com.santander.gastos.integracao.repository.GastoRepository;
import com.querydsl.core.BooleanBuilder;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GastosQueryServiceImpl implements GastosQueryService {

    private GastosMapper gastosMapper = Mappers.getMapper(GastosMapper.class);

    private final GastoRepository gastoRepository;

    @Autowired
    public GastosQueryServiceImpl(GastoRepository gastoRepository){
        this.gastoRepository = gastoRepository;
    }

    @Override
    public Page<GastosDTO> consultarGastos(Long codigoUsuario, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        QGastoEntity qGasto = QGastoEntity.gastoEntity;

        builder.and(qGasto.data.between(LocalDateTime.now().minusSeconds(5), LocalDateTime.now()));
        builder.and(qGasto.codigoUsuario.eq(codigoUsuario));

        final Page<GastoEntity> page = gastoRepository.findAll(builder, pageable);

        final List<GastosDTO> dtoList = page.get()
                .map(gastosMapper::entityToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }
}
