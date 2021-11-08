package repository;

import model.enums.PostStatus;
import repository.interfaces.Repository;
import utils.database.DataBaseAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatusRepository implements Repository<PostStatus> {

    private final DataBaseAccess dataSource;

    public PostStatusRepository() {
        dataSource = new DataBaseAccess();
    }

    @Override
    public void add(PostStatus entity) { }

    @Override
    public void remove(PostStatus entity) { }

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
            dataSource.releaseConnection(statement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return postStatuses;
    }
}
