package org.springframework.boot.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.cliente.dto.ClienteDTO;
import org.springframework.boot.cliente.model.Billing;
import org.springframework.boot.cliente.model.Categoria;
import org.springframework.boot.cliente.model.Cliente;
import org.springframework.boot.cliente.service.ClienteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public Cliente criarCliente(@RequestBody ClienteDTO cliente) {
        return clienteService.novoCliente(cliente);
    }

    @GetMapping("/listar")
    public List<Cliente> listaClientes(){
        return clienteService.lista();
    }

    @GetMapping("gastosCliente/{id}")
    public List<Billing> listarGastosByCliente(@PathVariable Long id){
        return clienteService.listaGastosCliente(id);
    }

    @GetMapping("gastosCliente/{id}/{data}")
    public List<Billing> listarGastosByCliente(@PathVariable Long id, @PathVariable  @DateTimeFormat(pattern = "dd-MM-yyyy") Date data ){
        return clienteService.listaGastosClienteData(id,data);
    }

    @PostMapping("categoria")
    public Categoria criarCategoria(@RequestBody Categoria categoria){
        return clienteService.criarCategoria(categoria);
    }


    @GetMapping("categoria")
    public List<Categoria> criarCategoria(){
        return clienteService.listarCategoria();
    }



}
