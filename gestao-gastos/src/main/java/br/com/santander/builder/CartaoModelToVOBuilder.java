package br.com.santander.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;

import br.com.santander.model.Gasto;
import br.com.santander.vo.CartaoVO;
import br.com.santander.vo.GastoVO;

public class CartaoModelToVOBuilder {

	private List<Gasto> gastoModelList;

	public CartaoModelToVOBuilder(List<Gasto> gastoModelList) {
		this.gastoModelList = gastoModelList;
	}
	
	public List<CartaoVO> build() {
		List<CartaoVO> cartoesVO = new ArrayList<CartaoVO>();
		for (Gasto gastoModel : gastoModelList) {
			GastoVO gastoVO = new GastoVO(gastoModel.getCodigoGasto(), gastoModel.getDescricao(), gastoModel.getValor(), gastoModel.getCodigoUsuario(), gastoModel.getData());
			
			BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate("numeroCartao", gastoModel.getNumeroCartao(), true);
			CartaoVO cartaoSelecionado = (CartaoVO) CollectionUtils.find(cartoesVO, predicate);
			
			if(cartaoSelecionado == null) {
				CartaoVO cartaoVO = new CartaoVO(gastoModel.getNumeroCartao(), gastoVO);
				cartoesVO.add(cartaoVO);
			} else {
				cartaoSelecionado.getGastos().add(gastoVO);
			}
			
		}

		return cartoesVO;
	}
	
	public static void main(String[] args) {
		List<Gasto> gastos = new ArrayList<Gasto>();
		Gasto g1 = new Gasto(UUID.randomUUID(), 4444555566664444444L, "Mercado", new BigDecimal("500.53"), 123354851L, Calendar.getInstance().getTime());
		Gasto g2 = new Gasto(UUID.randomUUID(), 4444555566664444445L, "Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		Gasto g3 = new Gasto(UUID.randomUUID(), 4444555566664444444L, "Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		Gasto g4 = new Gasto(UUID.randomUUID(), 4444555566664444444L, "Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		Gasto g5 = new Gasto(UUID.randomUUID(), 4444555566664444445L, "Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		
		gastos.add(g1);
		gastos.add(g2);
		gastos.add(g3);
		gastos.add(g4);
		gastos.add(g5);
		
		List<CartaoVO> cartoes = new CartaoModelToVOBuilder(gastos).build();
		
		System.out.println("Tamanho da lista" + cartoes.size());
		
		for (CartaoVO cartaoVO : cartoes) {
			System.out.println("Numero: " + cartaoVO.getNumeroCartao());
			System.out.println("Qtde gastos: " + cartaoVO.getGastos().size());
		}
	}
	
}
