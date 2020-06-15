package br.com.altrantest.demo.controller;

import br.com.altrantest.demo.gastos.GastoByUser;
import br.com.altrantest.demo.repository.GastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/v1/gastos")
@RestController
public class GastosController {

    @Autowired
    private GastosRepository gastosRepository;

    private GastoByUser gastosByUser;

    //Controle de gastos


    @GetMapping(path = "/user/all")
    public ResponseEntity<?> findAllGastos() {

        return new ResponseEntity<>(gastosRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> getGastosById(@PathVariable("id") Long id) {
        GastoByUser gastos = gastosRepository.findById(id).get();
        return new ResponseEntity<>(gastos, HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<GastoByUser> adcionarGastos(@RequestBody GastoByUser gastosByUser) {
        gastosByUser = gastosRepository.save(gastosByUser);
        return ResponseEntity.ok().body(gastosByUser);
    }

    @DeleteMapping(path = "/admin/{id}")
    public ResponseEntity<?> deleteGastos(@PathVariable Long id) {
        gastosRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@RequestBody GastoByUser gastoByUser) {
        gastosRepository.save(gastoByUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/user/filtro/{descricao}")
    @ResponseBody
    public ResponseEntity<?> getByDesc(@PathVariable String descricao) {

        return new ResponseEntity<>(gastosRepository.findByDescricao(descricao), HttpStatus.OK);
    }


}
