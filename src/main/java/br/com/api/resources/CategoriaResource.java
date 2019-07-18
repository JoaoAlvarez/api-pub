package br.com.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.domain.Categoria;
import br.com.api.dto.CategoriaDTO;
import br.com.api.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria c = service.find(id);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",  method=RequestMethod.DELETE)
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
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
	
	@RequestMapping(value="page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<Categoria> listCategoria = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<CategoriaDTO> listDto = listCategoria.map(obj -> new CategoriaDTO(obj)); 
		/*
		 * DTO == ADAPTER
		 * Steam.map serve para percorrer toda a lista
		*e para cada iten da lista fazer o new CategoriaDTO
		*/
		
		return ResponseEntity.ok().body(listDto);
	}
	
}
