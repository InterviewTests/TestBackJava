package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.entity.GastoEntity;
import br.com.santander.gastos.integracao.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GastosCommandServiceImpl implements GastosCommandService {

    private final GastoRepository gastoRepository;

    @Autowired
    public GastosCommandServiceImpl(GastoRepository gastoRepository){
        this.gastoRepository = gastoRepository;
    }

    @Override
    public void adicionarGasto(AdicionarGastoRequest adicionarGastoRequest) {
        GastoEntity g = new GastoEntity();

        g.setCodigoUsuario(adicionarGastoRequest.getCodigoUsuario());
        g.setData(adicionarGastoRequest.getData());
        g.setDescricao(adicionarGastoRequest.getDescricao());
        g.setValor(adicionarGastoRequest.getValor());

        GastoEntity save = gastoRepository.save(g);
    }
}
