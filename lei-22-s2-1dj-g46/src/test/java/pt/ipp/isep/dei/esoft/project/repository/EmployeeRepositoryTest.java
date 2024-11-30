package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeRepositoryTest {
    @Test
    void testAddEmployee() {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee(123456789, "test@this.app", "Test", "Employee", "999999999", "Agency", 987654321, "Home", "password");
        employeeRepository.add(employee);
        assertTrue(employeeRepository.getEmployees().contains(employee));
    }

    @Test
    void ensureGetEmployeesWorks(){
        EmployeeRepository employeeRepository = new EmployeeRepository();
        List <Employee> employeeList = employeeRepository.getEmployees();
    }
}
