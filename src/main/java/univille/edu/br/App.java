package univille.edu.br;

import univille.edu.br.DAO.BaseDAO;
import univille.edu.br.Service.AluguelService;
import univille.edu.br.Service.ClienteService;
import univille.edu.br.Service.ImovelService;
import univille.edu.br.entidades.Aluguel;
import univille.edu.br.entidades.Cliente;
import univille.edu.br.entidades.Imovel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        try(Connection con = new BaseDAO().con()) {

            ClienteService clienteService = new ClienteService(con);
            ImovelService imovelService = new ImovelService(con);
            AluguelService aluguelService = new AluguelService(con);

            Scanner sc = new Scanner(System.in);

            boolean continuar = true;
            while (continuar) {
                System.out.print("Insira um comando: ");
                switch (sc.nextLine()) {
                    case "cliente listar todos":
                        clienteService.listarTodos();
                        break;
                    case "cliente listar id":
                        System.out.print("ID: ");
                        clienteService.listarPorId(sc.nextLong());
                        break;
                    case "cliente inserir":
                        Cliente clienteInserir = setCliente(sc);
                        clienteService.inserir(clienteInserir);
                        break;
                    case "cliente atualizar":
                        Cliente clienteAtualizar = setCliente(sc);
                        clienteService.alterar(clienteAtualizar);
                        break;
                    case "cliente excluir":
                        System.out.print("ID: ");
                        clienteService.remover(sc.nextLong());
                        break;
                    /// ////////////////////////////////////////////////// ///
                    case "imovel listar todos":
                        imovelService.listarTodos();
                        break;
                    case "imovel listar id":
                        System.out.println("ID: ");
                        imovelService.listarPorId(sc.nextLong());
                        break;
                    case "imovel listar disponivel":
                        imovelService.listarDisponivel();
                        break;
                    case "imovel inserir":
                        Imovel imovelInserir = setImovel(sc);
                        imovelService.inserir(imovelInserir);
                        break;
                    case "imovel atualizar":
                        Imovel imovelAtualizar = setImovel(sc);
                        imovelService.alterar(imovelAtualizar);
                        break;
                    case "imovel excluir":
                        System.out.println("ID: ");
                        imovelService.remover(sc.nextLong());
                        break;
                    /// ////////////////////////////////////////////////// ///
                    case "aluguel listar todos":
                        aluguelService.listarTodos();
                        break;
                    case "aluguel listar id":
                        System.out.println("ID: ");
                        aluguelService.listarPorId(sc.nextLong());
                        break;
                    case "aluguel listar ativos":
                        aluguelService.listarAtivos();
                        break;
                    case "aluguel listar melhores clientes":
                        aluguelService.listarMelhoresClientes();
                        break;
                    case "aluguel listar contratos expirando":
                        aluguelService.listarContratosExpirando();
                        break;
                    case "aluguel inserir":
                        Aluguel aluguelInserir = setAluguel(sc);
                        aluguelService.inserir(aluguelInserir);
                        break;
                    case "aluguel atualizar":
                        Aluguel aluguelAtualizar = setAluguel(sc);
                        aluguelService.alterar(aluguelAtualizar);
                        break;
                    case "aluguel excluir":
                        System.out.print("ID: ");
                        aluguelService.remover(sc.nextLong());
                        break;
                    /// ////////////////////////////////////////////////// ///
                    case "exit":
                        continuar = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static Cliente setCliente(Scanner sc) {
        System.out.print("ID: ");
        long id  = sc.nextLong();
        System.out.print("nome ");
        String nome = sc.next();
        System.out.print("cpf: ");
        String cpf = sc.next();
        System.out.print("email: ");
        String email = sc.next();
        return new Cliente(id,nome,cpf,email);
    }
    private static Imovel setImovel(Scanner sc) {
        System.out.print("ID: ");
        long id  = sc.nextLong();
        System.out.print("Endereço: ");
        String endereco = sc.next();
        System.out.print("Quantidade Quartos: ");
        int quantidadeQuartos = sc.nextInt();
        System.out.print("Disponível (true/false): ");
        boolean disponivel = sc.nextBoolean();
        return new Imovel(id,endereco,quantidadeQuartos,disponivel);
    }

    private static Aluguel setAluguel(Scanner sc) {
        System.out.print("ID Aluguel: ");
        long idAluguel  = sc.nextLong();
        System.out.print("ID Cliente: ");
        long idCliente = sc.nextLong();
        System.out.print("ID Imovel: ");
        long idImovel = sc.nextLong();
        System.out.print("Preço: ");
        BigDecimal preco = new BigDecimal(sc.next());
        LocalDateTime dataAluguel = LocalDateTime.now();
        LocalDateTime dataVencimento = LocalDateTime.now().plusDays(40);
        return new Aluguel(idAluguel,idCliente,idImovel,preco,dataAluguel,dataVencimento);
    }
}
