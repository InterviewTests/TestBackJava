package br.com.zup.way.service.impl;

import br.com.zup.way.db.solr.model.Gasto;
import br.com.zup.way.db.solr.model.dto.FiltroCategorizarGastoDTO;
import br.com.zup.way.db.solr.model.dto.FiltroGastoDTO;
import br.com.zup.way.db.solr.model.dto.GastoDTO;
import br.com.zup.way.db.solr.model.dto.IntegrateGastoDTO;
import br.com.zup.way.db.solr.repository.GastoRepository;
import br.com.zup.way.security.JwtHelper;
import br.com.zup.way.service.GastoService;
import br.com.zup.way.service.UsuarioService;
import br.com.zup.way.util.DateUtil;
import br.com.zup.way.util.Exception.WayBusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GastoServiceImpl implements GastoService {

    private GastoRepository gastoRepository;

    private UsuarioService usuarioService;

    public GastoServiceImpl(GastoRepository gastoRepository, UsuarioService usuarioService) {
        this.gastoRepository = gastoRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public GastoDTO integrateGasto(IntegrateGastoDTO gastoDTO) throws WayBusinessException {
        validateCodigoUsuario(gastoDTO.getCodigoUsuario());
        Gasto gasto = Gasto.convert(gastoDTO);
        if (gasto.getDataCadastro() == null) {
            gasto.setDataCadastro(DateUtil.setZone(LocalDateTime.now()));
        }
        gasto.setCategoria(findCategoriaByGasto(gastoDTO));
        return GastoDTO.convert(gastoRepository.save(gasto));
    }

    @Override
    public GastoDTO categorizarGasto(FiltroCategorizarGastoDTO filtro) throws WayBusinessException {
        Gasto gasto = findById(filtro.getIdGasto());
        if (StringUtils.isNotBlank(gasto.getCategoria())) {
            throw new WayBusinessException("Esse gasto já foi categorizado");
        }
        gasto.setCategoria(filtro.getCategoria());
        return GastoDTO.convert(gastoRepository.save(gasto));
    }

    @Override
    public List<GastoDTO> findLastGastos(Long codigoUsuario) {
        return GastoDTO.convert(
                gastoRepository.findGastoByCodigoUsuarioAndDataCadastroLessThanEqual(
                        codigoUsuario, DateUtil.format(LocalDateTime.now().minusSeconds(5)),
                        PageRequest.of(0, 15,
                                Sort.by(Sort.Order.desc("dataCadastro"), Sort.Order.asc("descricao")))
                )
        );
    }

    @Override
    public List<GastoDTO> findGastosByDate(Long codigoUsuario, FiltroGastoDTO filtroData) {
        return GastoDTO.convert(
                gastoRepository.findGastoByCodigoUsuarioAndDataCadastroBetween(
                        codigoUsuario,
                        DateUtil.format(DateUtil.getStartDate(filtroData.getFiltroData())),
                        DateUtil.format(DateUtil.getEndDate(filtroData.getFiltroData())),
                        Sort.by(Sort.Order.desc("dataCadastro"))
                )
        );
    }

    @Override
    public Set<String> findCategoria(String nomeCategoria) {
        List<Gasto> gastos =
                gastoRepository.findGastoByCategoriaContainingIgnoreCaseAndCodigoUsuarioOrderByCategoria(nomeCategoria, JwtHelper.getCodigoUsuario());
        return gastos.stream().map(Gasto::getCategoria).collect(Collectors.toSet());
    }

    /**
     * Private Methods
     */

    private void validateCodigoUsuario(Long codigoUsuario) throws WayBusinessException {
        usuarioService.findClienteByCodigoUsuario(codigoUsuario);
    }

    private String findCategoriaByGasto(IntegrateGastoDTO gastoDTO) {
        List<Gasto> gastos =
                gastoRepository.findGastoByDescricaoIgnoreCaseAndCodigoUsuario(gastoDTO.getDescricao(), gastoDTO.getCodigoUsuario());
        return gastos.stream().findFirst().orElse(new Gasto()).getCategoria();
    }

    private Gasto findById(String idGasto) throws WayBusinessException {
        return gastoRepository.findById(idGasto).orElseThrow(() -> new WayBusinessException("Gasto não Encontrado."));
    }
}
