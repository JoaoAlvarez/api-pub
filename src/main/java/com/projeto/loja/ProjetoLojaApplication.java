package com.projeto.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.projeto.loja.resources")
public class ProjetoLojaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLojaApplication.class, args);
	}

}
