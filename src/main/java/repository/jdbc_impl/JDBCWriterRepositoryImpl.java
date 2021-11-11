package repository.jdbc_impl;

import model.Writer;
import repository.WriterRepository;
import utils.database.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCWriterRepositoryImpl implements WriterRepository {

    private static final String GET_ALL_QUERY = "SELECT * FROM writers";
    private static final String GET_BY_ID = "SELECT * FROM writers where WriterId = ?";
    private static final String SAVE_QUERY = "INSERT INTO writers(FirstName, LastName) VALUES (?,?)";
    private static final String UPDATE_QUERY = "UPDATE writers SET FirstName = ?, LastName = ? WHERE WriterId = ?";
    private static final String DELETE_QUERY = "DELETE FROM writers WHERE WriterId = ?";

    @Override
    public Writer getById(Long aLong) {
        Writer writer = null;
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_BY_ID)){
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.first()) {
                writer = new Writer(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(4));
            }
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return writer;
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(GET_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                writers.add(
                        new Writer(
                                resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3)));
            }
            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(SAVE_QUERY)){
            preparedStatement.setString(1, writer.firstName());
            preparedStatement.setString(2, writer.lastName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long id;
            if(resultSet.first())
                id = resultSet.getLong(1);
            else
                throw new RuntimeException("Creating failed");

            resultSet.close();
            DataBaseAccess.returnConnection(preparedStatement.getConnection());

            return new Writer(id, writer.firstName(), writer.lastName());
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Writer update(Writer writer) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(UPDATE_QUERY)){
            preparedStatement.setString(1, writer.firstName());
            preparedStatement.setString(2, writer.lastName());
            preparedStatement.setLong(3, writer.id());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return writer;
    }

    @Override
    public Writer deleteById(Writer writer) {
        try (PreparedStatement preparedStatement = DataBaseAccess.preparedStatement(DELETE_QUERY)){
            preparedStatement.setLong(1, writer.id());
            preparedStatement.executeUpdate();

            DataBaseAccess.returnConnection(preparedStatement.getConnection());
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return writer;
    }
}