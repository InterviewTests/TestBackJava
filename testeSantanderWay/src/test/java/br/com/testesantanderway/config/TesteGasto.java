package br.com.testesantanderway.config;

import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.service.GastoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
public class TesteGasto {

    @Mock
    private GastoService service;

    private Gasto gasto;

    private String codigoUsuario;

    @BeforeEach
    public void setup(){
        this.codigoUsuario = UUID.randomUUID().toString();
        this.gasto = new Gasto();
        this.gasto.setDataCriacao(LocalDateTime.now());
        this.gasto.setDescricao("Teste Unitario");
        this.gasto.setValor(10.0);
        this.gasto.setCodigoUsuario(this.codigoUsuario);
        this.gasto.setCodigoSistema(UUID.randomUUID().toString());
    }

    @DisplayName("Testa o Serviço de Lançamento de Gasto")
    @Test
    public void testarLancamentoDeGasto(){
        service.lancarGastosCartao(this.gasto);
    }

    @DisplayName("Testa o Serviço de Listagem de Gastos")
    @Test
    public void testarListagemDeGastos(){
        service.listarGastosMaisRecentes(this.codigoUsuario);
    }

    @DisplayName("Testa o Serviço que encontra os Gastos de um determinado dia")
    @Test
    public void testarEncontrarGastosDoDia(){
        service.encontrarGastosDoDia(codigoUsuario, LocalDate.now(), Pageable.unpaged());
    }

}
