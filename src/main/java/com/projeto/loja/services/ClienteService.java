package com.projeto.loja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.loja.domain.Cidade;
import com.projeto.loja.domain.Cliente;
import com.projeto.loja.domain.Endereco;
import com.projeto.loja.domain.enums.TipoCliente;
import com.projeto.loja.dto.ClienteDTO;
import com.projeto.loja.dto.ClienteNewDTO;
import com.projeto.loja.repositories.ClienteRepository;
import com.projeto.loja.repositories.EnderecoRepository;
import com.projeto.loja.services.execptions.DataIntegrityException;
import com.projeto.loja.services.execptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;
	
	@Autowired
	private EnderecoRepository enderecoRepositorio;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		enderecoRepositorio.saveAll(obj.getEnderecos());
		return repositorio.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		/* Caso nao encontre o id do obj ele retorna uma excecao */
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repositorio.save(newObj);
	}

	 private void updateData(Cliente newObj, Cliente obj) {
		 newObj.setNome(obj.getNome());
		 newObj.setEmail(obj.getEmail());
	 }
	 
	public void delete(Integer id) {
		/* Caso nao encontre o id do obj ele retorna uma excecao */
		find(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel remover um Cliente que há entidade relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repositorio.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		// ditection = asc or desc;
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorio.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	 
}