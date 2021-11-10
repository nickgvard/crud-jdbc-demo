package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseAccess {

    public static PreparedStatement preparedStatement(String sql, boolean autoCommit) {
        try {
            final Connection connection = ConnectionPool.pool().connection();
            if(!autoCommit)
                connection.setAutoCommit(false);
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static PreparedStatement preparedStatement(String sql) {
        try {
            final Connection connection = ConnectionPool.pool().connection();
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Statement statement() {
        try {
            final Connection connection = ConnectionPool.pool().connection();
            return connection.createStatement();
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void returnConnection(Connection connection) {
        ConnectionPool.pool().retrieve(connection);
    }
}