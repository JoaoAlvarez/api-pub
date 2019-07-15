package com.projeto.loja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.loja.domain.Categoria;
import com.projeto.loja.repositories.CategoriaRepository;
import com.projeto.loja.services.execptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repositorio.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		/*Caso nao encontre o id do obj ele retorna uma excecao*/
		find(obj.getId());
		return repositorio.save(obj);
	}
}