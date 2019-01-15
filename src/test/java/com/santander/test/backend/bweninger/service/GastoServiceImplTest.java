package com.santander.test.backend.bweninger.service;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by BWeninger on 11/01/2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class GastoServiceImplTest {

    @Mock
    private GastoRepository gastoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private GastoPorDataRepository gastoPorDataRepository;

    @InjectMocks
    private GastoServiceImpl gastoService;

    @Captor
    private ArgumentCaptor<Gasto> gastoArgumentCaptor;

    @Captor
    private ArgumentCaptor<Categoria> categoriaArgumentCaptor;

    @Test
    public void testListarMeusGastos(){
        String cpf = "123.456.789-00";

        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);

        List<Gasto> resultSet = new ArrayList<>();
        Gasto g1 = new Gasto();
        g1.setDescricao("Carrefour");
        g1.setValor(new BigDecimal(222.50));
        g1.setId(UUID.randomUUID());
        g1.setCpfUsuario(cpf);
        g1.setData(LocalDateTime.now());
        Gasto g2 = new Gasto();
        g2.setDescricao("Playstation Network");
        g2.setValor(new BigDecimal(123.00));
        g2.setId(UUID.randomUUID());
        g2.setCpfUsuario(cpf);
        g2.setData(LocalDateTime.now());
        resultSet.add(g1);
        resultSet.add(g2);
        when(usuarioRepository.findById(cpf)).thenReturn(Optional.of(usuario));
        when(gastoRepository.findByCpf(cpf)).thenReturn(resultSet);

        List<GastoVO> gastos = gastoService.listarMeusGastos(cpf);

        assertNotNull(gastos);
        assertEquals(gastos.get(0).getValor(), g1.getValor());
        assertEquals(gastos.get(0).getDescricao(), g1.getDescricao());
        assertEquals(gastos.get(0).getIdGasto(), g1.getId());
        assertEquals(gastos.get(0).getData(), g1.getData());
        assertEquals(gastos.get(1).getValor(), g2.getValor());
        assertEquals(gastos.get(1).getDescricao(), g2.getDescricao());
        assertEquals(gastos.get(1).getIdGasto(), g2.getId());
        assertEquals(gastos.get(1).getData(), g2.getData());
    }

    @Test
    public void testFiltrarGastosPorData(){
        String cpf = "123.456.789-00";

        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);

        List<GastoPorData> resultSet = new ArrayList<>();
        GastoPorData gpd1 = new GastoPorData();
        gpd1.setIdGasto(UUID.randomUUID());
        gpd1.setCpfUsuario(cpf);
        gpd1.setData(LocalDateTime.now());
        resultSet.add(gpd1);

        Gasto g = new Gasto();
        g.setId(gpd1.getIdGasto());
        g.setValor(new BigDecimal(123.00));
        g.setDescricao("Carrefour");
        g.setData(gpd1.getData());

        LocalDateTime dataIni = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime dataFim = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(0);
        when(usuarioRepository.findById(cpf)).thenReturn(Optional.of(usuario));
        when(gastoPorDataRepository.findByCpfndDate(cpf, dataIni, dataFim)).thenReturn(resultSet);
        when(gastoRepository.findByIdAndCpf(gpd1.getIdGasto(), cpf)).thenReturn(Optional.of(g));

        List<GastoVO> gastos = gastoService.filtrarGastosPorData(cpf, LocalDateTime.now());

        assertNotNull(gastos);
        assertEquals(gastos.size(), 1);
        assertEquals(gastos.get(0).getValor(), g.getValor());
        assertEquals(gastos.get(0).getDescricao(), g.getDescricao());
        assertEquals(gastos.get(0).getIdGasto(), g.getId());
        assertEquals(gastos.get(0).getData(), g.getData());
    }

    @Test
    public void testCadastrarCateogriaExistente(){
        Gasto g = new Gasto();
        UUID idGasto = UUID.randomUUID();
        g.setId(idGasto);

        CategoriaVO categoriaVO = new CategoriaVO();
        categoriaVO.setDescricao("Alimentacao");
        categoriaVO.setIdCategoria(UUID.randomUUID());
        String cpf = "123.456.789-00";

        Usuario u = new Usuario();
        u.setCpf(cpf);

        Categoria c = new Categoria();
        c.setDescricao(categoriaVO.getDescricao());
        c.setGastos(new ArrayList<>());

        when(usuarioRepository.findById(cpf)).thenReturn(Optional.of(u));
        when(gastoRepository.findByIdAndCpf(idGasto, u.getCpf())).thenReturn(Optional.of(g));
        when(categoriaRepository.findByDescricaoAndCpfUsuario(categoriaVO.getDescricao(), cpf)).thenReturn(Optional.of(c));

        try {
            gastoService.cadastrarCategoria(idGasto, categoriaVO, cpf);
            verify(gastoRepository).save(gastoArgumentCaptor.capture());
            assertEquals(gastoArgumentCaptor.getValue().getCategoria(), categoriaVO.getDescricao());
            verify(categoriaRepository).save(categoriaArgumentCaptor.capture());
            assertTrue(categoriaArgumentCaptor.getValue().getGastos().contains(idGasto));

        } catch (UsuarioOuSenhaInvalidaException e) {
            fail();
        }
    }

    @Test
    public void testCadastrarCateogriaNova(){
        Gasto g = new Gasto();
        UUID idGasto = UUID.randomUUID();
        g.setId(idGasto);

        CategoriaVO categoriaVO = new CategoriaVO();
        categoriaVO.setDescricao("Alimentacao");

        String cpf = "123.456.789-00";

        Usuario u = new Usuario();
        u.setCpf(cpf);

        when(usuarioRepository.findById(cpf)).thenReturn(Optional.of(u));
        when(gastoRepository.findByIdAndCpf(idGasto, u.getCpf())).thenReturn(Optional.of(g));

        try {
            gastoService.cadastrarCategoria(idGasto, categoriaVO, cpf);
            verify(gastoRepository).save(gastoArgumentCaptor.capture());
            assertEquals(gastoArgumentCaptor.getValue().getCategoria(), categoriaVO.getDescricao());
            verify(categoriaRepository).save(categoriaArgumentCaptor.capture());
            assertTrue(categoriaArgumentCaptor.getValue().getGastos().contains(idGasto));

        } catch (UsuarioOuSenhaInvalidaException e) {
            fail();
        }
    }

    @Test
    public void testCadastrarGastosSimples(){
        String cpf = "123.456.789-00";
        GastoVO g1 = new GastoVO(null, "Posto Shell", new BigDecimal(80.00), LocalDateTime.now());
        GastoVO g2 = new GastoVO(null, "Pao de Acucar", new BigDecimal(153.00), LocalDateTime.now());
        GastoVO g3 = new GastoVO(null, "Curso de Ingles", new BigDecimal(1200.00), LocalDateTime.now());
        List<GastoVO> input = Arrays.asList(g1, g2, g3);

        gastoService.cadastrarGastos(input, cpf);

        verify(gastoRepository, times(3)).save(any(Gasto.class));
    }

    @Test
    public void testCadastrarGastosCategorizacaoAutomatica(){
        String cpf = "123.456.789-00";
        GastoVO g1 = new GastoVO(null, "Carrefour", new BigDecimal(80.00), LocalDateTime.now());
        GastoVO g2 = new GastoVO(null, "Curso de Ingles", new BigDecimal(1200.00), LocalDateTime.now());
        List<GastoVO> input = Arrays.asList(g1, g2);

        Gasto entity = new Gasto();
        entity.setDescricao("Carrefour");
        entity.setCategoria("Alimentação");
        entity.setId(UUID.randomUUID());
        List<Gasto> gastosEntity = Arrays.asList(entity);

        Categoria c = new Categoria();
        c.setDescricao("Alimentação");
        c.setCpfUsuario(cpf);
        c.addGasto(entity.getId());

        when(gastoRepository.findByCpf(cpf)).thenReturn(gastosEntity);
        when(categoriaRepository.findByDescricaoAndCpfUsuario("Alimentação", cpf)).thenReturn(Optional.of(c));
        gastoService.cadastrarGastos(input, cpf);

        verify(gastoRepository, times(2)).save(gastoArgumentCaptor.capture());
        verify(categoriaRepository, times(2)).save(categoriaArgumentCaptor.capture());

        List<Gasto> gastos = gastoArgumentCaptor.getAllValues();
        List<Categoria> categorias = categoriaArgumentCaptor.getAllValues();

        assertNotNull(gastos.get(0).getCategoria());
        assertEquals(gastos.get(0).getCategoria(), "Alimentação");
        assertTrue(categorias.get(0).getGastos().size() > 1);

        assertNotNull(gastos.get(1).getCategoria());
        assertEquals(gastos.get(1).getCategoria(), "Outros");
        assertTrue(categorias.get(1).getGastos().size() > 0);
    }
}
