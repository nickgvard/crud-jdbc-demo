package repository.jdbc_impl;

import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;
import enums.PostStatus;
import repository.GenericRepository;
import utils.database.DataBaseAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPostRepositoryImpl implements GenericRepository<Post> {

    private final DataBaseAccess dataAccess;
    private Writer writer;

    public JDBCPostRepositoryImpl(Writer writer) {
        this.writer = writer;
        dataAccess = new DataBaseAccess();
    }

    public JDBCPostRepositoryImpl() {
        dataAccess = new DataBaseAccess();
    }

    @Override
    public void save(Post entity) {
        String SQL = "INSERT INTO posts(Content, Created, PostStatusId, WriterId) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL, false)){
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

            addLabelsToPost(preparedStatement.getConnection(), entity, currentId);

            resultSet.close();
            dataAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteById(Post entity) {
        String SQL = "DELETE FROM posts WHERE PostId = ?";
        try (PreparedStatement preparedStatement = dataAccess.preparedStatement(SQL)){
            preparedStatement.setLong(1, entity.id());
            preparedStatement.executeUpdate();

            dataAccess.returnConnection(preparedStatement.getConnection());
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

            dataAccess.returnConnection(preparedStatement.getConnection());
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
                                new JDBCLabelRepositoryImpl().labelsOfThePost(resultSet.getLong(1))));
            }

            dataAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return posts;
    }

    private void addLabelsToPost(Connection connection, Post entity, long currentId) {
        JDBCLabelRepositoryImpl labelRepository = new JDBCLabelRepositoryImpl(new Post(currentId, entity.content(), entity.created(), entity.updated(), entity.labels()), connection);
        for(Label label : entity.labels())
            labelRepository.save(label);
    }
}