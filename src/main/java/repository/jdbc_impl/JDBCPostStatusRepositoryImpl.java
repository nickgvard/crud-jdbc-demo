package repository.jdbc_impl;

import enums.PostStatus;
import repository.GenericRepository;
import utils.database.DataBaseAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCPostStatusRepositoryImpl implements GenericRepository<PostStatus> {

    private final DataBaseAccess dataSource;

    public JDBCPostStatusRepositoryImpl() {
        dataSource = new DataBaseAccess();
    }

    @Override
    public void save(PostStatus entity) { }

    @Override
    public void deleteById(PostStatus entity) { }

    @Override
    public void update(PostStatus entity) { }

    @Override
    public List<PostStatus> read() {
        String SQL = "SELECT * FROM poststatus";
        List<PostStatus> postStatuses = new ArrayList<>();
        try (Statement statement = dataSource.statement();
             ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()) {
                String postStatus = resultSet.getString(2).toUpperCase().replaceAll("\\s", "_");
                postStatuses.add(PostStatus.valueOf(postStatus));
            }

            dataSource.returnConnection(statement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return postStatuses;
    }
}
