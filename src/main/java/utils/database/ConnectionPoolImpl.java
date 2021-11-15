package utils.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class ConnectionPoolImpl implements ConnectionPool {

    private static List<Connection> connectionPool;
    private static List<Connection> usedConnection;
    private final static Properties PROPERTIES;
    private static int POOL_SIZE;

    private static final ConnectionPoolImpl INSTANCE = new ConnectionPoolImpl();

    private ConnectionPoolImpl() {}

    static {
        PROPERTIES = new Properties();
        //default pool size
        POOL_SIZE = 10;
        try {
            try {
                PROPERTIES.load(new FileInputStream("src/main/resources/application.properties"));
                POOL_SIZE = Integer.parseInt(System.getenv(PROPERTIES.getProperty("poolsize")));
            } catch (IOException | NumberFormatException exception) {
                exception.printStackTrace();
            }
            connectionPool = new ArrayList<>(POOL_SIZE);
            usedConnection = new ArrayList<>();

            for (int i = 0; i < POOL_SIZE; i++)
                connectionPool.add(createdConnection());

        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static ConnectionPoolImpl pool() {
        return INSTANCE;
    }

    @Override
    public Connection connection() throws SQLException {
        if(connectionPool.isEmpty()) {
            if (usedConnection.size() < POOL_SIZE)
                connectionPool.add(createdConnection());
            else
                throw new RuntimeException("Max pool size achieved");
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);

        usedConnection.add(connection);
        return connection;
    }

    @Override
    public void retrieve(Connection connection) {
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
        return DriverManager.getConnection(System.getenv(PROPERTIES.getProperty("url")), System.getenv(PROPERTIES.getProperty("username")), System.getenv(PROPERTIES.getProperty("password")));
    }
}
