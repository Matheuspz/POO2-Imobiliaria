package univille.edu.br.Service;

import univille.edu.br.DAO.ClienteDAO;
import univille.edu.br.entidades.Cliente;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ClienteService implements Service<Cliente> {

    private final ClienteDAO clienteDAO;

    public ClienteService(Connection con) {
        this.clienteDAO = new ClienteDAO(con);
    }

    @Override
    public void listarTodos() {
        List<Cliente> clientes = clienteDAO.listarTodos();

        if(clientes.isEmpty()){
            System.out.println("Nenhum cliente encontrado");
        } else {
            for(Cliente cliente : clientes){
                System.out.println("IdCliente: " + cliente.getIdCliente() + " | " +
                        "Nome: " + cliente.getNome() + " | " +
                        "CPF: " + cliente.getCpf() + " | " +
                        "Email: " + cliente.getEmail());
            }
        }
    }

    @Override
    public void listarPorId(long id) {
        Optional<Cliente> optionalCliente = clienteDAO.listarPorId(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            System.out.println("IdCliente: " + cliente.getIdCliente() + " | " +
                    "Nome: " + cliente.getNome() + " | " +
                    "CPF: " + cliente.getCpf() + " | " +
                    "Email: " + cliente.getEmail());
        } else {
            System.out.println("Nenhum cliente encontrado com o ID: " + id);
        }
    }

    @Override
    public void inserir(Cliente cliente) {
        clienteDAO.inserir(cliente);
        System.out.println("Cliente inserido com sucesso");
    }

    @Override
    public void alterar(Cliente cliente) {
        clienteDAO.alterar(cliente);
        System.out.println("Cliente alterado com sucesso");
    }

    @Override
    public void remover(long id) {
        clienteDAO.remover(id);
        System.out.println("Cliente removido com sucesso");
    }
}
