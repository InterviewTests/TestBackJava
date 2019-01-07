package br.com.santander.gastos.query.listener;

import br.com.santander.gastos.query.dto.GastosDTO;
import br.com.santander.gastos.query.entity.GastoEntity;
import br.com.santander.gastos.query.mappers.GastosMapper;
import br.com.santander.gastos.query.repository.GastoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FilaListenerImpl implements FilaListener {

    private GastoRepository gastoRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private GastosMapper gastosMapper = Mappers.getMapper(GastosMapper.class);

    @Autowired
    public FilaListenerImpl(GastoRepository gastoRepository){
        this.gastoRepository = gastoRepository;
    }

    @Override
    public void receiveMessage(String mensagem) throws IOException {
        final GastosDTO gastosDTO = objectMapper.readValue(mensagem, GastosDTO.class);
        final GastoEntity save = gastoRepository.save(gastosMapper.dtoToEntity(gastosDTO));
    }
}