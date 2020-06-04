package Test.BackJava;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {
	
	private final RepositorioDeGastos repositorio;
	
	Controlador(RepositorioDeGastos repositorio) {
		this.repositorio = repositorio;
	}
	
	@PostMapping("integracao")
    public String integracao(@RequestBody Gasto gasto) {
		repositorio.save(gasto);
		return "Gasto registrado com sucesso!\n" + gasto;
    }
	
	@GetMapping("listagem")
    public String listagem(@RequestHeader("codigousuario") Integer codigousuario) {
		return Gasto.arrayToString(repositorio.findByCodigousuario(codigousuario));
    }
	
	@GetMapping("filtro")
    public String filtro(@RequestHeader("codigousuario") Integer codigousuario, @RequestParam Integer dia, @RequestParam Integer mes, @RequestParam Integer ano) {
		List<Gasto> gastosDoUsuario = repositorio.findByCodigousuario(codigousuario);
		List<Gasto> gastosDoDia = new ArrayList<Gasto>();		
		for (Gasto gasto : gastosDoUsuario) {
			LocalDate localDate = gasto.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (dia == localDate.getDayOfMonth() && mes == localDate.getMonthValue() && ano == localDate.getYear()) {
				gastosDoDia.add(gasto);
			}
		}
		return Gasto.arrayToString(gastosDoDia);
    }
	
	@PostMapping("categorizacao")
    public String categorizacao(@RequestHeader("codigousuario") Integer codigousuario, @RequestBody NovaCategoria novaCategoria) {
		List<Gasto> gastosDoUsuario = repositorio.findByCodigousuario(codigousuario);
		for (Gasto gasto : gastosDoUsuario) {
			if (novaCategoria.getIdgasto() == gasto.getId()) {
				gasto.setCategoria(novaCategoria.getCategoria());
				repositorio.save(gasto);
				return "Gasto categorizado com sucesso!\n" + gasto;
			}
		}
		return "Erro: Nao foi possivel encontrar um gasto seu com este id";
    }
    
}
