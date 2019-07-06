package br.com.testesantanderway.service;

import br.com.testesantanderway.config.security.AutenticacaoViaTokenFilter;
import br.com.testesantanderway.dto.GastoDTO;
import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    private void integrarCategoria(Gasto gasto){
        if(gasto.getCategoria() == null){
            Optional<String> categoria = gastoRepository.findCategoriaByDescricao(gasto.getDescricao());
            if(categoria.isPresent()){
                gasto.setCategoria(categoria.get());
            }
        }
    }
}
