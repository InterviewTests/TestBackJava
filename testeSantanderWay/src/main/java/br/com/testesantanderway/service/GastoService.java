package br.com.testesantanderway.service;

import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.*;
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
        return gastoRepository.findByCodigoUsuarioAndDataCriacaoAfter(codigoUsuario, Date.from(ultimosCincoSegundos.toInstant()));
    }

    public Page<Gasto> encontrarGastosDoDia(String codigoUsuario, LocalDate dia, Pageable paginacao){
        ZonedDateTime inicio = dia.atStartOfDay(ZoneOffset.UTC).plusSeconds(1);
        ZonedDateTime fim = dia.plusDays(1).atStartOfDay(ZoneOffset.UTC).minusSeconds(1);
        return gastoRepository.findByCodigoUsuarioAndDataCriacaoBetween(codigoUsuario, Date.from(inicio.toInstant()), Date.from(fim.toInstant()), paginacao);
    }

    public void categorizarGasto(Gasto gasto) {
        Optional<Gasto> categoriaASerAlterada = gastoRepository.findById(gasto.getCodigoUsuario());
        if(!categoriaASerAlterada.isPresent() || categoriaASerAlterada.get().getCategoria() != null){
            throw new IllegalArgumentException("Operação não permitida");
        }
        categoriaASerAlterada.get().setCategoria(gasto.getCategoria());
        gastoRepository.save(categoriaASerAlterada.get());
    }

    private void integrarCategoria(Gasto gasto){
        if(gasto.getCategoria() == null){
            Optional<String> categoria = gastoRepository.findCategoriaByDescricao(gasto.getDescricao());
            if(categoria.isPresent()){
                gasto.setCategoria(categoria.get());
            }
        }
    }
}
