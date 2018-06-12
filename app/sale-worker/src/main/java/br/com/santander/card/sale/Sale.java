package br.com.santander.card.sale;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Sale {
	@Id
	@Getter
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Getter @Setter
	@Column(nullable=false)
	private String descricao;
	
	@Getter @Setter
	@Column(nullable=false)
	private double valor;
	
	@Getter @Setter
	@Column(nullable=false)
	private Long codigousuario;
	
	@Getter @Setter
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private Date data;
	
	@Getter @Setter
	@Column(nullable=true)
	private String categoria;
}
