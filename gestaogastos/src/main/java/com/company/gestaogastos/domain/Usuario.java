package com.company.gestaogastos.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.company.gestaogastos.domain.dto.UsuarioDTO;
import com.company.gestaogastos.domain.repository.UsuarioRepository;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	
	@Transient
	private UsuarioRepository usuarioRepository;
	
	static final int USUARIOS_PAGE_SIZE = 4;
	
//    @OneToMany(mappedBy="gasto")
//    //@JoinColumn(name="fk_usuariogasto")
//    private List<Gasto> gastos = new ArrayList<Gasto>();

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Usuario(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	
	
	
	
	
	public UsuarioDTO retrieveUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findById(this.getId());

		return toDTO(usuario.get());
	}

	public Page<UsuarioDTO> retrieveUsuarios(Map<String, String> allRequestParams) {
		Page<Usuario> usuarios;
		PageRequest pageRequest = getPageRequest(allRequestParams);
		if (allRequestParams.get("nome") != null) {
			String nomeParam = allRequestParams.get("nome");
			usuarios = usuarioRepository.findByNome(nomeParam, pageRequest);
		} else {
			usuarios = usuarioRepository.findAllUsuario(pageRequest);
		}
		return convertPageUsuarioToPageUsuarioDTO(usuarios);
	}

	public void deleteUsuario() {
		usuarioRepository.deleteById(this.getId());
	}

	public UsuarioDTO createUsuario() {
		Usuario savedUsuario = usuarioRepository.save(this);

		return toDTO(savedUsuario);
	}
	
	public UsuarioDTO updateUsuario() {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (!usuarioOptional.isPresent())
			return null;

		Usuario usuarioBanco = usuarioRepository.save(this);

		return toDTO(usuarioBanco);
	}

	public PageRequest getPageRequest(Map<String, String> allRequestParams) {
		Integer offset = 0;
		Integer limit = USUARIOS_PAGE_SIZE;
		if (allRequestParams.get("offset") != null) {
			offset = Integer.decode(allRequestParams.get("offset"));
		}
		if (allRequestParams.get("limit") != null) {
			limit = Integer.decode(allRequestParams.get("limit"));
		}
		return PageRequest.of(offset, limit, new Sort(Sort.Direction.ASC,"nome"));
	}
	
	public void toDomain(UsuarioDTO usuario) {
		if (usuario != null) {
			this.id = usuario.getId();
			this.nome = usuario.getNome();
		}
	}

	public UsuarioDTO toDTO(Usuario usuario) {
		UsuarioDTO dto = null;
		if (usuario != null) {
			dto = new UsuarioDTO();
			dto.setId(usuario.getId());
			dto.setNome(usuario.getNome());
		}
		return dto;
	}

	private Page<UsuarioDTO> convertPageUsuarioToPageUsuarioDTO(Page<Usuario> usuarios) {
		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		usuarios.getContent().forEach(usuario-> {
			usuarioDTOList.add(toDTO(usuario));
		});
		Page<UsuarioDTO> pageUsuarioDTO = new PageImpl<UsuarioDTO>(usuarioDTOList, usuarios.getPageable(), usuarios.getContent().size());
		return pageUsuarioDTO;
	}


	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

//	public List<Gasto> getGastos() {
//		return gastos;
//	}
//
//	public void setGastos(List<Gasto> gastos) {
//		this.gastos = gastos;
//	}

}
