package br.com.resource.testbackjava.data;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

import br.com.resource.testbackjava.exception.MensagemTratadaException;
import br.com.resource.testbackjava.util.CryptoUtilsService;

@Table
public class User {

	
	private UUID id;
	@PrimaryKey
	private Integer codigoUsuario;
	private String nome;
	private String senha;
	
	public User() {}
	
	public User(Integer codigoUsuario, String nome, String senha) throws MensagemTratadaException {
		this.id = UUIDs.timeBased();
		this.codigoUsuario = codigoUsuario;
		this.nome = nome;
		this.senha = CryptoUtilsService.encrypt(senha);
	}

	public UUID getId() {
		return id;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", codigousuario=" + codigoUsuario + ", nome=" + nome + ", senha=" + senha + "]";
	}
	
	
}
