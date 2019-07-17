package io.santander.gastos.web.controller;

import io.santander.gastos.dto.SpentDTO;
import io.santander.gastos.service.DateUTCParser;
import io.santander.gastos.service.SpentService;
import io.santander.gastos.vo.GastoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(SpentController.SPENTS_ENDPOINT)
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SpentController {

    public static final String SPENTS_ENDPOINT = "/spent";

    private final SpentService spentService;
    private final DateUTCParser dateParser;

    @PostMapping("/{numeroCartao}")
    String addSpent(@Valid @PathVariable("numeroCartao") final String numeroCartao, final GastoVO vo) {
        return spentService.saveSpent(numeroCartao, vo);
    }

    @GetMapping("/user/{codigoUsuario}")
    @Cacheable("spents")
    PageImpl<GastoVO> getAllSpents(@Valid @PathVariable final Long codigoUsuario, @RequestParam final String numeroCartão, final GastoVO vo, Pageable pageable) {
        Page<SpentDTO> dtoPage = spentService.buscaTodosOsGastoPorCliente(codigoUsuario, numeroCartão, vo, pageable);
        return new PageImpl<>(dtoPage.getContent().stream().map(this::toVo).collect(Collectors.toList()), pageable, dtoPage.getTotalElements());
    }

    @GetMapping("/user/{codigoUsuario}/{codigoGasto}")
    @Cacheable("spent-datail")
    GastoVO getSpentDatail(@Valid @PathVariable final Long codigoUsuario, @Valid @PathVariable final Long codigoGasto, @RequestParam final String numeroCartão) {
        return toVo(spentService.getSpentDetail(codigoUsuario, codigoGasto, numeroCartão));
    }

    @PutMapping("/user/{codigoUsuario}/{codigoGasto}")
    GastoVO updateSpentDatail(@Valid @PathVariable final Long codigoUsuario, @Valid @PathVariable final Long codigoGasto, @RequestParam final String numeroCartão, @RequestParam(required = false) final String description, @RequestParam(required = false) final Long classification) {
        spentService.updateSpendDatail(codigoUsuario, codigoGasto, numeroCartão, description, classification);
        return null;
    }

    private GastoVO toVo(SpentDTO spentDTO) {
        return GastoVO.builder()
                .id(spentDTO.getId())
                .codigoUsuario(spentDTO.getUserCode())
                .descricao(spentDTO.getDescription())
                .data(dateParser.toUtcDate(spentDTO.getSpentDate()))
                .valor(spentDTO.getSpentValue())
                .classificacao(spentDTO.getClassification().getName())
                .build();
    }
}
