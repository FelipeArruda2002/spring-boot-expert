package br.com.felipearruda.vendas.repositories;

import br.com.felipearruda.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome(String nome);

}
