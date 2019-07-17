package com.projeto.loja.resources;

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

import com.projeto.loja.domain.Cliente;
import com.projeto.loja.dto.ClienteDTO;
import com.projeto.loja.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente c = service.find(id);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",  method=RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listCliente = service.findAll();
		List<ClienteDTO> listDto = listCliente
				.stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList()); 
		/*
		 * DTO == ADAPTER
		 * Steam.map serve para percorrer toda a lista
		*e para cada iten da lista fazer o new ClienteDTO
		* O .collect(Collectors.toList()); serve para converter uma lista para outra lista.
		*/
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<Cliente> listCliente = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClienteDTO> listDto = listCliente.map(obj -> new ClienteDTO(obj)); 
		/*
		 * DTO == ADAPTER
		 * Steam.map serve para percorrer toda a lista
		*e para cada iten da lista fazer o new ClienteDTO
		*/
		
		return ResponseEntity.ok().body(listDto);
	}

	
}
