package univille.edu.br.Service;

import univille.edu.br.DAO.AluguelDAO;
import univille.edu.br.entidades.Aluguel;
import univille.edu.br.entidades.MelhoresClientes;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AluguelService implements Service<Aluguel> {

    private final AluguelDAO aluguelDAO;

    public AluguelService(Connection con) {
        this.aluguelDAO = new AluguelDAO(con);
    }

    @Override
    public void listarTodos() {
        List<Aluguel> aluguels = aluguelDAO.listarTodos();
        if (aluguels.isEmpty()) {
            System.out.println("Nenhum aluguel encontrado");
        }  else {
            for (Aluguel aluguel : aluguels) {
                System.out.println("idAluguel: " + aluguel.getIdAluguel() + " | " +
                        "idCliente: " + aluguel.getIdCliente() + " | " +
                        "idImovel " + aluguel.getIdImovel() + " | " +
                        "Preço: " + aluguel.getPreco() + " | " +
                        "DataAluguel: " + aluguel.getDataAluguel() + " | " +
                        "DataVencimento: " + aluguel.getDataVencimento());
            }
        }
    }

    @Override
    public void listarPorId(long id) {
        Optional<Aluguel> optionalAluguel = aluguelDAO.listarPorId(id);
        if (optionalAluguel.isPresent()) {
            Aluguel aluguel = optionalAluguel.get();
            System.out.println("idAluguel: " + aluguel.getIdAluguel() + " | " +
                    "idCliente: " + aluguel.getIdCliente() + " | " +
                    "idImovel " + aluguel.getIdImovel() + " | " +
                    "Preço: " + aluguel.getPreco() + " | " +
                    "DataAluguel: " + aluguel.getDataAluguel() + " | " +
                    "DataVencimento: " + aluguel.getDataVencimento());
        } else  {
            System.out.println("Nenhum aluguel encontrado");
        }
    }

    public void listarAtivos() {
        List<Aluguel> aluguels = aluguelDAO.listarAtivos();
        if (aluguels.isEmpty()) {
            System.out.println("Nenhum aluguel ativo");
        } else  {
            for (Aluguel aluguel : aluguels) {
                System.out.println("idAluguel: " + aluguel.getIdAluguel() + " | " +
                        "idCliente: " + aluguel.getIdCliente() + " | " +
                        "idImovel " + aluguel.getIdImovel() + " | " +
                        "Preço: " + aluguel.getPreco() + " | " +
                        "DataAluguel: " + aluguel.getDataAluguel() + " | " +
                        "DataVencimento: " + aluguel.getDataVencimento());
            }
        }
    }

    public void listarMelhoresClientes() {
        List<MelhoresClientes> melhoresClientes = aluguelDAO.listarMelhoresClientes();
        if (melhoresClientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado");
        }  else  {
            for (MelhoresClientes melhoresCliente : melhoresClientes) {
                System.out.println("idCliente: " + melhoresCliente.getIdCliente() + " | " +
                        "Nome: " + melhoresCliente.getNome() + " | " +
                        "Total de contratos: " + melhoresCliente.getTotalContratos());
            }
        }
    }

    public void listarContratosExpirando() {
        List<Aluguel> aluguels = aluguelDAO.listarContratosExpirando();
        if (aluguels.isEmpty()) {
            System.out.println("Nenhum aluguel expirando");
        } else {
            for (Aluguel aluguel : aluguels) {
                System.out.println("idAluguel: " + aluguel.getIdAluguel() + " | " +
                        "idCliente: " + aluguel.getIdCliente() + " | " +
                        "idImovel " + aluguel.getIdImovel() + " | " +
                        "Preço: " + aluguel.getPreco() + " | " +
                        "DataAluguel: " + aluguel.getDataAluguel() + " | " +
                        "DataVencimento: " + aluguel.getDataVencimento());
            }
        }
    }

    @Override
    public void inserir(Aluguel aluguel) {
        aluguelDAO.inserir(aluguel);
        System.out.println("Aluguel inserido com sucesso");
    }

    @Override
    public void alterar(Aluguel aluguel) {
        aluguelDAO.alterar(aluguel);
        System.out.println("Aluguel alterado com sucesso");
    }

    @Override
    public void remover(long id) {
        aluguelDAO.remover(id);
        System.out.println("Aluguel removido com sucesso");
    }
}
