package br.com.felipearruda.vendas.repositories;

import br.com.felipearruda.vendas.domain.entity.Cliente;
import br.com.felipearruda.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {

    Set<Pedido> findByCliente(Cliente cliente);
}
