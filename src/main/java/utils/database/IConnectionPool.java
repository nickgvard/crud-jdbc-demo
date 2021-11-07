package utils.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {

    Connection connection() throws SQLException;
    void release(Connection connection);
    void releaseAll() throws SQLException;
}
