package utils.database;

import java.sql.*;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class DataBaseAccess {

    public static PreparedStatement preparedStatement(String sql) {
        try {
            final Connection connection = ConnectionPoolImpl.pool().connection();
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static PreparedStatement preparedStatement(String sql, boolean autoCommit) {
        try {
            final Connection connection = ConnectionPoolImpl.pool().connection();
            if(!autoCommit)
                connection.setAutoCommit(false);
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static PreparedStatement preparedStatement(String sql, boolean autoCommit, boolean useResultType) {
        try {
            final Connection connection = ConnectionPoolImpl.pool().connection();
            if(!autoCommit)
                connection.setAutoCommit(false);
            if(useResultType)
                return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            else
                return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Statement statement() {
        try {
            final Connection connection = ConnectionPoolImpl.pool().connection();
            return connection.createStatement();
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void returnConnection(Connection connection) {
        ConnectionPoolImpl.pool().retrieve(connection);
    }
}