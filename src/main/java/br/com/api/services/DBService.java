package br.com.api.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.domain.Categoria;
import br.com.api.domain.Cidade;
import br.com.api.domain.Cliente;
import br.com.api.domain.Conta;
import br.com.api.domain.ContaComanda;
import br.com.api.domain.ContaMesa;
import br.com.api.domain.Endereco;
import br.com.api.domain.Estado;
import br.com.api.domain.ItemConta;
import br.com.api.domain.Pagamento;
import br.com.api.domain.PagamentoCartao;
import br.com.api.domain.PagamentoDinheiro;
import br.com.api.domain.Produto;
import br.com.api.domain.enums.EstadoPagamento;
import br.com.api.domain.enums.TipoCliente;
import br.com.api.domain.enums.TipoConta;
import br.com.api.repositories.CategoriaRepository;
import br.com.api.repositories.CidadeRepository;
import br.com.api.repositories.ClienteRepository;
import br.com.api.repositories.ContaRepository;
import br.com.api.repositories.EnderecoRepository;
import br.com.api.repositories.EstadoRepository;
import br.com.api.repositories.ItemContaRepository;
import br.com.api.repositories.PagamentoRepository;
import br.com.api.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ContaRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemContaRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador","comp", 1000.00, 2000.00, 20);
		Produto p2 = new Produto(null, "Impressora","imp",400.00, 800.00, 1);
		Produto p3 = new Produto(null, "Mouse", "mous",40.0,80.00, 5);
		Produto p4 = new Produto(null, "Mesa de escritório","m-escr" ,150.0,300.00, 0);
		Produto p5 = new Produto(null, "Toalha","toal",2.0, 50.00, 15);
		Produto p6 = new Produto(null, "Colcha","col" ,100.0,200.00,5);
		Produto p7 = new Produto(null, "TV true color","tvc",600.0, 1200.00,8);
		Produto p8 = new Produto(null, "Roçadeira", "roç",400.0,800.00,7);
		Produto p9 = new Produto(null, "Abajour", "aba",50.0, 100.00,12);
		Produto p10 = new Produto(null, "Pendente", "pend",60.0, 180.00,5);
		Produto p11 = new Produto(null, "Shampoo","shamp",30.0, 90.00,0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Cliente Generico", "cliente@cliente.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Conta ped1 = new Conta(null, TipoConta.COMPRA_LIVRE, sdf.parse("30/09/2017 10:32"), e1);
		Conta ped2 = new Conta(null, TipoConta.COMPRA_LIVRE, sdf.parse("10/10/2017 19:35"), e2);
		ContaComanda ped3 = new ContaComanda(null, TipoConta.CONTA_COMANDA, sdf.parse("10/10/2017 19:35"), null, cli1);
		ContaMesa ped4 = new ContaMesa(null, TipoConta.CONTA_MESA, sdf.parse("10/10/2017 19:35"), null, 2);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 500.0, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoDinheiro(null, EstadoPagamento.PENDENTE, ped2, null, null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2, ped3));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3, ped4));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemConta ip1 = new ItemConta(ped1, p1, 0.00, 1);
		ItemConta ip2 = new ItemConta(ped1, p3, 0.00, 2);
		ItemConta ip3 = new ItemConta(ped2, p2, 100.00, 1);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));		
	}
}