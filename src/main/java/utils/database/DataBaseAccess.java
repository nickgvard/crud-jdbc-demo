package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseAccess {

    public PreparedStatement preparedStatement(String sql, boolean autoCommit) {
        try {
            Connection connection = ConnectionPool.pool().connection();
            if(!autoCommit)
                connection.setAutoCommit(false);
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public PreparedStatement preparedStatement(String sql) {
        try {
            Connection connection = ConnectionPool.pool().connection();
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Statement statement() {
        try {
            Connection connection = ConnectionPool.pool().connection();
            return connection.createStatement();
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void releaseConnection(Connection connection) {
        ConnectionPool.pool().release(connection);
    }
}