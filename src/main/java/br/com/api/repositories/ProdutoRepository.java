package br.com.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.domain.Categoria;
import br.com.api.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	/* @Transactional(readOnly=true)
	 * @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat where obj.nome like %:nome% AND cat in :categorias"
	 * ) Page<Produto> search(@Param("nome")String nome, @Param("categorias")List<Categoria>categorias, Pageable pageRequest);
	 * 
	 * Abaixo segue a mesma consulta porem seguindo o gerador de consultar automatica do Springboot
	 */
	
	//O @Transactional serve pra informar que não vai ser uma transacao, apenas uma consulta, evita o loking no banco
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	@Transactional(readOnly=true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria>categorias, Pageable pageRequest);
}
