package br.com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
	
	/*So em colocar findBy<Nome do campo> o springData ja indentifica e cria o metodo por debaixo dos panos
	 *-O @Transactional serve pra informar que não vai ser uma transacao, apenas uma consulta, evita o loking no banco 
	 *
	 *Exemplos em: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	 **/
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
	
	@Transactional(readOnly=true)
	Cliente findByCpfCnpj(String cpfCnpj);
}
