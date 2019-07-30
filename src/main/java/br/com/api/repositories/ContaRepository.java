package br.com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Integer>{}
