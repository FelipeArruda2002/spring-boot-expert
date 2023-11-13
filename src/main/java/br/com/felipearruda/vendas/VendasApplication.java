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

            Boolean existeFelipe = clienteRepo.existsByNome("Felipe");
            System.out.println(String.format("Existem algum cliente com o nome Felipe? %s", existeFelipe.toString()));

            List<Cliente> clientes = clienteRepo.findAll();
            clientes.forEach(System.out::println);

            System.out.println("Atualizando Clientes");
            clientes.forEach(c -> {
                c.setNome(c.getNome() + " Atualizado");
                clienteRepo.save(c);
            });

            clientes = clienteRepo.findAll();
            clientes.forEach(System.out::println);

            System.out.println("Buscando por nome");
            clientes = clienteRepo.findByNomeLike("Felipe Atualizado");
            clientes.forEach(System.out::println);

            System.out.println("Deletando clientes");
            clientes = clienteRepo.findAll();
            clientes.forEach(clienteRepo::delete);
            clientes = clienteRepo.findAll();

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado");
            } else {
                clientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
