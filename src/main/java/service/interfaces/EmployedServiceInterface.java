package service.interfaces;

import javafx.collections.ObservableList;
import models.Employed;
import models.dto.CreateEmployedDto;
import models.dto.EmployedFilter;
import models.dto.UpdateEmployedDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployedServiceInterface {
    Employed createEmployed(CreateEmployedDto employed) throws SQLException;
    List<Employed> getEmployedByFilter(EmployedFilter filter) throws SQLException;
    ObservableList<Employed> showEmployedList();
    void deleteEmployed(String id) throws SQLException;
    Employed updateEmployed(UpdateEmployedDto updateEmployedDto) throws SQLException;
}

