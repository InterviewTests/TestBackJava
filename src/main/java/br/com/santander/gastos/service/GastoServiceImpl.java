package br.com.santander.gastos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.gastos.model.Gasto;
import br.com.santander.gastos.repository.GastoRepository;

@Service
public class GastoServiceImpl implements GastoService {

	Date date = new Date();
	Date dateFim = new Date();
	
	
	@Autowired
	private GastoRepository gastoRepository;
	
	@Override
	public List<Gasto> findByIdUsuarioOrderByDataDesc(Long id) {
		return gastoRepository.findByIdUsuarioOrderByDataDesc(id);
	}

	@Override
	public List<Gasto> findByDate(Long userId, String data) {
		
		tratarDatas(data);
		
		return gastoRepository.findGastosByDate(userId, 
				date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
				dateFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	}

	private void tratarDatas(String data) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			date = sdf.parse(data);
		} catch (ParseException e) {
			System.out.println("Erro ao converter data: " + e.getMessage());
		}
		
		Calendar dateEnd = new GregorianCalendar();
		dateEnd.setTime(date);
		dateEnd.set(Calendar.HOUR, 23);
		dateEnd.set(Calendar.MINUTE, 59);
		dateEnd.set(Calendar.SECOND, 59);
		dateFim = dateEnd.getTime();
		
	}

	@Override
	public Optional<Gasto> findById(Long userId, Long gastoId) {
		return gastoRepository.findById(userId, gastoId);
	}

	@Override
	public void save(Gasto novoGasto) {
		gastoRepository.save(novoGasto);
	}

}
