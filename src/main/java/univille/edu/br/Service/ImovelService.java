package univille.edu.br.Service;

import univille.edu.br.DAO.ImovelDAO;
import univille.edu.br.entidades.Cliente;
import univille.edu.br.entidades.Imovel;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ImovelService implements Service<Imovel> {

    private final ImovelDAO imovelDAO;

    public ImovelService(Connection con) {
        this.imovelDAO = new ImovelDAO(con);
    }

    @Override
    public void listarTodos() {
        List<Imovel> imovels = imovelDAO.listarTodos();

        if(imovels.isEmpty()){
            System.out.println("Nenhum Imovel encontrado");
        } else {
            for(Imovel imovel : imovels){
                System.out.println("idImovel: " + imovel.getIdImovel() + " | " +
                        "Endereco: " + imovel.getEndereco() + " | " +
                        "Quantidade de Quartos: " + imovel.getQuantidadeQuartos() + " | " +
                        "Disponibilidade: " + imovel.isDisponivel());
            }
        }
    }

    @Override
    public void listarPorId(long id) {
        Optional<Imovel> optionalImovel = imovelDAO.listarPorId(id);

        if (optionalImovel.isPresent()) {
            Imovel imovel = optionalImovel.get();
            System.out.println("idImovel: " + imovel.getIdImovel() + " | " +
                    "Endereco: " + imovel.getEndereco() + " | " +
                    "Quantidade de Quartos: " + imovel.getQuantidadeQuartos() + " | " +
                    "Disponibilidade: " + imovel.isDisponivel());
        } else  {
            System.out.println("Nenhum Imovel encontrado");
        }
    }

    @Override
    public void inserir(Imovel imovel) {
        imovelDAO.inserir(imovel);
        System.out.println("Imovel inserido com sucesso");
    }

    @Override
    public void alterar(Imovel imovel) {
        imovelDAO.alterar(imovel);
        System.out.println("Imovel alterado com sucesso");
    }

    @Override
    public void remover(long id) {
        imovelDAO.remover(id);
        System.out.println("Imovel removido com sucesso");
    }
}
