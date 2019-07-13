package com.projeto.loja;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.projeto.loja.domain.Categoria;
import com.projeto.loja.domain.Produto;
import com.projeto.loja.repositories.CategoriaRepository;
import com.projeto.loja.repositories.ProdutoRepository;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.projeto.loja.resources","com.projeto.loja.services"})
public class ProjetoLojaApplication  implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoLojaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria (null, "Bebidas");
		Categoria cat2 = new Categoria (null, "Comidas");
		
		Produto p1 = new Produto(null,"cerveja",20.00);
		Produto p2 = new Produto(null,"agua",2.00);
		Produto p3 = new Produto(null,"Carne",35.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2,p3));
	}

}
