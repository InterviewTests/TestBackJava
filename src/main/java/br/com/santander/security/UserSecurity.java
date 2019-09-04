package br.com.santander.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.santander.enums.Perfil;

public class UserSecurity implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String username;
	private String password;
	private Integer codigoUsuario;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSecurity() {
	}
	
	
	
	public UserSecurity(String uuid, String username, String password, Set<Perfil> perfis, Integer codigousuario) {
		this.uuid = uuid;
		this.username = username;
		this.password = password;
		this.codigoUsuario = codigousuario;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());  
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUuid() {
		return uuid;
	}



	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}



	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

}
