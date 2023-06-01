package service;

import javafx.scene.chart.PieChart;
import repository.DashboardRepository;
import repository.interfaces.DashboardRepositoryInterface;
import service.interfaces.DashboardServiceInterface;

import java.sql.SQLException;
import java.util.Map;

public class DashboardService implements DashboardServiceInterface {
    private DashboardRepositoryInterface dashboardRepositoryInterface;

    public DashboardService() {
        try {
            this.dashboardRepositoryInterface = new DashboardRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalEmployed() throws SQLException {
        return dashboardRepositoryInterface.getTotalEmployed();
    }

    public int getTotalMaleEmployed() throws SQLException {
        return dashboardRepositoryInterface.getTotalMaleEmployed();
    }

    public int getTotalFemaleEmployed() throws SQLException {
        return dashboardRepositoryInterface.getTotalFemaleEmployed();
    }

    public Map<String, Integer> getEmployedChartData() throws SQLException {
        return dashboardRepositoryInterface.getEmployedChartData();
    }
}

