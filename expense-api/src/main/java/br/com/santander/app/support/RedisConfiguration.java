/*package br.com.santander.app.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> redis = new RedisTemplate<>();
		redis.setConnectionFactory(jedisConnectionFactory());
		redis.setDefaultSerializer(new StringRedisSerializer());
		return redis;
	}

}
 */