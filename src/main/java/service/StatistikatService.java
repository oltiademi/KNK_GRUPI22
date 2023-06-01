package service;

import repository.StatistikatRepository;
import repository.interfaces.StatistikatRepositoryInterface;
import service.interfaces.StatistikatServiceInterface;

import java.sql.*;
import java.util.Map;

public class StatistikatService implements StatistikatServiceInterface {
    private StatistikatRepositoryInterface statistikatRepositoryInterface;

    public StatistikatService() {
        try {
            statistikatRepositoryInterface = new StatistikatRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getBachelorCount() throws SQLException {
        return statistikatRepositoryInterface.getBachelorCount();
    }
    public int getMasterCount() throws SQLException {
        return statistikatRepositoryInterface.getMasterCount();
    }
    public int getDoktoratureCount() throws SQLException {
        return statistikatRepositoryInterface.getDoktoratureCount();
    }
    public int getIKSBachelorCount() throws SQLException {
        return statistikatRepositoryInterface.getIKSBachelorCount();
    }
    public int getIKSMasterCount() throws SQLException {
        return statistikatRepositoryInterface.getIKSMasterCount();
    }
    public int getEARBachelorCount() throws SQLException {
        return statistikatRepositoryInterface.getEARBachelorCount();
    }
    public int getEARMasterCount() throws SQLException {
        return statistikatRepositoryInterface.getEARMasterCount();
    }
    public int getTIKBachelorCount() throws SQLException {
        return statistikatRepositoryInterface.getTIKBachelorCount();
    }
    public int getTIKMasterCount() throws SQLException {
        return statistikatRepositoryInterface.getTIKMasterCount();
    }
    public int getElektroenergjetikeBachelorCount() throws SQLException {
        return statistikatRepositoryInterface.getElektroenergjetikeBachelorCount();
    }
    public Map<String, Integer> getTitulliChartData() throws SQLException {
        return statistikatRepositoryInterface.getTitulliChartData();
    }
    public Map<String, Integer> getDrejtimiChartData() throws SQLException {
        return statistikatRepositoryInterface.getDrejtimiChartData();
    }

}
