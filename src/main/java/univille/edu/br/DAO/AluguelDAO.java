package univille.edu.br.DAO;

import univille.edu.br.entidades.Aluguel;
import univille.edu.br.entidades.MelhoresClientes;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AluguelDAO extends BaseDAO implements DAO<Aluguel> {

    private final Connection con;
    public AluguelDAO(Connection con) {
        this.con = con;
    }

    @Override
    public Optional<Aluguel> listarPorId(long id) {
        try {
            String sql = "SELECT * FROM contrato WHERE idContrato = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Aluguel aluguel = setAluguel(rs);
                return Optional.of(aluguel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Aluguel> listarTodos() {
        List<Aluguel> aluguels = new ArrayList<>();

        try {
            String sql = "SELECT * FROM contrato";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluguel aluguel = setAluguel(rs);
                aluguels.add(aluguel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluguels;
    }

    public List<Aluguel> listarAtivos() {
        List<Aluguel> aluguels = new ArrayList<>();
        try {
            String sql = "SELECT * FROM contrato WHERE dataInicio < dataFim";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluguel aluguel = setAluguel(rs);
                aluguels.add(aluguel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluguels;
    }

    public List<MelhoresClientes> listarMelhoresClientes() {
        List<MelhoresClientes> melhoresClientes = new ArrayList<>();
        try {
            String sql = "SELECT c.idCliente,c.nome, COUNT(ct.idContrato) as totalContratos" +
                         " FROM cliente c INNER JOIN contrato ct ON c.idCliente = ct.idCliente" +
                         " GROUP BY c.idCliente ORDER BY totalContratos DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MelhoresClientes mc = new MelhoresClientes(
                        rs.getInt("idCliente"),
                        rs.getString("nome"),
                        rs.getInt("totalContratos")
                );
                melhoresClientes.add(mc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return melhoresClientes;
    }

    public List<Aluguel> listarContratosExpirando() {
        List<Aluguel> aluguels = new ArrayList<>();
        try {
            String sql = "SELECT * FROM contrato WHERE " +
                         "dataFim BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 30 DAY);";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluguel aluguel = setAluguel(rs);
                aluguels.add(aluguel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluguels;
    }

    @Override
    public void inserir(Aluguel aluguel) {
        try {
            String sql = "INSERT INTO contrato (idCliente, idImovel, valor, dataInicio, dataFim) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = prepareAluguel(aluguel, sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(Aluguel aluguel) {
        try {
            String sql = "UPDATE contrato SET idCliente=?, idImovel=?, valor=?, dataInicio=?, dataFim=? WHERE idContrato=?";
            PreparedStatement ps = prepareAluguel(aluguel, sql);
            ps.setLong(6, aluguel.getIdAluguel());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(long id) {
        try {
            String sql = "DELETE FROM contrato WHERE idContrato = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Aluguel setAluguel(ResultSet rs) throws SQLException {
        Timestamp tsDataInicio = rs.getTimestamp("dataInicio");
        LocalDateTime dataInicio = (tsDataInicio != null) ? tsDataInicio.toLocalDateTime(): null;
        Timestamp tsDataFim = rs.getTimestamp("dataFim");
        LocalDateTime dataFim = (tsDataFim != null) ? tsDataFim.toLocalDateTime(): null;
        return new Aluguel(
                rs.getInt("idContrato"),
                rs.getInt("idCliente"),
                rs.getInt("idImovel"),
                rs.getBigDecimal("valor"),
                dataInicio,
                dataFim
        );
    }

    private PreparedStatement prepareAluguel(Aluguel aluguel, String sql) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        LocalDateTime dataInicio = aluguel.getDataAluguel();
        LocalDateTime dataFim = aluguel.getDataVencimento();
        ps.setLong(1, aluguel.getIdCliente());
        ps.setLong(2, aluguel.getIdAluguel());
        ps.setBigDecimal(3,aluguel.getPreco());
        ps.setTimestamp(4,dataInicio != null? Timestamp.valueOf(dataInicio) : null);
        ps.setTimestamp(5,dataFim != null? Timestamp.valueOf(dataFim) : null);

        return ps;
    }
}
