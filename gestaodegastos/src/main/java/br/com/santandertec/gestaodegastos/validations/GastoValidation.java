package br.com.santandertec.gestaodegastos.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.santandertec.gestaodegastos.models.Gasto;

public class GastoValidation implements Validator {

	private static final int MIN_TITULO_LENGTH = 5;
	private static final int MAX_TITULO_LENGTH = 50;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Gasto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "categoria.nomeCategoria", "campo.obrigatorio");
		
		Gasto gasto = (Gasto) target;
		
		if (gasto.getCategoria().getNomeCategoria().length() <= MIN_TITULO_LENGTH) {
			errors.rejectValue("categoria.nomeCategoria", "campo.min.quantidadeDeCaracteres");
		}
		
		if (gasto.getCategoria().getNomeCategoria().length() > MAX_TITULO_LENGTH) {
			errors.rejectValue("categoria.nomeCategoria", "campo.max.quantidadeDeCaracteres");
		}
	}

}
