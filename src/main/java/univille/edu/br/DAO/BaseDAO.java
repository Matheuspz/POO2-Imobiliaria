package univille.edu.br.DAO;

import univille.edu.br.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDAO {

    protected Connection con() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }
}
