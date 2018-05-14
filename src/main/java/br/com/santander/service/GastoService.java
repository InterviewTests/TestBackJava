package br.com.santander.service;

import br.com.santander.domain.Gasto;
import br.com.santander.repository.GastosRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

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
}
