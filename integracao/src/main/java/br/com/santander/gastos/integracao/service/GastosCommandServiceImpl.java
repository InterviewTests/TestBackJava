package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.entity.GastoEntity;
import br.com.santander.gastos.integracao.mappers.GastosMapper;
import br.com.santander.gastos.integracao.repository.GastoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GastosCommandServiceImpl implements GastosCommandService {

    private GastosMapper gastosMapper = Mappers.getMapper(GastosMapper.class);

    private final GastoRepository gastoRepository;

    @Autowired
    public GastosCommandServiceImpl(GastoRepository gastoRepository){
        this.gastoRepository = gastoRepository;
    }

    @Override
    public GastosDTO adicionarGasto(AdicionarGastoRequest adicionarGastoRequest) {
        GastoEntity g = new GastoEntity();

        g.setCodigoUsuario(adicionarGastoRequest.getCodigoUsuario());
        g.setData(adicionarGastoRequest.getData());
        g.setDescricao(adicionarGastoRequest.getDescricao());
        g.setValor(adicionarGastoRequest.getValor());

        return gastosMapper.entityToDTO(gastoRepository.save(g));
    }
}
