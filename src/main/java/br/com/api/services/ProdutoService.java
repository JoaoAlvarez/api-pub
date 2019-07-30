package br.com.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.api.domain.Categoria;
import br.com.api.domain.Produto;
import br.com.api.repositories.CategoriaRepository;
import br.com.api.repositories.ProdutoRepository;
import br.com.api.services.execptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepositorio;
	
	@Autowired
	private CategoriaRepository categoriaRepositorio;

	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		//ditection = asc or desc;
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepositorio.findAllById(ids);
		return produtoRepositorio.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
	}
	
	public Produto insert(Produto obj) {
		return produtoRepositorio.save(obj);
	}
}