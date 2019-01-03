package br.com.santander.gastos.sugestaocategorias.service;

import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDocument;
import br.com.santander.gastos.sugestaocategorias.repository.CategoriasDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaQueryServiceImpl implements CategoriaQueryService {

    private CategoriasDocumentRepository categoriasDocumentRepository;

    @Autowired
    public CategoriaQueryServiceImpl(CategoriasDocumentRepository categoriasClienteRepository, SolrTemplate solrTemplate) {
        this.categoriasDocumentRepository = categoriasClienteRepository;
    }

    @Override
    public Page<String> consultarTodasCategorias(String query, Pageable pageable) {
        final Page<CategoriaDocument> documentos = categoriasDocumentRepository.consultarCategorias(query, pageable);

        final List<String> categorias = documentos
                .getContent()
                .stream()
                .map(CategoriaDocument::getId)
                .collect(Collectors.toList());

        return new PageImpl<>(categorias, pageable, documentos.getTotalElements());
    }
}
