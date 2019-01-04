package br.com.santander.gastos.sugestaocategorias.service;

import br.com.santander.gastos.sugestaocategorias.dto.AdicionarCategoriaRequest;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDTO;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDocument;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriasCliente;
import br.com.santander.gastos.sugestaocategorias.mappers.CategoriaMapper;
import br.com.santander.gastos.sugestaocategorias.repository.CategoriasClienteRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleField;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.solr.core.query.Criteria.where;

@Service
public class CategoriaCommandServiceImpl implements CategoriaCommandService {

    private CategoriasClienteRepository categoriasClienteRepository;

    private SolrTemplate solrTemplate;

    private CategoriaMapper categoriaMapper = Mappers.getMapper(CategoriaMapper.class);

    @Autowired
    public CategoriaCommandServiceImpl(CategoriasClienteRepository categoriasClienteRepository, SolrTemplate solrTemplate) {
        this.categoriasClienteRepository = categoriasClienteRepository;
        this.solrTemplate = solrTemplate;
    }

    @Override
    public CategoriaDTO adicionarCategoria(AdicionarCategoriaRequest adicionarCategoriaRequest) {

        CategoriasCliente categorias = new CategoriasCliente();
        categorias.setId(1L);
        List<CategoriaDocument> c = new ArrayList<>();
        CategoriaDocument d = new CategoriaDocument();
        d.setId("finanças");
        d.setDescricoes(Arrays.asList("pagamento cartão", "pagamento divida", "conta atrasada"));
        c.add(d);
        categorias.setCategorias(c);

        categoriasClienteRepository.save(categorias);

        Query query = new SimpleQuery(where("id").is(adicionarCategoriaRequest.getIdCliente()));
        query.addProjectionOnField(new SimpleField("*"));
        query.addProjectionOnField(new SimpleField(String.format("[child parentFilter=id:%d]", adicionarCategoriaRequest.getIdCliente())));

        final Optional<CategoriasCliente> books = solrTemplate.queryForObject("categorias", query, CategoriasCliente.class);

        return null;
    }
}
