package repository.jdbc_impl;

import model.entity.Post;
import model.entity.Writer;
import repository.GenericRepository;
import utils.database.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCWriterRepositoryImpl implements GenericRepository<Writer> {

    private final DataBaseAccess dataAccess;

    public JDBCWriterRepositoryImpl() {
        dataAccess = new DataBaseAccess();
    }

    @Override
    public void save(Writer entity) {
        String SQL = "INSERT INTO writers(FirstName, LastName) VALUES (?,?)";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setString(1, entity.firstName());
            preparedStatement.setString(2, entity.lastName());
            preparedStatement.executeUpdate();

            dataAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteById(Writer entity) {
        String SQL = "DELETE FROM writers WHERE WriterId = ?";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setLong(1, entity.id());
            preparedStatement.executeUpdate();

            dataAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Writer entity) {
        String SQL = "UPDATE writers SET FirstName = ?, LastName = ? WHERE WriterId = ?";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setString(1, entity.firstName());
            preparedStatement.setString(2, entity.lastName());
            preparedStatement.setLong(3, entity.id());
            preparedStatement.executeUpdate();

            dataAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Writer> read() {
        String SQL = "SELECT * FROM writers";
        List<Writer> writers = new ArrayList<>();
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                writers.add(
                        new Writer(
                                resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                postsOfTheWriter(resultSet.getLong(1))));
            }
            dataAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return writers;
    }

    private List<Post> postsOfTheWriter(long id) {
        String SQL = "SELECT PostId, Content, Created, Updated FROM posts " +
                "WHERE WriterId = ?";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(
                        new Post(
                                resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getTimestamp(3),
                                resultSet.getTimestamp(4),
                                new JDBCLabelRepositoryImpl().labelsOfThePost(resultSet.getLong(1))));
            }
            resultSet.close();
            dataAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return posts;
    }
}