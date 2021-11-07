package repository;

import model.entity.Post;
import model.entity.Writer;
import repository.interfaces.Repository;
import utils.database.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class WriterRepository implements Repository<Writer> {

    private final DataBaseAccess dataAccess;

    public WriterRepository() {
        dataAccess = new DataBaseAccess();
    }

    @Override
    public void add(Writer entity) {
        String SQL = "INSERT INTO writers(FirstName, LastName) VALUES (?,?)";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setString(1, entity.firstName());
            preparedStatement.setString(2, entity.lastName());
            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            long id;
//            if(resultSet.next())
//                id = resultSet.getLong(1);
//            else
//                throw new RuntimeException("Creating failed");
//            resultSet.close();
            dataAccess.releaseConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void remove(Writer entity) {
        String SQL = "DELETE FROM writers WHERE WriterId = ?";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setLong(1, entity.id());
            preparedStatement.executeUpdate();

            dataAccess.releaseConnection(preparedStatement.getConnection());
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

            dataAccess.releaseConnection(preparedStatement.getConnection());
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
            dataAccess.releaseConnection(preparedStatement.getConnection());
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
                                new LabelRepository().labelsOfThePost(resultSet.getLong(1))));
            }
            resultSet.close();
            dataAccess.releaseConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return posts;
    }
}