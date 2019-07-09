package br.com.testesantanderway.service;

import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    public void lancar(Gasto gasto) {
        integrarCategoria(gasto);
        gastoRepository.save(gasto);
    }

    public List<Gasto> listarGastosMaisRecentes(String codigoUsuario) {
        ZonedDateTime ultimosCincoSegundos = ZonedDateTime.now().minusSeconds(5);
        return gastoRepository.findByCodigoUsuarioAndDataCriacaoAfter(codigoUsuario, ultimosCincoSegundos.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
    }

    public Page<Gasto> encontrarGastosDoDia(String codigoUsuario, LocalDateTime dia, Pageable paginacao) {
        ZonedDateTime inicio = dia.toLocalDate().atStartOfDay(ZoneOffset.UTC).plusSeconds(1);
        ZonedDateTime fim = dia.toLocalDate().plusDays(1).atStartOfDay(ZoneOffset.UTC).minusSeconds(1);
        return gastoRepository.findByCodigoUsuarioAndDataCriacaoBetween(codigoUsuario, inicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")),
                fim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")), paginacao);
    }

    public void categorizarGasto(Gasto gasto) {
        Optional<Gasto> categoriaASerAlterada = gastoRepository.findById(gasto.getCodigoUsuario());
        if (!categoriaASerAlterada.isPresent() || categoriaASerAlterada.get().getCategoria() != null) {
            throw new IllegalArgumentException("Operação não permitida");
        }
        categoriaASerAlterada.get().setCategoria(gasto.getCategoria());
        gastoRepository.save(categoriaASerAlterada.get());
    }

    private void integrarCategoria(Gasto gasto) {
        if (gasto.getCategoria() == null) {
            Optional<String> categoria = gastoRepository.findCategoriaByDescricao(gasto.getDescricao());
            if (categoria.isPresent()) {
                gasto.setCategoria(categoria.get());
            }
        }
    }
}