package io.santander.gastos.web.controller;

import io.santander.gastos.dto.SpentDTO;
import io.santander.gastos.service.SpentService;
import io.santander.gastos.vo.GastoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(GastosController.GASTOS_ENDPOINT)
@Validated
public class GastosController {

    public static final String GASTOS_ENDPOINT = "/gastos";

    private SpentService gastosService;

    @GetMapping("/{codigoUsuario}")
    PageImpl<GastoVO> buscaTodosOsGastoPorCliente(@Valid @PathVariable final Long codigoUsuario, final GastoVO vo, Pageable pageable) {
        Page<SpentDTO> dtoPage = gastosService.buscaTodosOsGastoPorCliente(codigoUsuario, vo, pageable);
        return new PageImpl<>(dtoPage.getContent().stream().map(this::toVo).collect(Collectors.toList()), pageable, dtoPage.getTotalElements());
    }

    private GastoVO toVo(SpentDTO gastoDTO) {
        return GastoVO.builder()
                .codigoUsuario(gastoDTO.getCodigoUsuario())
                .descricao(gastoDTO.getDescricao())
                .data(gastoDTO.getData())
                .valor(gastoDTO.getValor())
                .build();
    }
}
