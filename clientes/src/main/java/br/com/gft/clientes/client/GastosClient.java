package br.com.gft.clientes.client;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.gft.clientes.model.Gasto;

@FeignClient("gastos")
public interface GastosClient {
	
	@RequestMapping("/gastos/listarPorCliente/{id}")
	List<Gasto> listaPorCliente(@PathVariable Long id);
	
	@RequestMapping("/gastos/listarPorCliente/{id}/{data}")
	List<Gasto> listaPorClienteEData(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date data);
}
