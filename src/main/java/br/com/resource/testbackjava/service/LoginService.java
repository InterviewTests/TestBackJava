package br.com.resource.testbackjava.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resource.testbackjava.data.User;
import br.com.resource.testbackjava.data.UserRepository;
import br.com.resource.testbackjava.exception.MensagemTratadaException;
import br.com.resource.testbackjava.util.CryptoUtilsService;
import br.com.resource.testbackjava.vo.LoginVO;
import br.com.resource.testbackjava.vo.UserVO;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	private static final String ADMIN = "ADMIN";
	
	public UserVO login(LoginVO login) throws MensagemTratadaException {

		User user = userRepository.findByUserCode(login.getCodigoUsuario());
		if (user == null) {
			throw new MensagemTratadaException("Usuario não encontrado");
		}

		String encrytSenha = CryptoUtilsService.encrypt(login.getSenha());

		if (!encrytSenha.equals(user.getSenha())) {
			throw new MensagemTratadaException("Senha não confere");
		}

		UserVO result = new UserVO(user.getCodigoUsuario(), user.getNome(), true, ADMIN.equalsIgnoreCase(user.getNome()));
		return result;

	}
}
