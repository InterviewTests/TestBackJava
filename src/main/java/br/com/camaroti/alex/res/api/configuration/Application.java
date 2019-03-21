package br.com.camaroti.alex.res.api.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"br.com.camaroti.alex.res.api.domain"})
@ComponentScan(basePackages = {"br.com.camaroti.alex.res.api.controller", "br.com.camaroti.alex.res.api.service", "class br.com.camaroti.alex.res.api.domain"})
@EnableJpaRepositories("br.com.camaroti.alex.res.api.repository")
@EnableRedisRepositories("br.com.camaroti.alex.res.api.service")
public class Application {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
	    return new JedisConnectionFactory(config);
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
	    final RedisTemplate<String, String> template = new RedisTemplate<String, String>();
	    template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        // the following is not required      
        template.setHashValueSerializer(new StringRedisSerializer());
        //template.setHashKeySerializer(new StringRedisSerializer());
	    template.afterPropertiesSet();
	    return template;

	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
