package univille.edu.br.DAO;

import univille.edu.br.entidades.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO extends BaseDAO implements DAO<Cliente> {

    private Connection con;
    public ClienteDAO(Connection con) {
        this.con = con;
    }

    @Override
    public Optional<Cliente> listarPorId(long id) {

        try {
            String sql = "SELECT * FROM cliente WHERE idCliente = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email")
                );
                System.out.println("IdCliente: " + cliente.getIdCliente() + " | " +
                                   "Nome: " + cliente.getNome() + " | " +
                                   "CPF: " + cliente.getCpf() + " | " +
                                   "Email: " + cliente.getEmail());
                return Optional.of(cliente);
            }
            System.out.println("Nenhum cliente encontrado");

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        return clientes;
    }

    @Override
    public void inserir(Cliente cliente) {

    }

    @Override
    public void alterar(Cliente cliente) {

    }

    @Override
    public void remover(long id) {

    }
}
