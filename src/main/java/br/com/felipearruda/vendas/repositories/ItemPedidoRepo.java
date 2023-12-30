package br.com.felipearruda.vendas.repositories;

import br.com.felipearruda.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepo extends JpaRepository<ItemPedido, Long> {
}
