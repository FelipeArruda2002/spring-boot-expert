package br.com.felipearruda.vendas.repositories;

import br.com.felipearruda.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeLike(String nome);

    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(value = "delete from tb_cliente c where c.nome =:nome", nativeQuery = true)
    @Modifying
    @Transactional
    void excluirPorNome(@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Query(value = "select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente getClienteFetchPedidos(@Param("id") Long id);

}
