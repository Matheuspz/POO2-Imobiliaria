package univille.edu.br.DAO;

import univille.edu.br.entidades.Imovel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImovelDAO extends BaseDAO implements DAO<Imovel> {

    private final Connection con;
    public ImovelDAO(Connection con) { this.con = con; }

    @Override
    public Optional<Imovel> listarPorId(long id) {

        try {
            String sql = "SELECT * FROM imovel WHERE idImovel = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Imovel imovel = new Imovel(
                        rs.getInt("idImovel"),
                        rs.getString("endereco"),
                        rs.getInt("qtdComodos"),
                        rs.getBoolean("disponivel")
                );
                return Optional.of(imovel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Imovel> listarTodos() {
        List<Imovel> imovels = new ArrayList<>();

        try {
            String sql = "SELECT * FROM imovel";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Imovel imovel = new Imovel(
                        rs.getInt("idImovel"),
                        rs.getString("endereco"),
                        rs.getInt("qtdComodos"),
                        rs.getBoolean("disponivel")
                );
                imovels.add(imovel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imovels;
    }

    @Override
    public void inserir(Imovel imovel) {
        try {
            String sql = "INSERT INTO imovel (endereco, qtdComodos, disponivel) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, imovel.getEndereco());
            ps.setInt(2, imovel.getQuantidadeQuartos());
            ps.setBoolean(3, imovel.isDisponivel());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(Imovel imovel) {
        try {
            String sql = "UPDATE imovel SET endereco=?, qtdComodos=?, disponivel=? WHERE idImovel=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, imovel.getEndereco());
            ps.setInt(2, imovel.getQuantidadeQuartos());
            ps.setBoolean(3, imovel.isDisponivel());
            ps.setLong(4, imovel.getIdImovel());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(long id) {
        try {
            String sql = "DELETE FROM imovel WHERE idImovel = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
