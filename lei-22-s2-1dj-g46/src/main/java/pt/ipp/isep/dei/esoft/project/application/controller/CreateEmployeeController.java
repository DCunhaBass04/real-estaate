package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.*;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.GenerateAndSavePassword;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;
import pt.isep.lei.esoft.auth.domain.model.Email;

/**
 * The CreateEmployeeController class represents a controller for creating employees.
 */
public class CreateEmployeeController {

    private EmployeeRepository employeeRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    private StoreRepository storeRepository = null;
    private UserRepository userRepository = null;

    /**
     * Default constructor that initializes the employee, authentication, and store repositories.
     */
    public CreateEmployeeController() {
        getEmployeeRepository();
        getAuthenticationRepository();
        getStoreRepository();
        getUserRepository();
    }

    /**
     * Constructor that allows setting specific repositories.
     *
     * @param employeeRepository       the employee repository to set
     * @param authenticationRepository the authentication repository to set
     * @param storeRepository          the store repository to set
     */
    public CreateEmployeeController(EmployeeRepository employeeRepository, AuthenticationRepository authenticationRepository, StoreRepository storeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.authenticationRepository = authenticationRepository;
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
    }

    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private StoreRepository getStoreRepository() {
        if (storeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }
    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();
            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }

    /**
     * This method requests the employee's data.
     *
     * @return The employee.
     */
    public Employee requestData() {
        Employee employee;
        Scanner input = new Scanner(System.in);
        System.out.printf("Insert the data in the appropriate format.%nAn employee cannot be registered more than once.%n");
        VerifyInput verifyInput = new VerifyInput();

        String name = verifyInput.verifyComplexString(input, "Name");

        String email = getEmail();

        String phoneNumber = verifyInput.verifySimpleString(input, "Phone Number");

        String store = chooseStore();

        String role = chooseRole();

        int ccNumber = verifyInput.verifyInt(input, "Citizen Card Number");

        int taxNumber = verifyInput.verifyInt(input, "Tax Number");

        employee = requestAddress(email, name, role, phoneNumber, store, ccNumber, taxNumber);

        return employee;
    }

    /**
     * This method validates the input email.
     *
     * @return The email.
     */
    public String getEmail() {
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        String emailID = "";
        boolean valid = false;
        do {
            try {
                emailID = verifyInput.verifySimpleString(input, "Email");
                Email email = new Email(emailID);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.printf("Invalid email. Try again.%n");
            }
        } while (!valid);
        return emailID;
    }

    /**
     * This method gives the user the choice to register the employee's address with the rest of their data.
     *
     * @param email       The employee's email.
     * @param name        The employee's name.
     * @param role        The employee's role.
     * @param phoneNumber The employee's phone number.
     * @param store       The employee's agency.
     * @param ccNumber    The employee's citizen card number.
     * @param taxNumber   The employee's tax identification number.
     * @return the employee.
     */
    public Employee requestAddress(String email, String name, String role, String phoneNumber, String store, int ccNumber, int taxNumber) {
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        int option = verifyInput.verifyInt(input, "Address.(Optional)%n1 - Insert address.%n(Select any other NUMBER%nto skip.)");
        if (option == 1) {
            String address = verifyInput.verifyComplexString(input, "Address");
            //with address without password
            return new Employee(ccNumber, email, name, role, phoneNumber, store, taxNumber, address);
        } else {
            //without address without password
            return new Employee(email, name, role, phoneNumber, store, ccNumber, taxNumber);
        }
    }

    public String choose(List<String> options, String choice, String choiceTitle) {
        if (options.isEmpty()) {
            System.out.printf("%nThere are no %ss registered in the system yet.%n", choiceTitle);
            return choice = "NOT_AVAILABLE";
        }
        VerifyInput verifyInput = new VerifyInput();
        Scanner input = new Scanner(System.in);
        System.out.printf("%nSelect %s.%n", choiceTitle);
        int index = 0;
        for (Object o : options) {
            index++;
            System.out.println(index + ". " + o.toString());
        }
        boolean valid = false;
        int option;
        do {
            option = verifyInput.verifyInt(input, "a valid option");
            if (option < 1 || option > options.size()) {
                System.out.println("Option unavailable.");
            } else {
                choice = options.get(option - 1);
                System.out.printf("%s", choice);
                valid = true;
            }
        } while (!valid);
        return choice;
    }

    /**
     * This method make the user select an available role for the employee.
     *
     * @return selected role
     */
    public String chooseRole() {
        System.out.println();
        String role = "";
        List<String> options = new ArrayList<String>();
        options.add("Agent");
        options.add("Store Manager");
        role = choose(options, role, "role");
        System.out.println();
        return role;
    }

    public String chooseStore() {
        String store = "";
        List<String> storeNames = storeRepository.getStoreNames();
        store = choose(storeNames, store, "store");
        return store;
    }

    /**
     * This method verifies the employee's data.
     *
     * @param employee The employee.
     */
    public boolean verifyData(Employee employee) {
        boolean validNewEmployee = true;
        int ccNumber = employee.getCcNumber(), taxNumber = employee.getTaxNumber();
        String email = employee.getEmail(), store = employee.getStore();
        boolean newEmployee = verifyIfNewEmployee(ccNumber, taxNumber, email);
        boolean storeHasNoOtherStoreManagers = true;
        if(employee.getRole().equals("Store Manager")) {
            storeHasNoOtherStoreManagers = verifyIfStoreManagerSlotIsEmpty(store);
        }
        if (!newEmployee || (!storeHasNoOtherStoreManagers && employee.getRole().equals("Store Manager"))) {
            validNewEmployee = false;
        }
        return validNewEmployee;
    }

    public boolean verifyIfStoreManagerSlotIsEmpty(String store) {
        boolean valid = true;
        Store requestedStore = storeRepository.getStoreByName(store);
        if (requestedStore.containsStoreManager()) {
            valid = false;
            System.out.printf("The store you tried to register this store manager in has already been assigned a store manager.%n");
        }
        return valid;
    }

    /**
     * This method verifies if the employee is valid.
     *
     * @param ccNumber  the citizen card number of the employee.
     * @param taxNumber the tax identification number of the employee.
     * @param email     the email/id of the employee.
     */
    public boolean verifyIfNewEmployee(int ccNumber, int taxNumber, String email) {
        int otherCCNumber, otherTaxNumber;
        String otherEmail;
        Employee alreadyRegistered;
        boolean valid = true;

        List<Employee> employeeList = employeeRepository.getEmployees();
        for (int i = 0; i < employeeList.size() && valid; i++) {
            alreadyRegistered = employeeList.get(i);
            otherCCNumber = alreadyRegistered.getCcNumber();
            otherTaxNumber = alreadyRegistered.getTaxNumber();
            otherEmail = alreadyRegistered.getEmail();
            if (Objects.equals(otherEmail, email) || otherCCNumber == ccNumber || otherTaxNumber == taxNumber) {
                valid = false;
                System.out.printf("This employee has already been registered.%n");
            }
        }
        return valid;
    }

    /**
     * This method makes a copy of the employee and saves it.
     *
     * @param employee The employee that stored the requested data.
     */
    public void createEmployee(Employee employee, String password) {
        String name = employee.getName();

        String email = employee.getEmail();

        String phoneNumber = employee.getPhoneNumber();

        String address = employee.getAddress();

        String store = employee.getStore();

        String role = employee.getRole();

        int ccNumber = employee.getCcNumber();

        int taxNumber = employee.getTaxNumber();

        Employee copyOfEmployee = new Employee(ccNumber, email, name, role, phoneNumber, store, taxNumber, address, password);

        addAuthentication(copyOfEmployee);

        employeeRepository.add(copyOfEmployee);

        userRepository.add(copyOfEmployee);

        storeRepository.getStoreByName(store).addEmployee(copyOfEmployee);

        GenerateAndSavePassword generateAndSavePassword = new GenerateAndSavePassword();

        generateAndSavePassword.sendEmployeePassword(copyOfEmployee);
    }

    /**
     * This method makes the employee a registered user.
     *
     * @param copyOfEmployee The copy of the submitted employee
     */
    public void addAuthentication(Employee copyOfEmployee) {
        String name = copyOfEmployee.getName(), email = copyOfEmployee.getEmail(), password = copyOfEmployee.getPassword(), role = copyOfEmployee.getRole();
        switch (role) {
            case "Store Manager":
                authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);
                authenticationRepository.addUserWithRole(name, email, password, AuthenticationController.ROLE_STORE_MANAGER);
                break;
            case "Agent":
                authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
                authenticationRepository.addUserWithRole(name, email, password, AuthenticationController.ROLE_AGENT);
                AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
                agentRepository.add(new Agent(copyOfEmployee));
                break;
        }
    }
}
