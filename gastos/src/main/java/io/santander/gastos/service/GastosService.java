package io.santander.gastos.service;

import io.santander.gastos.dto.GastoDTO;
import io.santander.gastos.repository.GastoRepository;
import io.santander.gastos.vo.GastosVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
public class GastosService {

    private final GastoRepository gastoRepository;

    public PageImpl<GastoDTO> buscaTodosOsGastoPorCliente(final Long codigoUsuario, final GastosVO vo, final Pageable pageable) {
        return gastoRepository.buscaTodosOsGastoPorCliente(codigoUsuario,vo.getDescricao(),vo.getValor(),vo.getData(),pageable);
    }
}
