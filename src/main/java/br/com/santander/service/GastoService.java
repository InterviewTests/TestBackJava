package br.com.santander.service;

import br.com.santander.domain.Gasto;
import br.com.santander.repository.GastosRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class GastoService {

    private static final Logger log = Logger.getLogger(GastoService.class.getName());

    @Resource
    private GastosRepository repository;

    public Gasto incluir(Gasto gasto) {
        if (gasto.getData() == null) {
            log.warning("Data nulla, utilizando data atual.");
            gasto.setData(Calendar.getInstance().getTime());
        } 
        return repository.save(gasto);
    }

    public List<Gasto> listarGastos(long user) {
        log.info("Listando gastos do usuario " + user);
        List<Gasto> result = repository.findAllByCodigoUsuario(user);

        log.info("Quantidade de resultados validos: " + result.size());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -5);
        return result.stream().filter(g -> g.getData().before(calendar.getTime())).collect(Collectors.toList());
    }

    public List<Gasto> listarGastosFiltrados(long user, String data) throws ParseException {
        log.info("Listando gastos filtrados, data: ".concat(data));
        List<Gasto> result = listarGastos(user);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date time = dateFormat.parse(data);

        return result.stream().filter(g-> g.getData().after(time)).collect(Collectors.toList());
    }
}
