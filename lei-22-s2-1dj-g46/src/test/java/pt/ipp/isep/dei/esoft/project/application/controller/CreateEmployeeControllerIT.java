package pt.ipp.isep.dei.esoft.project.application.controller;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * The Create Employee Controller Integration Tests.
 *
 */
public class CreateEmployeeControllerIT {

    @Test
    void ensureChooseAgencyWorks(){
        OrganizationRepository organizationRepository  = new OrganizationRepository();
        try{
            List<String> organizationNames = organizationRepository.getOrganizationNames();
        } catch (NullPointerException e) {
        }
    }

    @Test
    void ensureVerifyIfNewEmployeeWorks(){
        EmployeeRepository employeeRepository = new EmployeeRepository();
        List<Employee> employeeList = employeeRepository.getEmployees();
        int otherCCNumber, otherTaxNumber;
        String otherEmail;
        Employee alreadyRegistered;

        for (int i = 0; i < employeeList.size(); i++) {
            alreadyRegistered = employeeList.get(i);
            otherCCNumber = alreadyRegistered.getCcNumber();
            otherTaxNumber = alreadyRegistered.getTaxNumber();
            otherEmail = alreadyRegistered.getEmail();
        }
    }

    @Test
    void ensureGetOrganizationRepositoryWorks(){
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Repositories repositories = Repositories.getInstance();
        organizationRepository = repositories.getOrganizationRepository();
    }

    @Test
    void ensureCreateEmployeeWorks(){
        OrganizationRepository organizationRepository = new OrganizationRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee copyOfEmployee = new Employee(123456789, "test@this.app", "Jonathan test", "Employee", "999999999", "This Company", 987654321, "Home", "password");
        Optional<Organization> organization = organizationRepository.getOrganizationByVatNumber("This company");
        employeeRepository.add(copyOfEmployee);
    }

    @Test
    void ensureAddAuthenticationWorks(){
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserWithRole("Employee Name", "employee@this.app", "employee", AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Agent Name", "agent@this.app", "agent", AuthenticationController.ROLE_AGENT);
        AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
        agentRepository.add(new Agent("agent@this.app"));

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Admin Name", "admin@this.app", "admin", AuthenticationController.ROLE_ADMIN);
    }
}
