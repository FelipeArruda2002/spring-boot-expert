package br.com.felipearruda.vendas;

import br.com.felipearruda.vendas.domain.entity.Cliente;
import br.com.felipearruda.vendas.domain.entity.Pedido;
import br.com.felipearruda.vendas.repositories.ClienteRepo;
import br.com.felipearruda.vendas.repositories.PedidoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

    @Bean
    CommandLineRunner init(@Autowired ClienteRepo clienteRepo, PedidoRepo pedidoRepo) {
        return args -> {
            System.out.println("Salvando Clientes");
            Cliente felipe = clienteRepo.save(new Cliente("Felipe"));

            Pedido pedido = new Pedido();
            pedido.setCliente(felipe);
            pedido.setDataPedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(100.00));
            pedidoRepo.save(pedido);

            System.out.println("Cliente:" + felipe.getNome());
            System.out.println("Pedidos(Fetch):");
            felipe = clienteRepo.getClienteFetchPedidos(felipe.getId());
            felipe.getPedidos().forEach(System.out::println);

            System.out.println("Pedidos(Find By Cliente)");
            pedidoRepo.findByCliente(felipe).forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
