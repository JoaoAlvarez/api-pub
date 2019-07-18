package br.com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{}
