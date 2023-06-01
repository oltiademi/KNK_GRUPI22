package repository.interfaces;

import javafx.collections.ObservableList;
import models.Employed;
import models.dto.CreateEmployedDto;
import models.dto.UpdateEmployedDto;
import models.dto.EmployedFilter;
import java.sql.SQLException;
import java.util.List;

public interface EmployedRepositoryInterface {
    Employed insert(CreateEmployedDto employedDto) throws SQLException;
    Employed update(UpdateEmployedDto updateEmployedDto) throws SQLException;
    Employed getEmployedById(String id) throws SQLException;
    public ObservableList<Employed> showEmployedList();
    List<Employed> getEmployedByFilter(EmployedFilter filter) throws SQLException;
    void deleteEmployed(String id) throws SQLException;
}
