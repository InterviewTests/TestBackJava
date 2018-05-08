package br.com.santander.app.support;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtHelper {

	public static DecodedJWT getDecoder(final String token) throws JWTVerificationException {
		JWTVerifier verifier = null;
		DecodedJWT decoder = null;

		verifier = JWT.require(algorithm()).build();
		decoder = verifier.verify(token);

		return decoder;
	}

	public static Builder getBuilder() {
		final Builder builder = JWT.create();
		return builder;
	}

	public static Algorithm algorithm() {
		try {
			return Algorithm.HMAC256(secret());

		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			throw new InternalError();
		}
	}

	private static String secret() {
		return "aasdooiqwkqwkj#sasdlkasdklj";
	}

	public static String createHash(final String subject, final String issuer, final Date expiresAt, final Map<String, Object> header) {
		Builder builder = JWT.create();
		builder = builder.withIssuer(issuer);
		builder = builder.withAudience(issuer);
		builder = builder.withSubject(subject);
		builder = builder.withIssuedAt(Date.from(Instant.now()));


		if(Objects.nonNull(expiresAt)) {
			builder = builder.withExpiresAt(expiresAt);
		}

		if(Objects.nonNull(header)) {
			builder = builder.withHeader(header);
		}

		return builder.sign(algorithm());
	}

}
