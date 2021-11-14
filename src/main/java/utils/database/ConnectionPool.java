package utils.database;

import liquibase.util.beans.PropertyUtils;
import org.apache.commons.text.StringSubstitutor;

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

public class ConnectionPool implements IConnectionPool {

    private static List<Connection> connectionPool;
    private static List<Connection> usedConnection;
    private final static Properties PROPERTIES;
    private static int POOL_SIZE;
    private final static StringSubstitutor STR_SUB;

    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private ConnectionPool() {}

    static {
        STR_SUB = new StringSubstitutor(System.getenv());
        PROPERTIES = new Properties();
        //default pool size
        POOL_SIZE = 10;
        try {
            try {
                PROPERTIES.load(new FileInputStream("src/main/resources/application.properties"));
                POOL_SIZE = Integer.parseInt(STR_SUB.replace(PROPERTIES.getProperty("poolsize")));
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

    public static ConnectionPool pool() {
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
        return DriverManager.getConnection(STR_SUB.replace(PROPERTIES.getProperty("url")), STR_SUB.replace(PROPERTIES.getProperty("username")), STR_SUB.replace(PROPERTIES.getProperty("password")));
    }
}
