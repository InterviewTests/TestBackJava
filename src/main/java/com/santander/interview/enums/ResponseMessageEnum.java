package com.santander.interview.enums;

public enum ResponseMessageEnum {
    UNKNOWN_ERROR(0, "", ""),
    ADD_EXPENSE_SUCCESS(1,"Gasto criado com sucesso", "Gasto criado"),
//    EXPENSE_NOT_FOUND_TO_USER_CODE(2, "Esse cliente não possui gastos",
//            "Não foram encontrados gastos para esse código de usuário"),
    SEARCH_EXPENSE_BY_USER_CODE_SUCCESS(3, "Busca pelo cliente realizada com sucesso",
            "Gastos do usuário encontrados"),
    EXPENSE_BADLY_FORMATTED_DATE(4, "Data mal formatada",
            "A data deve estar no formato ddMMyyyy"),
    EXPENSE_NOT_FOUND(5, "Gasto não encontrado",
            "Não foi encontrado gasto para o ID informado"),
    SEARCH_EXPENSE_BY_USER_CODE_AND_DATE_SUCCESS(6,
            "Busca realizada com sucesso",
            "Busca por código do usuário e pela data realizada com sucesso"),
    UPDATE_EXPENSE_SUCCESS(7, "Gasto atualizado com sucesso",
            "Gasto encontrado e atualizado"),
    ADD_CATEGORY_SUCCESS(8, "Categoria adicionada com sucesso",
            "Categoria criada"),
    SUGGESTION_CATEGORY_SUCCESS(9, "Busca da categoria realizada com sucesso",
            "Lista com categorias");

    private int code;
    private String userMessage;
    private String internalMessage;

    private ResponseMessageEnum(int code, String userMessage, String internalMessage) {
        this.code = code;
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
    }

    public int getCode() { return this.code; }

    public String getUserMessage() { return this.userMessage; }

    public String getInternalMessage() { return this.internalMessage; }
}
