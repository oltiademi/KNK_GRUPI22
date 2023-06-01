package repository;
import repository.interfaces.StatistikatRepositoryInterface;
import service.ConnectionUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StatistikatRepository implements StatistikatRepositoryInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public StatistikatRepository() throws SQLException {
        connection = ConnectionUtil.getConnection();
    }
    public int getBachelorCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getMasterCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Master(MSc)'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getDoktoratureCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Doktoraturë(PHD)'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getIKSBachelorCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)' AND drejtimi = 'Inxhinieri Kompjuterike dhe Softuerike'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getIKSMasterCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Master(MSc)' AND drejtimi = 'Inxhinieri Kompjuterike dhe Softuerike'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getEARBachelorCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)' AND drejtimi = 'Elektronikë, Automatikë dhe Robotikë'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getEARMasterCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Master(MSc)' AND drejtimi = 'Elektronikë, Automatikë dhe Robotikë'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getTIKBachelorCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)' AND drejtimi = 'Teknologjite e Informacionit dhe Komunikimit'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getTIKMasterCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Master(MSc)' AND drejtimi = 'Teknologjite e Informacionit dhe Komunikimit'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public int getElektroenergjetikeBachelorCount() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)' AND drejtimi = 'Elektroenergjetikë'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    public Map<String, Integer> getTitulliChartData() throws SQLException {
        Map<String, Integer> chartData = new HashMap<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT titulli, COUNT(*) FROM employed GROUP BY titulli");
        while (resultSet.next()) {
            String titulli = resultSet.getString("titulli");
            int count = resultSet.getInt("COUNT(*)");
            chartData.put(titulli, count);
        }
        return chartData;
    }
    public Map<String, Integer> getDrejtimiChartData() throws SQLException {
        Map<String, Integer> chartData = new HashMap<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT drejtimi, COUNT(*) FROM employed GROUP BY drejtimi");
        while (resultSet.next()) {
            String drejtimi = resultSet.getString("drejtimi");
            int count = resultSet.getInt("COUNT(*)");
            chartData.put(drejtimi, count);
        }
        return chartData;
    }

}
