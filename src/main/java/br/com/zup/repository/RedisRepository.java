package br.com.zup.repository;

import br.com.zup.entity.Gasto;

import java.util.ArrayList;

public interface RedisRepository {

    /**
     * Retorna uma lista de gastos entre as horas min e max, a partir do código do usuário.
     */
    ArrayList<Gasto> findAllGastos(String codUser, double min, double max);

    /**
     * Inclui novo gasto ao Redis
     */
    void incluir(Gasto gasto);

    /**
     * Deleta registro a partir do id
     */
    void delete(String id);

    /**
     * Retorna o gasto com base no código do usuário e a id do gasto.
     */
    Gasto findGasto(String codUsuario,String id);

    /**
     * Atualiza a categoria do registro a partir do código do usuário e id do gasto.
     */
    boolean updateCategory(String codUsuario,String id, String categoria);

    /**
     * Gera lista de sugestões de categoria baseado na string de entrada.
     */
    ArrayList<String> findSugestao(String string);
}
