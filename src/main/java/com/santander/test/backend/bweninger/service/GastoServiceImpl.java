package com.santander.test.backend.bweninger.service;

import com.datastax.driver.core.utils.UUIDs;
import com.santander.test.backend.bweninger.exception.UsuarioOuSenhaInvalidaException;
import com.santander.test.backend.bweninger.model.Categoria;
import com.santander.test.backend.bweninger.model.Gasto;
import com.santander.test.backend.bweninger.model.GastoPorData;
import com.santander.test.backend.bweninger.model.Usuario;
import com.santander.test.backend.bweninger.repository.CategoriaRepository;
import com.santander.test.backend.bweninger.repository.GastoPorDataRepository;
import com.santander.test.backend.bweninger.repository.GastoRepository;
import com.santander.test.backend.bweninger.repository.UsuarioRepository;
import com.santander.test.backend.bweninger.vo.CategoriaVO;
import com.santander.test.backend.bweninger.vo.GastoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by BWeninger on 11/01/2019.
 */
@Service
public class GastoServiceImpl implements GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private GastoPorDataRepository gastoPorDataRepository;

    @Override
    public List<GastoVO> listarMeusGastos(String cpf) {
        return filtrarGastosPorData(cpf, null);
    }

    @Override
    public void cadastrarCategoria(UUID idGasto, CategoriaVO categoriaVO, String cpf) throws UsuarioOuSenhaInvalidaException {
        Optional<Usuario> usuario = usuarioRepository.findById(cpf);
        if (usuario.isPresent()) {
            Optional<Gasto> gasto = gastoRepository.findByIdAndCpf(idGasto, cpf);
            if (gasto.isPresent()) {
                gasto.get().setCategoria(categoriaVO.getDescricao());
                gastoRepository.save(gasto.get());
                Optional<Categoria> maybeCategoria = categoriaRepository.findByDescricaoAndCpfUsuario(categoriaVO.getDescricao(), cpf);
                if (maybeCategoria.isPresent()) {
                    maybeCategoria.get().getGastos().add(gasto.get().getId());
                } else {
                    Categoria c = new Categoria();
                    c.setDescricao(categoriaVO.getDescricao());
                    c.setCpfUsuario(usuario.get().getCpf());
                    c.setGastos(Arrays.asList(gasto.get().getId()));
                    maybeCategoria = Optional.of(c);
                }
                categoriaRepository.save(maybeCategoria.get());
            }
        } else {
            throw new UsuarioOuSenhaInvalidaException();
        }
    }

    @Override
    public List<GastoVO> filtrarGastosPorData(String cpf, LocalDateTime data) {
        Optional<Usuario> u = usuarioRepository.findById(cpf);
        if (u.isPresent()) {
            if (data == null) {
                return this.gastoRepository.findByCpf(u.get().getCpf()).stream().map(
                        g -> new GastoVO(g.getId(), g.getDescricao(), g.getValor(), g.getData())
                ).collect(Collectors.toList());
            } else {
                LocalDateTime dataIni = data.withHour(0).withMinute(0).withSecond(0).withNano(0);
                LocalDateTime dataFim = data.withHour(23).withMinute(59).withSecond(59).withNano(0);
                return this.gastoPorDataRepository.findByCpfndDate(u.get().getCpf(), dataIni, dataFim).stream().map(
                        g -> {
                            return gastoRepository.findByIdAndCpf(g.getIdGasto(), g.getCpfUsuario()).map(
                                    h -> {
                                        return new GastoVO(h.getId(), h.getDescricao(), h.getValor(), h.getData());
                                    }).get();
                        }).collect(Collectors.toList());
            }
        } else {
            return null;
        }
    }

    @Override
    public void cadastrarGastos(List<GastoVO> gastos, String cpf) {
        gastos.stream().map(g -> {
            Gasto gasto = new Gasto();
            gasto.setDescricao(g.getDescricao());
            gasto.setData(g.getData());
            gasto.setCpfUsuario(cpf);
            gasto.setValor(g.getValor());
            gasto.setId(UUIDs.random());
            return gasto;
        }).forEach(
                g -> {
                    g.setCategoria(categorizar(g, cpf));
                    GastoPorData gd = new GastoPorData();
                    gd.setCpfUsuario(cpf);
                    gd.setData(g.getData());
                    gd.setIdGasto(g.getId());
                    gastoRepository.save(g);
                    gastoPorDataRepository.save(gd);
                    Optional<Categoria> maybeCategoria = categoriaRepository.findByDescricaoAndCpfUsuario(g.getCategoria(), cpf);
                    if (maybeCategoria.isPresent()) {
                        maybeCategoria.get().getGastos().add(g.getId());
                    } else {
                        Categoria c = new Categoria();
                        c.setDescricao(g.getCategoria());
                        c.setCpfUsuario(cpf);
                        c.setGastos(Arrays.asList(g.getId()));
                        maybeCategoria = Optional.of(c);
                    }
                    categoriaRepository.save(maybeCategoria.get());
                });
    }

    private String categorizar(Gasto gasto, String cpf) {
        List<Gasto> gastos = this.gastoRepository.findByCpf(cpf);
        Optional<String> maybeCategoria = gastos.stream().filter(g -> g.getDescricao().equals(
                gasto.getDescricao())).map(g -> g.getCategoria()).findFirst();
        return maybeCategoria.orElse("Outros");
    }
}
