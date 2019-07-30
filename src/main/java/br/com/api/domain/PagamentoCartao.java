package br.com.api.domain;

import javax.persistence.Entity;

import br.com.api.domain.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;

	public PagamentoCartao() {}

	public PagamentoCartao(Integer id, EstadoPagamento estado, Conta pedido, Double valor, Integer numeroDeParcelas) {
		super(id, estado, pedido, valor);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
