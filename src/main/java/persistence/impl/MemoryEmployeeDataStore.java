package persistence.impl;

import entity.employee.Employee;
import persistence.boundary.EmployeeDataStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Memory data store for employees, used in tests for other classes
 */
public class MemoryEmployeeDataStore implements EmployeeDataStore {

    /**
     * The list of employees
     */
    private final List<Employee> employeeList = new ArrayList<>();

    // Inherited javadocs
    @Override
    public void remove(int staffNumber) throws IOException {
        employeeList.removeIf(employee -> employee.getStaffNumber() == staffNumber);
    }

    // Inherited javadocs
    @Override
    public void save(Employee employee) throws IOException {
        remove(employee.getStaffNumber());
        employeeList.add(employee);
    }

    // Inherited javadocs
    @Override
    public Optional<Employee> get(int staffNumber) throws IOException {
        return employeeList.stream()
                .filter(employee -> employee.getStaffNumber() == staffNumber)
                .findFirst();
    }

    // Inherited javadocs
    @Override
    public List<Employee> getEmployees() throws IOException {
        return employeeList;
    }
}
