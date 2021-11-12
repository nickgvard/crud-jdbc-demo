package repository.jdbc_impl;

import model.Post;
import enums.PostStatus;
import repository.PostRepository;
import utils.database.DataBaseAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPostRepositoryImpl implements PostRepository {

    private static final String GET_BY_ID = "SELECT * FROM posts where PostId = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM posts";
    private static final String SAVE_QUERY = "INSERT INTO posts (Content, Created, PostStatusId) VALUES (?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE posts SET Content = ?, Updated = ? WHERE PostId = ?";
    private static final String DELETE_QUERY = "DELETE FROM posts WHERE PostId = ?";

    @Override
    public Post getById(Long aLong) {
        Post post = null;
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_BY_ID)){
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                post = new Post(
                        resultSet.getLong(1),
                        resultSet.getString(4),
                        resultSet.getTimestamp(5),
                        resultSet.getTimestamp(6));
            }
            resultSet.close();
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                posts.add(
                        new Post(
                                resultSet.getLong(1),
                                resultSet.getString(4),
                                resultSet.getTimestamp(5),
                                resultSet.getTimestamp(6)));
            }
            resultSet.close();
            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post save(Post post) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(SAVE_QUERY)){
            preparedStatement.setString(1, post.content());
            preparedStatement.setTimestamp(2, post.created());
            preparedStatement.setLong(3, PostStatus.UNDER_REVIEW.statusId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long id;
            if(resultSet.next())
                id = resultSet.getLong(1);
            else
                throw new RuntimeException("Creating failed");

            resultSet.close();
            DataBaseAccess.returnConnection(preparedStatement.getConnection());

            return new Post(id, post.content(), post.created(), post.updated());
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Post update(Post post) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(UPDATE_QUERY)){
            preparedStatement.setString(1, post.content());
            preparedStatement.setTimestamp(2, post.updated());
            preparedStatement.setLong(3, post.id());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return post;
    }

    @Override
    public Post deleteById(Post post) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(DELETE_QUERY)){
            preparedStatement.setLong(1, post.id());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return post;
    }
}