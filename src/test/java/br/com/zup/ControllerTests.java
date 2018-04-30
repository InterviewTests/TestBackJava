/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.zup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * No inicio do teste é feito um flushall.
     */

    @Before
    public void init(){
        Jedis jedis = new Jedis();
        jedis.flushAll();
    }

    /**
     * Teste de Integração de gastos por cartão.
     *
     * Retorna Status Code 201 se o novo gasto for incluído com sucesso.
     */
    @Test
    public void inclusaoDeNovoGastoDeveRetornarStatusCode201Test() throws Exception {

        String json = "{\"descricao\":\"descTest\",\"valor\":\"1200.12\", \"codigousuario\":\"1\", \"data\":\"2018-04-28T16:20:34Z\"}";

        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }
    /**
     * Teste de Listagem de gastos.
     *
     * Inclui 3 novos gastos.
     * Retorna os gastos realizados a 5 segundos atrás.
     */
    @Test
    public void inclui3GastosEBuscaApenasOsGastosRecentesTest() throws Exception{
        String codUsuario = "1";

        Date now = new Date();
        Instant instant = now.toInstant();
        //Gasto feito a 1 segundo
        Instant instant1 = instant.minusSeconds(1);
        //Gasto feito a 2 segundos
        Instant instant2 = instant.minusSeconds(2);
        //Gasto feito a 6 segundos
        Instant instant3 = instant.minusSeconds(6);

        String json1 = "{\"descricao\":\"descTest1\",\"valor\":\"1234.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\""+instant1.toString()+"\"}";
        String json2 = "{\"descricao\":\"descTest2\",\"valor\":\"4321.21\", \"codigousuario\":\""+codUsuario+"\", \"data\":\""+instant2.toString()+"\"}";
        String json3 = "{\"descricao\":\"descTest3\",\"valor\":\"404.00\", \"codigousuario\":\""+codUsuario+"\", \"data\":\""+instant3.toString()+"\"}";

        //Inclusão dos 3 gastos
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isCreated());
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json2))
                .andExpect(status().isCreated());
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json3))
                .andExpect(status().isCreated());

        String resp1 = "{\"id\":\"0\",\"descricao\":\"descTest1\",\"valor\":1234.12,\"codigousuario\":1,\"data\":"+Date.from(instant1).getTime()+",\"categoria\":\"\"}";
        String resp2 = "{\"id\":\"1\",\"descricao\":\"descTest2\",\"valor\":4321.21,\"codigousuario\":1,\"data\":"+Date.from(instant2).getTime()+",\"categoria\":\"\"}";

        //O gasto feito a 6 segundos não deve aparecer.
        String resp = "["+resp2+","+resp1+"]";
        this.mockMvc.perform(get("/gastosAtuais/"+codUsuario)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(resp));
    }

    /** Teste de Filtro de gastos
     *
     * Inclui 4 novos gastos, sendo que apenas dois gastos foram feitos na data informada no filtro.
     */
    @Test
    public void incluiNovosGastosEBuscaGastosPeloFiltroDeDataTest() throws Exception {

        String codUsuario = "1";
        String filtroData = "27/03/1992";

        String json1 = "{\"descricao\":\"descTest1\",\"valor\":\"1234.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"1992-03-25T11:20:34Z\"}";
        String json2 = "{\"descricao\":\"descTest2\",\"valor\":\"1234.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"1992-03-27T11:20:34Z\"}";
        String json3 = "{\"descricao\":\"descTest3\",\"valor\":\"1234.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"1992-03-27T13:20:34Z\"}";
        String json4 = "{\"descricao\":\"descTest4\",\"valor\":\"1234.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"1992-03-30T14:20:34Z\"}";

        //Inclui novos gastos
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isCreated());
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json2))
                .andExpect(status().isCreated());
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json3))
                .andExpect(status().isCreated());
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json4))
                .andExpect(status().isCreated());

        //Referente ao json2
        String resp1 = "{\"id\":\"1\",\"descricao\":\"descTest2\",\"valor\":1234.12,\"codigousuario\":1,\"data\":701695234000,\"categoria\":\"\"}";
        //Referente ao json3
        String resp2 = "{\"id\":\"2\",\"descricao\":\"descTest3\",\"valor\":1234.12,\"codigousuario\":1,\"data\":701702434000,\"categoria\":\"\"}";

        //Apenas os registros do json2 e json3 foram feitos na data 27/03/1992.
        String resp = "["+resp1+","+resp2+"]";
        this.mockMvc.perform(get("/gastos/"+codUsuario)
                .param("data", filtroData))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(resp));
    }

    /**Teste de Categorização de gastos
     *
     *Inclui novo gasto e atualiza a sua categoria.
     */
    @Test
    public void atualizacaoDeCategoriaDoGastoTest() throws Exception {

        String codUsuario = "1";
        String json = "{\"descricao\":\"descTest\",\"valor\":\"1200.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"2018-04-28T16:20:34Z\"}";

        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json));

        //Id do gasto = 0, pois é o primeiro registro do banco.
        String idGasto = "0";
        //Nova categoria para o gasto
        String categoria = "novaCategoria";

        //Atualiza o gasto
        this.mockMvc.perform(put("/gastos/"+codUsuario)
                .param("id", idGasto)
                .param("categoria", categoria))
                .andDo(print()).andExpect(status().isOk());

        String resp = "{\"id\":\"0\",\"descricao\":\"descTest\",\"valor\":1200.12,\"codigousuario\":1,\"data\":1524932434000,\"categoria\":\"novaCategoria\"}";
        //Verifica a categoria do gasto foi mesmo atualizado.
        this.mockMvc.perform(get("/gasto/"+codUsuario)
                .param("id", idGasto))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(resp));
    }

    /**
     * Inclui 3 gastos com 3 categorias diferentes:
     * Gasto 1 - categoria "gastos essenciais"
     * Gasto 2 - categoria "prioridades financeiras"
     * Gasto 3 - categoria "gastos desnecessarios"
     * Depois é feito uma sugestão de categoria baseado nas categorias já registradas
     * Procura por uma substring
     *
     * Exemplo de entrada:
     * Entrada do usuário = "finan"
     * Resposta deve ser "prioridades financeiras"
     */
    @Test
    public void sugestaoDeCategoriaBaseadaNasCategoriasInformadasTest() throws Exception{
        String parte = "finan";

        /*Inclui novo gasto com uma categoria*/
        String codUsuario = "1";
        String json = "{\"descricao\":\"descTest1\",\"valor\":\"1200.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"2018-04-28T16:20:34Z\"}";
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json));
        //Atualiza gasto de id 0
        String idGasto = "0";
        String categoria = "gastos essenciais";
        //Atualiza o gasto
        this.mockMvc.perform(put("/gastos/"+codUsuario)
                .param("id", idGasto)
                .param("categoria", categoria))
                .andDo(print()).andExpect(status().isOk());

        /*Inclui novo gasto com outra categoria*/
        codUsuario = "2";
        json = "{\"descricao\":\"descTest2\",\"valor\":\"1200.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"2018-04-28T16:20:34Z\"}";
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json));
        //Atualiza gasto de id 1
        idGasto = "1";
        categoria = "prioridades financeiras";
        //Atualiza o gasto
        this.mockMvc.perform(put("/gastos/"+codUsuario)
                .param("id", idGasto)
                .param("categoria", categoria))
                .andDo(print()).andExpect(status().isOk());

        /*Inclui um terceiro gasto com outra categoria*/
        codUsuario = "3";
        json = "{\"descricao\":\"descTest3\",\"valor\":\"1200.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"2018-04-28T16:20:34Z\"}";
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json));
        //Atualiza gasto de id 2
        idGasto = "2";
        categoria = "gastos desnecessarios";

        //Atualiza o gasto
        this.mockMvc.perform(put("/gastos/"+codUsuario)
                .param("id", idGasto)
                .param("categoria", categoria))
                .andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(get("/sugestoes/"+parte)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[\"prioridades financeiras\"]"));
    }

    /**Teste Categorização automatica de gasto
     * No processo de integração de gastos, a categoria é incluida automaticamente caso a descrição de um gasto seja
     * igual a descrição de qualquer outro gasto já categorizado pelo cliente.
     */
    @Test
    public void incluiGastoComPreenchimentoAutomaticoDeCategoriaTest() throws Exception{

        String codUsuario = "1";
        String json = "{\"descricao\":\"descTest\",\"valor\":\"1200.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"2018-04-28T16:20:34Z\"}";
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json));

        //Atualiza gasto de id 0
        String idGasto = "0";
        String categoria = "categoriaTeste";
        //Atualiza o gasto
        this.mockMvc.perform(put("/gastos/"+codUsuario)
                .param("id", idGasto)
                .param("categoria", categoria))
                .andDo(print()).andExpect(status().isOk());

        //Inclusão de novo gasto com a mesma descrição do gasto já existente na base
        json = "{\"descricao\":\"descTest\",\"valor\":\"1234.12\", \"codigousuario\":\""+codUsuario+"\", \"data\":\"2015-05-02T11:20:34Z\"}";
        this.mockMvc.perform(post("/incluir")
                .contentType(MediaType.APPLICATION_JSON).content(json));

        String resp = "{\"id\":\"1\",\"descricao\":\"descTest\",\"valor\":1234.12,\"codigousuario\":"+codUsuario+",\"data\":1430565634000,\"categoria\":\"categoriaTeste\"}";
        //Verifica se a categoria foi preenchida automaticamente
        this.mockMvc.perform(get("/gasto/"+codUsuario)
                .param("id", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(resp));
    }
}
