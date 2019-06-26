package io.santander.gastos.service;

import io.santander.gastos.dto.GastoDTO;
import io.santander.gastos.mapper.GastosMapper;
import io.santander.gastos.repository.GastoRepository;
import io.santander.gastos.vo.GastoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GastosService {

    private final GastoRepository gastoRepository;
    private final GastosMapper gastosMapper;

    public PageImpl<GastoDTO> buscaTodosOsGastoPorCliente(final Long codigoUsuario, final GastoVO vo, final Pageable pageable) {
        return null;
    }

    public List<GastoDTO> getAll
}
