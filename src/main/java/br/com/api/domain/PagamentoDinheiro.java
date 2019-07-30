package br.com.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.domain.enums.EstadoPagamento;

@Entity
public class PagamentoDinheiro extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataPagamento;
	
	public PagamentoDinheiro() {}
	
	public PagamentoDinheiro(Integer id, EstadoPagamento estado, Conta pedido, Double valor, Date dataPagamento) {
		super(id, estado, pedido, valor);
		this.dataPagamento = dataPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	

}
