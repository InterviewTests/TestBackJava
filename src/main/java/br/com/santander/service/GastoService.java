package br.com.santander.service;

import br.com.santander.domain.Gasto;
import br.com.santander.repository.GastosRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GastoService {

    @Resource
    private GastosRepository repository;

    public Gasto incluir(Gasto gasto) {
        if (gasto.getData() == null) {
            gasto.setData(Calendar.getInstance().getTime());
        }
        return repository.save(gasto);
    }

    public List<Gasto> listarGastos(long user) {
        List<Gasto> result = repository.findAllByCodigoUsuario(user);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -5);
        return result.stream().filter(g -> g.getData().before(calendar.getTime())).collect(Collectors.toList());
    }
}
