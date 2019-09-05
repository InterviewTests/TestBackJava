package com.santander.interview.enums;

public enum ResponseMessageEnum {
    ADD_EXPENSE_SUCCESS("Gasto criado com sucesso"),
    SEARCH_EXPENSE_BY_USER_CODE_SUCCESS("Busca pelo código do usuário realizada com sucesso"),
    SEARCH_EXPENSE_BY_USER_CODE_AND_DATE_SUCCESS("Busca por código do usuário e pela data realizada com sucesso"),
    UPDATE_EXPENSE_SUCCESS("Gasto atualizado com sucesso"),
    ERROR_BADLY_FORMATTED_DATE("Data mal formatada"),
    ADD_CATEGORY_SUCCESS("Categoria criada com sucesso"),
    SUGGESTION_CATEGORY_SUCCESS("Busca por categorias realizada com sucesso");

    private String message;

    private ResponseMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() { return this.message; }
}
