package br.com.felipearruda.vendas.service;

import br.com.felipearruda.vendas.model.Cliente;
import br.com.felipearruda.vendas.repo.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void salvar(Cliente cliente) {
        this.repository.persistir(cliente);
    }

    public void validaCliente() {
        // Realiza validações no cadastro de cliente
    }

}
