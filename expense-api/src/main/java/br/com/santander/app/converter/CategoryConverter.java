package br.com.santander.app.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.app.dto.CategoryDTO;
import br.com.santander.app.model.Category;

public class CategoryConverter {

	public static Category fromDTO(final CategoryDTO dto) {
		final Category model= new Category();
		model.setId(dto.getCode());
		model.setDescription(dto.getDescription());
		return model;
	}

	public static CategoryDTO toDTO(final Category model) {
		final CategoryDTO dto= new CategoryDTO();
		dto.setCode(model.getId());
		dto.setDescription(model.getDescription());
		return dto;
	}

	public static List<CategoryDTO> toDTO(final List<Category> list){
		final List<CategoryDTO> results = new ArrayList<>();
		for (final Category model : list){
			results.add(toDTO(model));
		}
		return results;
	}
}
