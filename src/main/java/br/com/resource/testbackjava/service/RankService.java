package br.com.resource.testbackjava.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resource.testbackjava.data.Rank;
import br.com.resource.testbackjava.data.RankRepository;

@Service
public class RankService {

	@Autowired
	private RankRepository repository;

	/**
	 * Inclui ou atualiza o contador registro de categoria <br>
	 * Utilizar o método no lugar de save do repository
	 * 
	 * @param ranks
	 * @return
	 */
	public Rank save(Rank rank) {

		Rank opt = null;

		if (rank.getCategoria() != null) {
			List<Rank> tmp = this.repository.obterCategoriasPorIDDescricao(rank.getCategoria(), rank.getDescricao());
			opt = tmp != null && !tmp.isEmpty() ? tmp.get(0) : null;
		}

		if (opt == null) {
			rank.setCounter(1L);
		} else {
			rank = opt;
			rank.setCounter(rank.getCounter() + 1);
		}

		return repository.save(rank);
	}

	/**
	 * Inclui ou atualiza o contador registro de categoria em lote<br>
	 * Utilizar o método no lugar de saveAll do repository
	 * 
	 * @param ranks
	 * @return
	 */
	public List<Rank> saveAll(List<Rank> ranks) {

		// List<String> categorias = ranks.stream().map(f -> f.getCategoria()).collect(Collectors.toList());

		Set<String> ids = new HashSet<>();
		for (Rank r : ranks) {
			ids.add(r.getCategoria());
		}
		List<Rank> finds = this.repository.findAllById(ids);

		ranks.removeAll(finds);
		for (Rank r : ranks) {
			r.setCounter(1L);
		}

		for (Rank r : finds) {
			r.setCounter(r.getCounter() + 1);
		}

		Set<Rank> result = new HashSet<>();
		result.addAll(ranks);
		result.addAll(finds);

		return repository.saveAll(result);
	}

	
	public Set<Rank> listarCategoriasUtilizadasPorDescricao (String descricao) {
		Set<Rank> result = new TreeSet<>();
		
		List<Rank> lista = repository.listarCategoriasUtilizadasPorDescricao(descricao);
		result.addAll(lista);
		
		return result;
	}
}
