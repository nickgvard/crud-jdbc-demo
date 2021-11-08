package repository;

import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;
import model.enums.PostStatus;
import repository.interfaces.Repository;
import utils.database.DataBaseAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostRepository implements Repository<Post> {

    private final DataBaseAccess dataAccess;
    private Writer writer;

    public PostRepository(Writer writer) {
        this.writer = writer;
        dataAccess = new DataBaseAccess();
    }

    public PostRepository() {
        dataAccess = new DataBaseAccess();
    }

    @Override
    public void add(Post entity) {
        String SQL = "INSERT INTO posts(Content, Created, PostStatusId, WriterId) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setString(1, entity.content());
            preparedStatement.setTimestamp(2, entity.created());
            preparedStatement.setLong(3, PostStatus.UNDER_REVIEW.statusId());
            preparedStatement.setLong(4, writer.id());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long currentId;
            if(resultSet.next())
                currentId = resultSet.getLong(1);
            else
                throw new RuntimeException("Creating failed");

            addLabelsToPost(entity, currentId);

            resultSet.close();
            dataAccess.releaseConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void remove(Post entity) {
        String SQL = "DELETE FROM posts WHERE PostId = ?";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setLong(1, entity.id());
            preparedStatement.executeUpdate();

            dataAccess.releaseConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Post entity) {
        String SQL = "UPDATE posts SET Content = ?, Updated = ? WHERE PostId = ?";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setString(1, entity.content());
            preparedStatement.setTimestamp(2, entity.updated());
            preparedStatement.setLong(3, entity.id());
            preparedStatement.executeUpdate();

            dataAccess.releaseConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Post> read() {
        String SQL = "SELECT * FROM posts";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                posts.add(
                        new Post(
                                resultSet.getLong(1),
                                resultSet.getString(4),
                                resultSet.getTimestamp(5),
                                resultSet.getTimestamp(6),
                                new LabelRepository().labelsOfThePost(resultSet.getLong(1))));
            }
            dataAccess.releaseConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return posts;
    }

    private void addLabelsToPost(Post entity, long currentId) {
        LabelRepository labelRepository = new LabelRepository(new Post(currentId, entity.content(), entity.created(), entity.updated(), entity.labels()));
        for(Label label : entity.labels())
            labelRepository.add(label);
    }
}