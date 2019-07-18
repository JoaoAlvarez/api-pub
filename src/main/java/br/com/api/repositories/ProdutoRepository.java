package br.com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{}
