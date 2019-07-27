package br.com.api.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.api.domain.PagamentoBoleto;

@Service
public class BoletoService {

	/** Metodo utilizado para substituir a ausencia de um Gerador de boleto
	 * Inserindo a data Vencimento paga pagemento boleto
	 */
	public void preencherPagamentoBoleto(PagamentoBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
