package br.com.zup.way.db.solr.repository;

import br.com.zup.way.db.solr.model.Gasto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface GastoRepository extends SolrCrudRepository<Gasto, String> {

    List<Gasto> findGastoByDescricaoIgnoreCaseAndCodigoUsuario(String descricao, Long codigoUsuario);

    List<Gasto> findGastoByCodigoUsuarioAndDataCadastroLessThanEqual(Long codigoUsuario, String dataLimite, PageRequest dataCadastro);

    List<Gasto> findGastoByCodigoUsuarioAndDataCadastroBetween(Long codigoUsuario, String dataLimiteInicio, String dataLimiteFim, Sort dataCadastro);

    List<Gasto> findGastoByCategoriaContainingIgnoreCaseAndCodigoUsuarioOrderByCategoria(String nomeCategoria, Long codigoUsuario);

}
