package utils.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public interface ConnectionPool {

    Connection connection() throws SQLException;
    void retrieve(Connection connection);
    void releaseAll() throws SQLException;
}
