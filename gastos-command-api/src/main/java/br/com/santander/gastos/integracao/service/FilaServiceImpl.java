package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.GastosDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FilaServiceImpl implements FilaService {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Qualifier("filaGastos")
    private Queue queue;

    @Autowired
    public FilaServiceImpl(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @Override
    public void enviarMensagem(GastosDTO gastosDTO) throws JsonProcessingException {
        final String mensagem = objectMapper.writeValueAsString(gastosDTO);
        rabbitTemplate.convertAndSend(queue.getName(), mensagem);
    }
}
