/*package br.com.santander.app.service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.santander.app.model.User;
import br.com.santander.app.support.JwtHelper;
import br.com.santander.app.support.RedisConfiguration;
import br.com.santander.app.support.RedisKeysHelper;

@Service
public class JwtAuthtokenService {

	@Autowired
	private RedisConfiguration redisConfiguration;

	public String createAndSaveAuthtokenFromAuthentication(final Authentication authentication) {
		final User details = (User) authentication.getDetails();

		final String authtoken = createTokenFromUsername(details);

		//String tokensKey = RedisKeysHelper.generateAuthtokensKey(authentication.getPrincipal().toString());
		final String tokenKey = RedisKeysHelper.generateAuthtokenKey(authtoken);

		//redis.redisTemplate().opsForList().leftPush(tokensKey, authtoken);
		redisConfiguration.redisTemplate().opsForValue().set(tokenKey, authtoken);

		final Date expire = Date.from(Instant.now().plusSeconds(60*60*60*24*365));

		//redis.redisTemplate().expireAt(tokensKey, expire);
		redisConfiguration.redisTemplate().expireAt(tokenKey, expire);

		return authtoken;
	}



	public String createTokenFromUsername(final User user) {
		final Map<String, Object> header = new HashMap<>();

		header.put("user.id", user.getId());
		header.put("user.name", user.getUserName());

		return JwtHelper.createHash("Auth", user.getUserName(), null, header);
	}

}
 */