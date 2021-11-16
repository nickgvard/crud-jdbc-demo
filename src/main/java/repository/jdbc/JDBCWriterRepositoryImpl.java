package repository.jdbc;

import lombok.Cleanup;
import model.Post;
import model.Writer;
import repository.WriterRepository;
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

public class JDBCWriterRepositoryImpl implements WriterRepository {

    private static final String GET_BY_ID = "SELECT * FROM writers WHERE WriterId = ?";
    public static final String GET_POSTS_BY_ID = "SELECT * FROM posts WHERE WriterId = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM writers";
    private static final String SAVE_QUERY = "INSERT INTO writers (FirstName, LastName) VALUES (?,?)";
    private static final String UPDATE_QUERY = "UPDATE writers SET FirstName = ?, LastName = ? WHERE WriterId = ?";
    private static final String DELETE_QUERY = "DELETE FROM writers WHERE WriterId = ?";

    @Override
    public Writer getById(Long aLong) {
        Writer writer = null;
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_BY_ID);
            preparedStatement.setLong(1, aLong);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                writer = Writer.builder()
                        .id(resultSet.getLong(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .posts(getPostsByWriterId(resultSet.getLong(1)))
                        .build();
            }
            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return writer;
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_ALL_QUERY);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                writers.add(
                        Writer.builder()
                                .id(resultSet.getLong(1))
                                .firstName(resultSet.getString(2))
                                .lastName(resultSet.getString(3))
                                .posts(getPostsByWriterId(resultSet.getLong(1)))
                                .build());
            }
            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(SAVE_QUERY);
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.executeUpdate();

            @Cleanup ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long id;
            if(resultSet.next())
                id = resultSet.getLong(1);
            else
                throw new RuntimeException("Creating failed");

            DataBaseAccess.returnConnection(preparedStatement.getConnection());

            return Writer.builder()
                    .id(id)
                    .firstName(writer.getFirstName())
                    .lastName(writer.getLastName())
                    .build();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Writer update(Writer writer) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(UPDATE_QUERY);
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.setLong(3, writer.getId());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return writer;
    }

    @Override
    public Writer deleteById(Writer writer) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(DELETE_QUERY);
            preparedStatement.setLong(1, writer.getId());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return writer;
    }

    @Override
    public List<Post> getPostsByWriterId(long id) {
        List<Post> posts = new ArrayList<>();
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_POSTS_BY_ID);
            preparedStatement.setLong(1, id);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(
                        Post.builder().id(resultSet.getLong(1))
                        .content(resultSet.getString(4))
                        .created(resultSet.getTimestamp(5))
                        .updated(resultSet.getTimestamp(6))
                        .labels(new JDBCPostRepositoryImpl().getLabelsByPostId(resultSet.getLong(1)))
                        .build());
            }
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return posts;
    }
}