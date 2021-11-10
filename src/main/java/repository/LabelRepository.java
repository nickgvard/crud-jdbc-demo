package repository;

import model.entity.Label;
import model.entity.Post;
import repository.interfaces.Repository;
import utils.database.DataBaseAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelRepository implements Repository<Label> {

    private final DataBaseAccess dataAccess;
    private Post post;
    private Connection connection;

    public LabelRepository(Post post, Connection connection) {
        this.post = post;
        this.connection = connection;
        dataAccess = new DataBaseAccess();
    }

    public LabelRepository(Post post) {
        this.post = post;
        dataAccess = new DataBaseAccess();
    }

    public LabelRepository() {
        dataAccess = new DataBaseAccess();
    }

    @Override
    public void add(Label entity) {
        String SQL = "INSERT INTO labels(Name, PostId) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL);){
            preparedStatement.setString(1, entity.name());
            preparedStatement.setLong(2, post.id());
            preparedStatement.executeUpdate();

            connection.commit();

            dataAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            try {
                connection.rollback();
            }catch (SQLException exRollback) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void remove(Label entity) {
        String SQL = "DELETE FROM labels WHERE LabelId = ?";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL, true)){
            preparedStatement.setLong(1, entity.id());
            preparedStatement.executeUpdate();

            dataAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Label entity) {
        String SQL = "UPDATE labels SET name = ? WHERE LabelId = ?";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL, true)){
            preparedStatement.setString(1, entity.name());
            preparedStatement.setLong(2, entity.id());
            preparedStatement.executeUpdate();

            dataAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Label> read() {
        String SQL = "SELECT * FROM labels";
        List<Label> labels = new ArrayList<>();
        try (Statement statement = dataAccess.statement();
             ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()) {
                labels.add(
                        new Label(
                                resultSet.getLong(1),
                                resultSet.getString(3)));
            }

            dataAccess.returnConnection(statement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return labels;
    }

    public List<Label> labelsOfThePost(long postId) {
        String SQL = "SELECT LabelId, Name, PostId FROM labels " +
                "WHERE PostId = ?";
        List<Label> labels = new ArrayList<>();
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setLong(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                labels.add(
                        new Label(
                                resultSet.getLong(1),
                                resultSet.getString(2)));
            }

            resultSet.close();
            dataAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return labels;
    }
}