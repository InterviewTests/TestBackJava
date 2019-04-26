package br.com.camaroti.alex.rest.api.expense.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.camaroti.alex.rest.api.expense.domain.Category;

@FeignClient("category-management")
public interface CategoryClient {

	@PostMapping(path="/categories")
	@ResponseBody Category save(@RequestParam(value="name") String name) throws Exception;

	@GetMapping(path="/category/{name}")
	@ResponseBody Category findByNameIgnoreCase(@PathVariable(value="name", required = true) String name)throws Exception;

}