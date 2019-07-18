package br.com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{}
