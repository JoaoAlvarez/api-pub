package br.com.api.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.domain.Conta;
import br.com.api.services.ContaService;

@RestController
@RequestMapping(value="/conta")
public class ContaResource {

	@Autowired
	private ContaService service;
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	public ResponseEntity<Conta> find(@PathVariable Integer id) {
		Conta c = service.find(id);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Conta obj){
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Conta> inserirItem(@PathVariable Integer contaId, @PathVariable Integer produtoId, @PathVariable Integer quantidade){
		Conta c = service.insertItem(contaId, produtoId, quantidade);
		return ResponseEntity.ok().body(c);
	}
	
}