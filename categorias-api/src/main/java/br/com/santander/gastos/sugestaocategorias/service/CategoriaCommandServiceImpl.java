package br.com.santander.gastos.sugestaocategorias.service;

import br.com.santander.gastos.sugestaocategorias.dto.AdicionarCategoriaRequest;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDocument;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriasCliente;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriasClienteDTO;
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
    public CategoriasClienteDTO adicionarCategoria(AdicionarCategoriaRequest adicionarCategoriaRequest) {

        Query query = new SimpleQuery(where("id").is(adicionarCategoriaRequest.getIdCliente()));
        query.addProjectionOnField(new SimpleField("*"));
        query.addProjectionOnField(new SimpleField(String.format("[child parentFilter=id:%d]", adicionarCategoriaRequest.getIdCliente())));

        final Optional<CategoriasCliente> optionalCategoriasDoCliente = solrTemplate.queryForObject("categorias", query, CategoriasCliente.class);

        CategoriasCliente categoriasCliente;

        final String novaDescricao = adicionarCategoriaRequest.getDescricao();
        final String novaCategoria = adicionarCategoriaRequest.getCategoria();

        if(optionalCategoriasDoCliente.isPresent()){

            categoriasCliente = optionalCategoriasDoCliente.get();

            final Optional<CategoriaDocument> c = categoriasCliente.getCategorias()
                    .stream()
                    .filter(categoriaDocument -> novaCategoria.equals(categoriaDocument.getId()))
                    .findFirst();

            c.ifPresent(categoriaDocument -> {
                final Optional<String> desc = categoriaDocument.getDescricoes()
                        .stream()
                        .filter(descricao -> descricao.equals(descricao))
                        .findFirst();

                if(!desc.isPresent()){
                    categoriaDocument.getDescricoes().add(novaDescricao);
                }
            });

        } else {
            categoriasCliente = new CategoriasCliente();
            categoriasCliente.setId(adicionarCategoriaRequest.getIdCliente());
            List<CategoriaDocument> cd = new ArrayList<>();

            CategoriaDocument d = new CategoriaDocument();
            d.setId(adicionarCategoriaRequest.getCategoria());
            d.getDescricoes().add(novaDescricao);
            cd.add(d);
            categoriasCliente.setCategorias(cd);
        }

        return categoriaMapper.entityToDTO(categoriasClienteRepository.save(categoriasCliente));
    }
}
