package br.com.testesantanderway.service;

import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    public void lancarGastosCartao(Gasto gasto) {
        integrarCategoria(gasto);
        gastoRepository.save(gasto);
    }

    public List<Gasto> listarGastosMaisRecentes(String codigoUsuario) {
        LocalDateTime ultimosCincoSegundos = LocalDateTime.now().minusSeconds(5);
        return gastoRepository.findByCodigoUsuarioAndDataCriacaoAfter(codigoUsuario, ultimosCincoSegundos);
    }

    public Page<Gasto> encontrarGastosDoDia(String codigoUsuario, LocalDateTime dia, Pageable paginacao){
        return gastoRepository.findByCodigoUsuarioAndDataCriacao(codigoUsuario, dia, paginacao);
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
