package br.com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.domain.ItemConta;

@Repository
public interface ItemContaRepository extends JpaRepository<ItemConta,Integer>{}
