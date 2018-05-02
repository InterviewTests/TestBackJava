package com.santander.gastos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.santander.gastos.domain.Categoria;
import com.santander.gastos.domain.Cliente;
import com.santander.gastos.domain.Movimento;
import com.santander.gastos.dto.GastosDTO;
import com.santander.gastos.repositories.CategoriaRepository;
import com.santander.gastos.repositories.ClienteRepository;
import com.santander.gastos.repositories.MovimentoRepository;
import com.santander.gastos.services.exceptions.ObjectNotFoundException;

@Service
public class MovimentoService {

	@Autowired
	private MovimentoRepository repo;

	@Autowired
	private CategoriaRepository catRepo;

	@Autowired
	private ClienteRepository clirepo;

	public Movimento buscarPorId(Integer id) {

		Optional<Movimento> mov = repo.findById(id);

		return mov.orElse(null);
	}

	public Page<Movimento> findMovimentoByCodCli(Long codCli, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findMovimentoByCodCli(codCli, pageRequest);
	}

	public Page<Movimento> findMovimentoByCodCliAndDate(Long codCli, Date data, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findMovimentoByCodCliAndDate(codCli, data, pageRequest);
	}

	public Movimento fromDTO(@Valid GastosDTO objDto) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date data = sdf.parse(objDto.getData());

		Movimento mov = new Movimento(null, objDto.getDescricao(), objDto.getValor(), data);
		
		Cliente cli = clirepo.findByCodigoUsuario(objDto.getCodigoCliente());

		if (cli == null) {
			throw new ObjectNotFoundException("Cliente não encontrado! Codigo: " + objDto.getCodigoCliente());
		}

		mov.setCartao(cli.getContas().get(0).getCartoes().get(0));

		if(objDto.getCategoria() != null) {
			Categoria cat = catRepo.findById(objDto.getCategoria().getId())
						.orElse(new Categoria(objDto.getCategoria().getNome()));
			mov.setCategoria(cat);
		}
		
		return mov;
	}

	public Movimento insert(Movimento obj) {
		obj.setId(null);
		
		//Busca uma categoria para asignação automática
		List<Movimento> movMesmaDescr = repo.findByDescricao(obj.getDescricao()).orElse(null);
		
		if( movMesmaDescr != null)
			obj.setCategoria(movMesmaDescr.get(0).getCategoria());
		
		obj = repo.save(obj);
		return obj;
	}

	public Movimento update(GastosDTO dto) {
		Movimento obj = repo.findById(dto.getId()).orElse(null);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Movimento de gasto não encontrado! Id: " + dto.getId());
		}
		
		updateData(dto, obj);
		return repo.save(obj);
	}

	private void updateData(GastosDTO dto, Movimento obj) {
		// Único campo permitido para alteração.
		obj.setCategoria(catRepo.findById(dto.getCategoria().getId()).orElse(null));

	}

}
