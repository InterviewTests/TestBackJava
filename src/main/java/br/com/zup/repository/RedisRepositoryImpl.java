package br.com.zup.repository;

import br.com.zup.entity.Gasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private static final String KEYCATEGORIA = "Categoria";

    private RedisTemplate<String, Object> redisTemplate;

    private ZSetOperations zSetOperations;
    private ValueOperations valueOperations;
    private HashOperations hashOperations;
    private SetOperations setOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        zSetOperations = redisTemplate.opsForZSet();
        valueOperations = redisTemplate.opsForValue();
        hashOperations = redisTemplate.opsForHash();
        setOperations = redisTemplate.opsForSet();
    }

    /**
     * Inclui novo gasto ao Redis.
     * o gasto é adicionado a uma lista ordenada.
     * @param gasto
     */
    public void incluir(Gasto gasto) {

        //Gera um novo id para o gasto.
        gasto.setId(getId());
        String codUsuario = String.valueOf(gasto.getCodigousuario());

        //Se descrição do gasto for igual a descrição de qualquer outro gasto já categorizado pelo cliente o mesmo
        //recebe esta categoria.
        Object cat = hashOperations.get("categoria:" + codUsuario, gasto.getDescricao());
        if (cat == null)
            gasto.setCategoria("");
        else
            gasto.setCategoria((String)cat);
        byte[] data = SerializationUtils.serialize(gasto);
        //Salva o gasto na lista.
        zSetOperations.add("gasto:" + codUsuario, data, gasto.getData().getTime());
    }

    public void delete(final String id) {
    }

    /**
     * Procura e retorna o gasto a partir do codigo do usuário e id do gasto.
     * @param codUsuario
     * @param id
     * @return
     */
    public Gasto findGasto(String codUsuario, String id){
        ArrayList<Gasto> gastos = new ArrayList<>();
        //Busca todos os gastos
        Set conjunto = zSetOperations.range("gasto:"+codUsuario, 0,-1);
        Object[] listaBytes = conjunto.toArray();
        //Percorre a lista de objetos.. O(n)..  :/
        for(Object o: listaBytes){
            String [] bytesString = ((String)o).split(",");
            byte[] bytes = new byte[bytesString.length];
            for(int i = 0 ; i < bytes.length ; ++i)
                bytes[i] = Byte.parseByte(bytesString[i]);
            Gasto gasto = (Gasto)SerializationUtils.deserialize(bytes);
            if(gasto.getId().equals(id))
                return gasto;
        }
        return null;
    }

    /**
     * Retorna uma lista com todos os gastos de acordo com o parâmetro de data min e max.
     * @param min
     * @param max
     * @return
     */
    public ArrayList<Gasto> findAllGastos(String codUser, double min, double max){
        ArrayList<Gasto> gastos = new ArrayList<>();
        Set conjunto = zSetOperations.rangeByScoreWithScores("gasto:"+codUser, min, max);
        Object[] listaBytes = conjunto.toArray();
        for(Object o: listaBytes){
            ZSetOperations.TypedTuple<String> gas= (ZSetOperations.TypedTuple)o;
            //Divide a string de bytes separados por vírgula, e da um parse da string para bytes.
            String [] bytesString = ((String)gas.getValue()).split(",");
            byte[] bytes = new byte[bytesString.length];
            for(int i = 0 ; i < bytes.length ; ++i)
                bytes[i] = Byte.parseByte(bytesString[i]);
            Gasto gasto = (Gasto)SerializationUtils.deserialize(bytes);
            //Adiciona gasto a lista de gastos.
            gastos.add(gasto);
        }
        return gastos;
    }

    /**
     * Gera um id único para o registro.
     * @return
     */
    private String getId() {
        String id;
        Object idObj = valueOperations.get("gasto:id");
        if(idObj == null){
            valueOperations.set("gasto:id", "1");
            id = "0";
        }else {
            id = (String) idObj;
            valueOperations.increment("gasto:id", 1);
        }
        return id;
    }

    /**
     * Atualiza a categoria do registro a partir do código de usuário e id do gasto.
     * @param codUser
     * @param id
     * @param categoria
     */
    public boolean updateCategory(String codUser, String id, String categoria){
        Gasto gasto = findGasto(codUser, id);
        if (gasto == null)
            return false;
        byte[] data = SerializationUtils.serialize(gasto);
        //Remove o gasto da base
        zSetOperations.remove("gasto:"+codUser, data);
        gasto.setCategoria(categoria);
        data = SerializationUtils.serialize(gasto);
        //Adiciona o gasto atualizado.
        zSetOperations.add("gasto:"+codUser, data, gasto.getData().getTime());
        //Adiciona a categoria a lista de categorias do usuário.
        hashOperations.put("categoria:"+codUser, gasto.getDescricao(), categoria);
        //Adiciona a categoria a lista de categorias geral.
        setOperations.add(KEYCATEGORIA, categoria);
        return true;
    }

    /**
     * Encontra uma sugestão de categoria a partir da String passada.
     * A lista de sugestões é baseada em categorias informadas por todos usuários.
     * @param string
     * @return
     */
    public ArrayList<String> findSugestao(String string){
        ArrayList<String> sugestoes = new ArrayList<>();
        Set setCategoriasList = setOperations.members(KEYCATEGORIA);
        Object[] categoriaList = setCategoriasList.toArray();
        Pattern p = Pattern.compile(".*"+string+".*");
        for(Object obj: categoriaList){
            String strObj = (String)obj;
            //Procura pelo pattern
            Matcher m = p.matcher(strObj);
            if(m.matches())
                sugestoes.add(strObj);
        }
        return sugestoes;
    }
}
