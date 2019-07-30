package br.com.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import br.com.api.domain.enums.TipoConta;

@Entity
public class ContaMesa extends Conta{
	private static final long serialVersionUID = 1L;

	private Integer numeroMesa;
	
	public ContaMesa() {}

	public ContaMesa(Integer id, TipoConta tipoConta, Date abertura, Endereco enderecoDeEntrega, Integer numeroMesa) {
		super(id, tipoConta, abertura, enderecoDeEntrega);
		this.numeroMesa = numeroMesa;
	}

	public Integer getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(Integer numeroMesa) {
		this.numeroMesa = numeroMesa;
	}
	
}
