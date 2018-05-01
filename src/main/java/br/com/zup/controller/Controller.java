package br.com.zup.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import br.com.zup.entity.Gasto;
import br.com.zup.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private RedisRepository redisRepository;

    /**
     * Inclui novo gasto ao Redis a partir do json.
     * @param gasto
     * @return
     */
    @RequestMapping(
            value = "/incluir",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"}
            )
    public ResponseEntity inclui(@RequestBody Gasto gasto) {
        try {
            redisRepository.incluir(gasto);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Lista todos os gastos atuais ( gastos realizados a 5  segundos atrás ).
     * @param codUsuario
     * @return
     */
    @RequestMapping(value = "/gastosAtuais/{codUsuario}", method = RequestMethod.GET)
    public ResponseEntity gastosAtuais(@PathVariable String codUsuario){
        Date date = new Date();
        System.out.println(date.toInstant());
        Instant ultimos5Segs = date.toInstant().minusSeconds(5);
        return new ResponseEntity(redisRepository.findAllGastos(codUsuario, Date.from(ultimos5Segs).getTime(), date.getTime()),HttpStatus.OK);
    }

    /**
     * Lista os gastos do usuário na data informada.
     * @param codusuario
     * @param data
     * @return
     */
    @RequestMapping(value = "/gastos/{codusuario}", method = RequestMethod.GET)
    public ResponseEntity gastos(@PathVariable String codusuario,
                                 @RequestParam(value="data") String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d;
        try {
            d = sdf.parse(data);
        }catch (java.text.ParseException e){
            return new ResponseEntity("Formato da data incorreto, informar data no formato dd/MM/yyyy",HttpStatus.BAD_REQUEST);
        }
        Instant insDia = d.toInstant().minusSeconds(3600*3);// subtrai -3 horas. GMT-3
        Date inicioDia = Date.from(insDia);
        //Inicio do próximo dia.
        Date fimDia = new Date(inicioDia.getTime()+86400000);
        return new ResponseEntity(redisRepository.findAllGastos(codusuario, inicioDia.getTime(), fimDia.getTime()), HttpStatus.OK);
    }

    /**
     * Retorna o gasto a partir do código de usuário e id do gasto.
     * @param codusuario
     * @param id
     * @return
     */
    @RequestMapping(value = "/gasto/{codusuario}", method = RequestMethod.GET)
    public ResponseEntity getGasto(@PathVariable String codusuario,
                                   @RequestParam(value="id") String id) {
        Gasto gasto = redisRepository.findGasto(codusuario, id);
        if (gasto == null)
            return new ResponseEntity("Codigo de usuario ou id incorretos.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(gasto, HttpStatus.OK);
    }

    /**
     * Atualiza a categoria do gasto, com base no código do usuário e o id do gasto.
     * @param codUsuario
     * @param id
     * @param categoria
     * @return
     */
    @RequestMapping(value = "/gastos/{codUsuario}", method = RequestMethod.PUT)
    public ResponseEntity updateGasto(@PathVariable String codUsuario,
                            @RequestParam(value="id") String id,
                            @RequestParam(value="categoria") String categoria){
        if(redisRepository.updateCategory(codUsuario, id, categoria))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity("Codigo de usuario ou id incorretos.",HttpStatus.BAD_REQUEST);
    }

    /**
     * Sugestão de categoria.
     * Busca no banco uma sugestão, uma substring do que o usuário está digitando.
     * @param str
     * @return
     */
    @RequestMapping(value = "/sugestoes/{str}", method = RequestMethod.GET)
    public ResponseEntity getSugestao(@PathVariable String str){
        return new ResponseEntity(redisRepository.findSugestao(str), HttpStatus.OK);
    }
}
