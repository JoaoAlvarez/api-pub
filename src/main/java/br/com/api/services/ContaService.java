package br.com.api.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.domain.Conta;
import br.com.api.domain.ItemConta;
import br.com.api.domain.PagamentoCartao;
import br.com.api.domain.Produto;
import br.com.api.domain.enums.EstadoPagamento;
import br.com.api.repositories.ContaRepository;
import br.com.api.repositories.ItemContaRepository;
import br.com.api.repositories.PagamentoRepository;
import br.com.api.services.execptions.ObjectNotFoundException;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemContaRepository itemContaRepository;
	
	@Autowired
	private PagamentoCartaoService cartaoService;
	
	public Conta find(Integer id) {
		Optional<Conta> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName()));
	}
	
	@Transactional
	public Conta insert(Conta obj) {
		//obj.setId(null);
		
		if(obj.getAbertura() ==null)
			obj.setAbertura(new Date());
		
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setConta(obj);
		if(obj.getPagamento() instanceof PagamentoCartao) {
			//PagamentoCartao pagto = (PagamentoCartao) obj.getPagamento();
			cartaoService.pagar((PagamentoCartao) obj.getPagamento());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemConta ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPrecoVenda());
			ip.setConta(obj);
		}
		itemContaRepository.saveAll(obj.getItens());
		return obj;
	}
	
	public Conta insertItem(Integer contaId, Integer produtoId, Integer quantidade) {
		Conta conta = find(contaId);
		Produto produto = produtoService.find(produtoId);
		ItemConta ip = new ItemConta(conta, produto, 0.0, quantidade);
		
		conta.getItens().add(ip);
		itemContaRepository.saveAll(conta.getItens());
		return conta;
	}
}