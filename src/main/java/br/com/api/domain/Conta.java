package br.com.api.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.domain.enums.TipoConta;

@Entity
public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer tipoConta;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date abertura;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="conta")
	private Pagamento pagamento;

	@ManyToOne
	@JoinColumn(name="endereco_entrega_id")
	private Endereco enderecoDeEntrega;

	@OneToMany(mappedBy="id.conta")
	private Set<ItemConta> itens = new HashSet<>();
	
	public Conta() {}

	public Conta(Integer id, TipoConta tipoConta, Date abertura, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.abertura = abertura;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.tipoConta = (tipoConta == null) ? null : tipoConta.getCod();
	}
	
	public double getValorTotal() {
		double soma = 0.0;
		for (ItemConta itemConta : itens) {
			soma += itemConta.getSubTotal();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
	public Set<ItemConta> getItens() {
		return itens;
	}

	public void setItens(Set<ItemConta> itens) {
		this.itens = itens;
	}
	
	public TipoConta getTipoConta() {
		return TipoConta.toEnum(tipoConta);
	}

	public void setEstado(TipoConta tipoConta) {
		this.tipoConta = tipoConta.getCod();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
