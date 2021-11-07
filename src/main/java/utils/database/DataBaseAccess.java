package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SourceDataBase {

    public PreparedStatement preparedStatement(String sql, boolean autoCommit) {
        try {
            Connection connection = ConnectionPool.pool().connection();
            connection.setAutoCommit(autoCommit);
            return connection.prepareStatement(sql);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void releaseConnection(Connection connection) {
        ConnectionPool.pool().release(connection);
    }
}