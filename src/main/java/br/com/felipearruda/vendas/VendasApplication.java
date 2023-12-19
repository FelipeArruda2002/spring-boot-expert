package br.com.felipearruda.vendas;

import br.com.felipearruda.vendas.domain.entity.Cliente;
import br.com.felipearruda.vendas.repositories.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    CommandLineRunner init(@Autowired ClienteRepo clienteRepo) {
        return args -> {
            System.out.println("Salvando Clientes");
            clienteRepo.save(new Cliente("Felipe"));
            clienteRepo.save(new Cliente("Daniela"));
            clienteRepo.save(new Cliente("Oseias"));

            System.out.println("Clientes com nome Felipe");
            List<Cliente> result = clienteRepo.encontrarPorNome("Felipe");
            result.forEach(System.out::println);

            System.out.println("Deletando cliente com o nome Daniela");
            clienteRepo.excluirPorNome("Daniela");
            result = clienteRepo.findAll();
            result.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
