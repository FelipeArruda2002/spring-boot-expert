package br.com.felipearruda.vendas.repositories;

import br.com.felipearruda.vendas.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClienteRepo {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void salvar(Cliente cliente) {
        entityManager.persist(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos() {
        return entityManager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

    @Transactional
    public void atualizar(Cliente cliente) {
        entityManager.merge(cliente);
    }

    @Transactional
    public void deletar(Cliente cliente) {
        if (!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        entityManager.remove(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterPorNome(String nome) {
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT c from Cliente c WHERE nome like :nome", Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

}
