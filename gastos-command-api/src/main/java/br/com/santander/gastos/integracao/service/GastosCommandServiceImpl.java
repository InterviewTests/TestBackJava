package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.CategorizarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.entity.GastoEntity;
import br.com.santander.gastos.integracao.exceptions.GastoNaoEncontradoException;
import br.com.santander.gastos.integracao.mappers.GastosMapper;
import br.com.santander.gastos.integracao.repository.GastoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class GastosCommandServiceImpl implements GastosCommandService {

    private GastosMapper gastosMapper = Mappers.getMapper(GastosMapper.class);

    private final GastoRepository gastoRepository;

    @Autowired
    public GastosCommandServiceImpl(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    @Override
    public GastosDTO adicionarGasto(AdicionarGastoRequest adicionarGastoRequest) {
        GastoEntity g = gastosMapper.requestToEntity(adicionarGastoRequest);

        return gastosMapper.entityToDTO(gastoRepository.save(g));
    }

    @Override
    public GastosDTO categorizarGasto(CategorizarGastoRequest categorizarGastoRequest) {
        final Optional<GastoEntity> gastoEntity = gastoRepository.findById(categorizarGastoRequest.getId());
        final GastoEntity gasto = gastoEntity.orElseThrow(GastoNaoEncontradoException::new);

        gasto.setCategoria(categorizarGastoRequest.getCategoria());

        final GastoEntity save = gastoRepository.save(gasto);



        return gastosMapper.entityToDTO(save);
    }
}
