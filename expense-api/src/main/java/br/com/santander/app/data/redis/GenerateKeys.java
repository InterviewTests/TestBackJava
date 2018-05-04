package br.com.santander.app.data.redis;

import org.springframework.stereotype.Component;

@Component
public class GenerateKeys {
	
	public String keyForCategoryId(Long category) {
		return String.format("catagories:%s", category);
	}
	
}
