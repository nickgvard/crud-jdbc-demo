package utils.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {

    Connection connection() throws SQLException;
    void release() throws SQLException;
    int currentSize();
}
