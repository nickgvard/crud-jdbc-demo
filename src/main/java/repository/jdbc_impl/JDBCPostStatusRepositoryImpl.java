package repository.jdbc_impl;

import enums.PostStatus;
import repository.PostStatusRepository;
import utils.database.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCPostStatusRepositoryImpl implements PostStatusRepository {

    private static final String GET_ALL_QUERY = "SELECT * FROM poststatus";
    private static final String GET_BY_ID = "SELECT PostStatusId FROM poststatus where PostStatusId = ?";
    private static final String SAVE_QUERY = "INSERT INTO poststatus (PostStatusId, Name) VALUES (?,?)";
    private static final String UPDATE_QUERY = "UPDATE poststatus SET Name = ? WHERE PostStatusId = ?";
    private static final String DELETE_QUERY = "DELETE FROM poststatus WHERE PostStatusId = ?";

    @Override
    public PostStatus getById(Long aLong) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_BY_ID)){
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.first()) {
                int id = resultSet.getInt(1);
                switch (id) {
                    case 1:
                        return PostStatus.ACTIVE;
                    case 2:
                        return PostStatus.UNDER_REVIEW;
                    case 3:
                        return PostStatus.DELETED;
                    default:
                        throw new RuntimeException("Wrong return from data base");
                }
            }
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return null;
    }

    @Override
    public List<PostStatus> getAll() {
        List<PostStatus> postStatuses = new ArrayList<>();
        try (Statement statement = DataBaseAccess.statement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY)){
            while (resultSet.next()) {
                String postStatus = resultSet.getString(2).toUpperCase().replaceAll("\\s", "_");
                postStatuses.add(PostStatus.valueOf(postStatus));
            }

            DataBaseAccess.returnConnection(statement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return postStatuses;
    }

    @Override
    public PostStatus save(PostStatus postStatus) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(SAVE_QUERY, false)){
            preparedStatement.setLong(1, postStatus.statusId());
            preparedStatement.setString(2, postStatus.name());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());

            return PostStatus.valueOf(postStatus.name());
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public PostStatus update(PostStatus postStatus) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(UPDATE_QUERY)){
            preparedStatement.setString(1, postStatus.name());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return postStatus;
    }

    @Override
    public PostStatus deleteById(PostStatus postStatus) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(DELETE_QUERY)){
            preparedStatement.setLong(1, postStatus.statusId());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return postStatus;
    }
}
