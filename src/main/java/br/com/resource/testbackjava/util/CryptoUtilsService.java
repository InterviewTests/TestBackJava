package br.com.resource.testbackjava.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.resource.testbackjava.exception.MensagemTratadaException;

@Service
public class CryptoUtilsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoUtilsService.class);

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";
	private static Charset CHAR_SET = Charset.forName("ISO-8859-1");

	private static String key = "1234567890123456";

	static {

		try {
			Properties prop = new Properties();
			prop.load(CryptoUtilsService.class.getClassLoader().getResourceAsStream("database.properties"));

			key = prop.getProperty("spring.data.cassandra,key-encrypt");
		} catch (IOException e) {
			LOGGER.warn("Erro ao adquirir chave de criptografia. Usando default", e);
		}
	}

	public static String encrypt(String content) throws MensagemTratadaException {
		try {
			Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(CHAR_SET), 16), ALGORITHM);

			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] outputBytes = cipher.doFinal(content.getBytes(CHAR_SET));

			return new String(outputBytes, CHAR_SET);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			throw new MensagemTratadaException("Erro no serviço de senha.");
		}
	}

	public static String decrypt(String content) throws MensagemTratadaException {
		try {
			Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(CHAR_SET), 16), ALGORITHM);

			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			byte[] outputBytes = cipher.doFinal(content.getBytes(CHAR_SET));

			return new String(outputBytes);

		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			throw new MensagemTratadaException("Erro no serviço de senha.");
		}

	}
}
