package repository;

import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Employed;
import models.Pagination;
import models.dto.CreateEmployedDto;
import models.dto.EmployedFilter;
import models.dto.UpdateEmployedDto;
import repository.interfaces.EmployedRepositoryInterface;
import service.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployedRepository implements EmployedRepositoryInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;

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
        } finally {
            closeResources();
        }

        return getEmployedById(employed.getId());
    }

    @Override
    public Employed update(UpdateEmployedDto updateEmployedDto) throws SQLException {
        String gjinia = updateEmployedDto.getGjinia();

        if (gjinia != null) {
            String sql = "UPDATE employed SET "
                    + "emri = '" + updateEmployedDto.getEmri()
                    + "', mbiemri = '" + updateEmployedDto.getMbiemri()
                    + "', gjinia = '" + gjinia
                    + "', titulli = '" + updateEmployedDto.getTitulli()
                    + "', drejtimi = '" + updateEmployedDto.getDrejtimi()
                    + "', profesioni = '" + updateEmployedDto.getProfesioni()
                    + "', kompania = '" + updateEmployedDto.getKompania()
                    + "' WHERE id = '" + updateEmployedDto.getId() + "'";
            connection = ConnectionUtil.getConnection();
                statement = connection.createStatement();
                statement.executeUpdate(sql);
        }
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
        } finally {
            closeResources();
        }

        return employed;
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
                preparedStatement.setObject(i + 1, params.get(i));
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employed employed = new Employed(
                        resultSet.getString("id"),
                        resultSet.getString("emri"),
                        resultSet.getString("mbiemri"),
                        resultSet.getString("gjinia"),
                        resultSet.getString("titulli"),
                        resultSet.getString("drejtimi"),
                        resultSet.getString("profesioni"),
                        resultSet.getString("kompania")
                );
                employedList.add(employed);
            }
        } finally {
            closeResources();
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
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
