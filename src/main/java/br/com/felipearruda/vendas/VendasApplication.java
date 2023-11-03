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
            clienteRepo.salvar(new Cliente("Felipe"));
            clienteRepo.salvar(new Cliente("Daniela"));

            List<Cliente> clientes = clienteRepo.obterTodos();
            clientes.forEach(System.out::println);

            System.out.println("Atualizando Clientes");
            clientes.forEach(c -> {
                c.setNome(c.getNome() + " Atualizado");
                clienteRepo.atualizar(c);
            });

            clientes = clienteRepo.obterTodos();
            clientes.forEach(System.out::println);

            System.out.println("Buscando por nome");
            clientes = clienteRepo.obterPorNome("Felipe");
            clientes.forEach(System.out::println);

            System.out.println("Deletando clientes");
            clientes = clienteRepo.obterTodos();
            clientes.forEach(clienteRepo::deletar);
            clientes = clienteRepo.obterTodos();

            if (clientes.isEmpty()) {
                System.out.println("Nenhumm cliente encontrado");
            } else {
                clientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
