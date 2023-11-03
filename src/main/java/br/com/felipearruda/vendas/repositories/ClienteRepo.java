package br.com.felipearruda.vendas.repositories;

import br.com.felipearruda.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepo {

    private static final String INSERT = "INSERT INTO CLIENTE (NOME) VALUES(?)";
    private static final String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static final String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, cliente.getNome());
    }

    public List<Cliente> obterTodos() {
        List<Cliente> clientes = jdbcTemplate.query(SELECT_ALL, getMapperCliente());
        return clientes;
    }

    public void atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, cliente.getNome(), cliente.getId());
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }
    public void deletar(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<Cliente> obterPorNome(String nome) {
        List<Cliente> clientes = jdbcTemplate.query(SELECT_ALL.concat(" WHERE NOME LIKE ?"),
                new Object[]{"%" + nome + "%"},
                getMapperCliente());
        return clientes;
    }

    public RowMapper getMapperCliente() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

}
