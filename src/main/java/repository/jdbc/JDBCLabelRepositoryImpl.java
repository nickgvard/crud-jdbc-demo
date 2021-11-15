package repository.jdbc;

import lombok.Cleanup;
import model.Label;
import repository.LabelRepository;
import utils.database.DataBaseAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class JDBCLabelRepositoryImpl implements LabelRepository {

    private static final String GET_BY_ID = "SELECT * FROM labels WHERE LabelId = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM labels";
    private static final String SAVE_QUERY = "INSERT INTO labels(Name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE labels SET name = ? WHERE LabelId = ?";
    private static final String DELETE_QUERY = "DELETE FROM labels WHERE LabelId = ?";

    @Override
    public Label getById(Long aLong) {
        Label label;
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_BY_ID);
            preparedStatement.setLong(1, aLong);
            @Cleanup  ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            label = Label.builder().id(resultSet.getLong(1)).name(resultSet.getString(3)).build();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return label;
    }

    @Override
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        try {
            @Cleanup Statement statement = DataBaseAccess.statement();
            @Cleanup ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                labels.add(
                        Label.builder()
                                .id(resultSet.getLong(1))
                                .name(resultSet.getString(3))
                                .build());
            }

            DataBaseAccess.returnConnection(statement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return labels;
    }

    @Override
    public Label save(Label label) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(SAVE_QUERY);
            preparedStatement.setString(1, label.getName());
            preparedStatement.executeUpdate();

            @Cleanup ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long id;
            if(resultSet.first())
                id = resultSet.getLong(1);
            else
                throw new RuntimeException("Creating failed");

            DataBaseAccess.returnConnection(preparedStatement.getConnection());

            return Label.builder().id(id).name(label.getName()).build();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Label update(Label label) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(UPDATE_QUERY);
            preparedStatement.setString(1, label.getName());
            preparedStatement.setLong(2, label.getId());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return label;
    }

    @Override
    public Label deleteById(Label label) {
        try {
            @Cleanup PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(DELETE_QUERY);
            preparedStatement.setLong(1, label.getId());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return label;
    }
}