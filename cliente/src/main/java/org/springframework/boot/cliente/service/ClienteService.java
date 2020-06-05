package org.springframework.boot.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.cliente.dto.ClienteDTO;
import org.springframework.boot.cliente.model.Billing;
import org.springframework.boot.cliente.model.Categoria;
import org.springframework.boot.cliente.model.Cliente;
import org.springframework.boot.cliente.repository.BillingRepository;
import org.springframework.boot.cliente.repository.CategoriaRepository;
import org.springframework.boot.cliente.repository.ClienteReposiroty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteReposiroty clienteReposiroty;

    @Autowired
    BillingRepository billingRepository;


    @Autowired
    CategoriaRepository categoriaRepository;


    public Cliente novoCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setName(dto.getName());
        cliente.setCpf(dto.getCpf());
        cliente.setPassword(dto.getPassword());

        clienteReposiroty.save(cliente);
        return  cliente;
    }

    public List<Cliente> lista() {
        return clienteReposiroty.findAll();
    }

    public List<Billing> listaGastosCliente(Long id) {
        return billingRepository.findByCodigousuario(id);
    }


    public List<Billing> listaGastosClienteData(Long id, Date data) {
        return billingRepository.findByData(id, data);
    }

    public Categoria criarCategoria(Long id, String descricao, String categoria) {
        Categoria saveCategoria  = new Categoria();
        saveCategoria.setDescricao(descricao);
        saveCategoria.setCategoria(categoria);
        return categoriaRepository.save(saveCategoria);
    }

    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }
}
