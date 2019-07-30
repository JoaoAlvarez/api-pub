package br.com.api.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.api.domain.enums.TipoConta;

@Entity
public class ContaComanda extends Conta{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	public ContaComanda() {}

	public ContaComanda(Integer id, TipoConta tipoConta, Date abertura, Endereco enderecoDeEntrega, Cliente cliente) {
		super(id, tipoConta, abertura, enderecoDeEntrega);
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
