package service;

import javafx.collections.ObservableList;
import models.Employed;
import models.dto.CreateEmployedDto;
import models.dto.EmployedFilter;
import models.dto.UpdateEmployedDto;
import repository.EmployedRepository;
import repository.interfaces.EmployedRepositoryInterface;
import service.interfaces.EmployedServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class EmployedService implements EmployedServiceInterface {
    private EmployedRepositoryInterface employedRepository;

    public EmployedService() {
        this.employedRepository = new EmployedRepository();
    }
    public Employed createEmployed(CreateEmployedDto employed) throws SQLException {
        return employedRepository.insert(employed);
    }
    public List<Employed> getEmployedByFilter(EmployedFilter filter) throws SQLException {
        return employedRepository.getEmployedByFilter(filter);
    }
    public ObservableList<Employed> showEmployedList(){
        return employedRepository.showEmployedList();
    }
    public void deleteEmployed(String id) throws SQLException {
        employedRepository.deleteEmployed(id);
    }
    public Employed updateEmployed(UpdateEmployedDto updateEmployedDto) throws SQLException {
        return employedRepository.update(updateEmployedDto);
    }
}
