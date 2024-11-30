package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements Serializable {
    private List<Employee> employees = new ArrayList<>();

    /**
     * This method adds an employee to the list of all employees.
     *
     */
    public void add(Employee employee) {
        Optional<Employee> newEmployee = Optional.empty();
        newEmployee = Optional.of(employee.clone());
        employees.add(newEmployee.get());
    }
    /**
     * This method returns the list of all registered employees in the system.
     *
     * @return List of all employees.
     */
    public List<Employee> getEmployees() {
        return employees;
    }
}
