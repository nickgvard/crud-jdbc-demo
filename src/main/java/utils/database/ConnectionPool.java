package utils.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool implements IConnectionPool {

    private static List<Connection> connectionPool;
    private static List<Connection> usedConnection;
    private final static Properties PROPERTIES;
    private static int MAX_POOL_SIZE;

    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private ConnectionPool() {}

    static {
        PROPERTIES = new Properties();
        //default pool size
        MAX_POOL_SIZE = 10;
        try {
            try {
                PROPERTIES.load(new FileInputStream("src/main/resources/db/migration/db.properties"));
                //preset pool size
                MAX_POOL_SIZE = Integer.parseInt(PROPERTIES.getProperty("maxpoolsize"));
            } catch (IOException | NumberFormatException exception) {
                exception.printStackTrace();
            }
            connectionPool = new ArrayList<>(MAX_POOL_SIZE);
            usedConnection = new ArrayList<>();

            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                connectionPool.add(createdConnection());
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static ConnectionPool pool() {
        return INSTANCE;
    }

    @Override
    public Connection connection() throws SQLException {
        if(connectionPool.isEmpty()) {
            if (usedConnection.size() < MAX_POOL_SIZE) {
                connectionPool.add(createdConnection());
            } else
                throw new RuntimeException("Max pool size achieved");
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);

        usedConnection.add(connection);
        return connection;
    }

    @Override
    public void release(Connection connection) {
        try {
            if(connection.isClosed())
                connection = createdConnection();
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        connectionPool.add(connection);
        usedConnection.remove(connection);
    }

    @Override
    public void releaseAll() throws SQLException {
        for(Connection connection : usedConnection)
            connection.close();
        usedConnection.clear();

        for(Connection connection : connectionPool)
            connection.close();
        connectionPool.clear();
    }

    private static Connection createdConnection() throws SQLException {
        return DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES.getProperty("username"), PROPERTIES.getProperty("password"));
    }
}
