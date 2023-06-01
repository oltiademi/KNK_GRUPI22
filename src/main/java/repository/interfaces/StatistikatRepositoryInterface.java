package repository.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface StatistikatRepositoryInterface {
    int getBachelorCount() throws SQLException;

    int getMasterCount() throws SQLException;

    int getDoktoratureCount() throws SQLException;

    int getIKSBachelorCount() throws SQLException;

    int getIKSMasterCount() throws SQLException;

    int getEARBachelorCount() throws SQLException;

    int getEARMasterCount() throws SQLException;

    int getTIKBachelorCount() throws SQLException;

    int getTIKMasterCount() throws SQLException;

    int getElektroenergjetikeBachelorCount() throws SQLException;

    Map<String, Integer> getTitulliChartData() throws SQLException;

    Map<String, Integer> getDrejtimiChartData() throws SQLException;
}

