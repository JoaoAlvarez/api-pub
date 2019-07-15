package com.projeto.loja.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projeto.loja.domain.Categoria;
import com.projeto.loja.dto.CategoriaDTO;
import com.projeto.loja.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> listCategoria = service.findAll();
		List<CategoriaDTO> listDto = listCategoria
				.stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList()); 
		/*
		 * DTO == ADAPTER
		 * Steam.map serve para percorrer toda a lista
		*e para cada iten da lista fazer o new CategoriaDTO
		* O .collect(Collectors.toList()); serve para converter uma lista para outra lista.
		*/
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria c = service.find(id);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",  method=RequestMethod.DELETE)
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
