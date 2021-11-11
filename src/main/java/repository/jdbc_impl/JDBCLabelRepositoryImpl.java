package repository.jdbc_impl;

import model.entity.Label;
import repository.LabelRepository;
import utils.database.DataBaseAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCLabelRepositoryImpl implements LabelRepository {

    private static final String GET_BY_ID = "SELECT * FROM labels WHERE LabelId = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM labels";
    private static final String SAVE_QUERY = "INSERT INTO labels(Name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE labels SET name = ? WHERE LabelId = ?";
    private static final String DELETE_QUERY = "DELETE FROM labels WHERE LabelId = ?";

    @Override
    public Label getById(Long aLong) {
        Label label;
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_BY_ID)) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            label = new Label(resultSet.getLong(1), resultSet.getString(3));

            resultSet.close();
            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return label;
    }

    @Override
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        try (Statement statement = DataBaseAccess.statement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY)){
            while (resultSet.next()) {
                labels.add(
                        new Label(
                                resultSet.getLong(1),
                                resultSet.getString(3)));
            }

            DataBaseAccess.returnConnection(statement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return labels;
    }

    @Override
    public Label save(Label label) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(SAVE_QUERY)){
            preparedStatement.setString(1, label.name());
            preparedStatement.executeUpdate();


        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return label;
    }

    @Override
    public Label update(Label label) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(UPDATE_QUERY)){
            preparedStatement.setString(1, label.name());
            preparedStatement.setLong(2, label.id());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return label;
    }

    @Override
    public Label deleteById(Label label) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(DELETE_QUERY)){
            preparedStatement.setLong(1, label.id());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return label;
    }

//    public List<Label> labelsOfThePost(long postId) {
//        String SQL = "SELECT LabelId, Name, PostId FROM labels " +
//                "WHERE PostId = ?";
//        List<Label> labels = new ArrayList<>();
//        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(SQL)){
//            preparedStatement.setLong(1, postId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                labels.add(
//                        new Label(
//                                resultSet.getLong(1),
//                                resultSet.getString(2)));
//            }
//
//            resultSet.close();
//            DataBaseAccess.returnConnection(preparedStatement.getConnection());
//        }catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//        return labels;
//    }
}