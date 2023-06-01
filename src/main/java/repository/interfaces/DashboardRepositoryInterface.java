package repository.interfaces;

import java.sql.SQLException;
import java.util.Map;

public interface DashboardRepositoryInterface {
    int getTotalEmployed() throws SQLException;

    int getTotalMaleEmployed() throws SQLException;

    int getTotalFemaleEmployed() throws SQLException;

    Map<String, Integer> getEmployedChartData() throws SQLException;
}

