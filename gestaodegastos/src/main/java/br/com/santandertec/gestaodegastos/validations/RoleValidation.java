package br.com.santandertec.gestaodegastos.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.santandertec.gestaodegastos.models.Role;

public class RoleValidation implements Validator {

	private static final int MIN_TITULO_LENGTH = 5;
	private static final int MAX_TITULO_LENGTH = 20;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "campo.obrigatorio");
		
		Role role = (Role) target;
		
		if (role.getNome().length() <= MIN_TITULO_LENGTH) {
			errors.rejectValue("nome", "campo.min.quantidadeDeCaracteres");
		}
		
		if (role.getNome().length() > MAX_TITULO_LENGTH) {
			errors.rejectValue("nome", "campo.max.quantidadeDeCaracteres");
		}
	}

}
