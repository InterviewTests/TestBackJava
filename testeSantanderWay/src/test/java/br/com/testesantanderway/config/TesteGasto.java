package br.com.testesantanderway.config;

import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.service.GastoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
public class TesteGasto {

    @Mock
    private GastoService service;

    private Gasto gasto;

    @BeforeEach
    public void setup(){
        this.gasto = new Gasto();
        this.gasto.setDataCriacao(LocalDateTime.now());
        this.gasto.setDescricao("Teste Unitario");
        this.gasto.setValor(10.0);
        this.gasto.setCodigoUsuario(UUID.randomUUID().toString());
        this.gasto.setCodigoSistema(UUID.randomUUID().toString());
    }

    @DisplayName("Testa o Serviço de Lançamento de Gasto")
    @Test
    public void testarLancamentoDeGasto(){
        service.lancarGastosCartao(this.gasto);
    }

}
