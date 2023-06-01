package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Employed;
import models.dto.CreateEmployedDto;
import models.dto.EmployedFilter;
import models.dto.UpdateEmployedDto;
import repository.interfaces.EmployedRepositoryInterface;
import service.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployedRepository implements EmployedRepositoryInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public Employed insert(CreateEmployedDto employed) throws SQLException {
        String sql = "INSERT INTO employed (id, emri, mbiemri, gjinia, titulli, drejtimi, profesioni, kompania) VALUES (?,?,?,?,?,?,?,?)";
        connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employed.getId());
            preparedStatement.setString(2, employed.getEmri());
            preparedStatement.setString(3, employed.getMbiemri());
            preparedStatement.setString(4, employed.getGjinia());
            preparedStatement.setString(5, employed.getTitulli());
            preparedStatement.setString(6, employed.getDrejtimi());
            preparedStatement.setString(7, employed.getProfesioni());
            preparedStatement.setString(8, employed.getKompania());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getEmployedById(employed.getId());
    }

    @Override
    public Employed update(UpdateEmployedDto updateEmployedDto) throws SQLException {
        String sql = "UPDATE employed SET emri = ?, mbiemri = ?, gjinia = ?, titulli = ?, drejtimi = ?, profesioni = ?, kompania = ? WHERE id = ?";
        connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, updateEmployedDto.getEmri());
        statement.setString(2, updateEmployedDto.getMbiemri());
        statement.setString(3, updateEmployedDto.getGjinia());
        statement.setString(4, updateEmployedDto.getTitulli());
        statement.setString(5, updateEmployedDto.getDrejtimi());
        statement.setString(6, updateEmployedDto.getProfesioni());
        statement.setString(7, updateEmployedDto.getKompania());
        statement.setString(8, updateEmployedDto.getId());
        statement.executeUpdate();

        return null;
    }
    @Override
    public Employed getEmployedById(String id) throws SQLException {
        String sql = "SELECT * FROM employed WHERE id = ?";
        connection = ConnectionUtil.getConnection();
        Employed employed = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employed = new Employed(
                        resultSet.getString("id"),
                        resultSet.getString("emri"),
                        resultSet.getString("mbiemri"),
                        resultSet.getString("gjinia"),
                        resultSet.getString("titulli"),
                        resultSet.getString("drejtimi"),
                        resultSet.getString("profesioni"),
                        resultSet.getString("kompania")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employed;
    }
    public ObservableList<Employed> showEmployedList() {
        ObservableList<Employed> employedList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employed";
        try {
            Employed employed;
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employed = new Employed(resultSet.getString("id"),
                        resultSet.getString("emri"),
                        resultSet.getString("mbiemri"),
                        resultSet.getString("gjinia"),
                        resultSet.getString("titulli"),
                        resultSet.getString("drejtimi"),
                        resultSet.getString("profesioni"),
                        resultSet.getString("kompania"));

                employedList.add(employed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employedList;
    }
    @Override
    public List<Employed> getEmployedByFilter(EmployedFilter filter) throws SQLException {
        String sql = "SELECT * FROM employed WHERE 1=1";
        List<Object> params = new ArrayList<>();
        List<Employed> employedList = new ArrayList<>();

        if (filter != null) {
            sql += filter.getFilterQuery();
            params.addAll(filter.getFilterParams());
        }
        connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, (String) params.get(i));
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employed employed = new Employed(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                );
                employedList.add(employed);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employedList;
    }
    @Override
    public void deleteEmployed(String id) throws SQLException {
        String sql = "DELETE FROM employed WHERE id = ?";
        connection = ConnectionUtil.getConnection();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
