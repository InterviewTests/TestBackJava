package br.com.santander.card.client.http.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Category {
	private final String category;
	private final String description;
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Category))
			return false;
		Category o = (Category)obj;
		return o.getCategory().equals(getCategory()) && o.getDescription().equals(getDescription());
	}
}
