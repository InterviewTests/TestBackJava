package br.com.zup.way.controllers;

import br.com.zup.way.db.solr.model.dto.FiltroCategorizarGastoDTO;
import br.com.zup.way.db.solr.model.dto.FiltroGastoDTO;
import br.com.zup.way.db.solr.model.dto.GastoDTO;
import br.com.zup.way.db.solr.model.dto.IntegrateGastoDTO;
import br.com.zup.way.security.JwtHelper;
import br.com.zup.way.service.GastoService;
import br.com.zup.way.util.Exception.WayBusinessException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class GastoController {

    private GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @PutMapping(path = "/sys/api/gasto/integracao")
    public GastoDTO integrate(@RequestBody @Valid IntegrateGastoDTO gastoDTO) throws WayBusinessException {
        return gastoService.integrateGasto(gastoDTO);
    }

    @PutMapping(path = "/user/api/gasto/categorizar")
    public GastoDTO categorizarGasto(@RequestBody @Valid FiltroCategorizarGastoDTO filtro) throws WayBusinessException {
        return gastoService.categorizarGasto(filtro);
    }

    @GetMapping(path = "/user/api/gasto/last")
    public List<GastoDTO> listLast() {
        return gastoService.findLastGastos(JwtHelper.getCodigoUsuario());
    }

    @PostMapping(path = "/user/api/gasto/find")
    public List<GastoDTO> find(@RequestBody @Valid FiltroGastoDTO filtroData) {
        return gastoService.findGastosByDate(JwtHelper.getCodigoUsuario(), filtroData);
    }

    @GetMapping(path = "/user/api/gasto/categoria/{nomeCategoria}")
    public Set<String> suggestCategoria(@PathVariable("nomeCategoria") String nomeCategoria) {
        return gastoService.findCategoria(nomeCategoria);
    }

}
