package repository.jdbc;

import enums.PostStatus;
import lombok.Cleanup;
import model.Label;
import model.Post;
import repository.PostRepository;
import utils.database.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class JDBCPostRepositoryImpl implements PostRepository {

    private static final String GET_BY_ID = "SELECT * FROM posts WHERE PostId = ?";
    private static final String GET_LABELS_BY_ID = "SELECT * FROM labels AS l LEFT JOIN posts_labels AS pl ON p.PostId = pl.PostId WHERE PostId = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM posts";
    private static final String SAVE_QUERY = "INSERT INTO posts (Content, Created, PostStatusId) VALUES (?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE posts SET Content = ?, Updated = ? WHERE PostId = ?";
    private static final String DELETE_QUERY = "DELETE FROM posts WHERE PostId = ?";

    @Override
    public Post getById(Long aLong) {
        Post post = null;
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_BY_ID);
            preparedStatement.setLong(1, aLong);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                post = Post.builder()
                        .id(resultSet.getLong(1))
                        .content(resultSet.getString(4))
                        .created(resultSet.getTimestamp(5))
                        .updated(resultSet.getTimestamp(6))
                        .labels(getLabelsByPostId(resultSet.getLong(1)))
                        .build();
            }
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_ALL_QUERY);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(
                        Post.builder()
                                .id(resultSet.getLong(1))
                                .content(resultSet.getString(4))
                                .created(resultSet.getTimestamp(5))
                                .updated(resultSet.getTimestamp(6))
                                .labels(getLabelsByPostId(resultSet.getLong(1)))
                                .build());
            }
            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return posts;
    }

    @Override
    public Post save(Post post) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(SAVE_QUERY);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, post.getCreated());
            preparedStatement.setLong(3, PostStatus.UNDER_REVIEW.getStatusId());
            preparedStatement.executeUpdate();

            @Cleanup ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long id;
            if(resultSet.next())
                id = resultSet.getLong(1);
            else
                throw new RuntimeException("Creating failed");

            DataBaseAccess.returnConnection(preparedStatement.getConnection());

            return Post.builder()
                    .id(id)
                    .content(post.getContent())
                    .created(post.getCreated())
                    .updated(post.getUpdated())
                    .build();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Post update(Post post) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(UPDATE_QUERY);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, post.getUpdated());
            preparedStatement.setLong(3, post.getId());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return post;
    }

    @Override
    public Post deleteById(Post post) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(DELETE_QUERY);
            preparedStatement.setLong(1, post.getId());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return post;
    }

    @Override
    public List<Label> getLabelsByPostId(long id) {
        List<Label> labels = new ArrayList<>();
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_LABELS_BY_ID);
            preparedStatement.setLong(1, id);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                labels.add(
                        Label.builder()
                                .id(resultSet.getLong(1))
                                .name(resultSet.getString(2))
                                .build());
            }
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return labels;
    }
}