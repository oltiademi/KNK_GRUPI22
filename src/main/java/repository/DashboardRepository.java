package repository;

import repository.interfaces.DashboardRepositoryInterface;
import service.ConnectionUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DashboardRepository implements DashboardRepositoryInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DashboardRepository() throws SQLException {
        connection = ConnectionUtil.getConnection();
    }

    public int getTotalEmployed() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("COUNT(id)");
        }
        return 0;
    }

    public int getTotalMaleEmployed() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE gjinia = 'Mashkull'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("COUNT(id)");
        }
        return 0;
    }

    public int getTotalFemaleEmployed() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE gjinia = 'Femër'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("COUNT(id)");
        }
        return 0;
    }

    public Map<String, Integer> getEmployedChartData() throws SQLException {
        Map<String, Integer> chartData = new HashMap<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT gjinia, COUNT(*) FROM employed GROUP BY gjinia");
        while (resultSet.next()) {
            String gjinia = resultSet.getString("gjinia");
            int count = resultSet.getInt("COUNT(*)");
            chartData.put(gjinia, count);
        }
        return chartData;
    }
}

