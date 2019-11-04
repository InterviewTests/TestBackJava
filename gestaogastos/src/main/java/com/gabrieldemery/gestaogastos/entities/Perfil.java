package com.gabrieldemery.gestaogastos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="tb_perfil")
public class Perfil implements GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;
	private String perfil;

	@Override
	public String getAuthority() {
		return this.perfil;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("Perfil[")
				.append("codigo="+this.codigo+",")
				.append("perfil="+this.perfil)
				.append("]")
				.toString();
	}

}
