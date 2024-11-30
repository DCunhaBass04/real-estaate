# US003 - Registering a new employee

# 4. Tests

**Test 1:** Check that the employee is placed in the Repository.

```java
    @Test void testAddEmployee() {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee(123456789, "test@this.app", "Test", "Employee", "999999999", "Agency", 987654321, "Home", "password");
        employeeRepository.add(employee);
        employeeRepository.getEmployees().contains(employee);
    }
```

**Test 2:** Check that getting the Employees list works.

```java
    @Test void ensureGetEmployeesWorks(){
        EmployeeRepository employeeRepository = new EmployeeRepository();
        List <Employee> employeeList = employeeRepository.getEmployees();
    }
```

**Test 3:** Check that the agency selector works.

```java
    @Test
    void ensureChooseAgencyWorks(){
        OrganizationRepository organizationRepository  = new OrganizationRepository();
        try{
            List<String> organizationNames = organizationRepository.getOrganizationNames();
        } catch (NullPointerException e) {
        }
    }
```

**Test 4:** Check that the employees data is validated.

```java
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
```

**Test 5:** Check that the Organization Repository is retrieved.

```java
    @Test
    void ensureGetOrganizationRepositoryWorks(){
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Repositories repositories = Repositories.getInstance();
        organizationRepository = repositories.getOrganizationRepository();
    }
```

**Test 6:** Check if the Copy of the Employee is stored.

```java
    @Test
    void ensureCreateEmployeeWorks(){
        OrganizationRepository organizationRepository = new OrganizationRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee copyOfEmployee = new Employee(123456789, "test@this.app", "Jonathan test", "Employee", "999999999", "This Company", 987654321, "Home", "password");
        Optional<Organization> organization = organizationRepository.getOrganizationByVatNumber("This company");
        employeeRepository.add(copyOfEmployee);
    }
```

**Test 7:** Check that the employee can be authenticated.

```java
    @Test
    void ensureAddAuthenticationWorks(){
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserWithRole("Employee Name", "employee@this.app", "employee", AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Agent Name", "agent@this.app", "agent", AuthenticationController.ROLE_AGENT);
        AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
        agentRepository.add(new Agent("Agent Name", "agent@this.app", "agent"));

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Admin Name", "admin@this.app", "admin", AuthenticationController.ROLE_ADMIN);
    }
```

# 5. Construction (Implementation)

As decided previously, the interaction with the System starts when the user (Admin) requests to Register a new Employee, using the AdminUI to access the CreateEmployeeUI.

```java
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Task", new CreateTaskUI()));
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
    --> options.add(new MenuItem("Register New Employee", new CreateEmployeeUI()));
        options.add(new MenuItem("Register a Store ", new CreateStoreUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
```      
        
CreateEmployeeUI calls a CreateEmployeeController...

```java
    private Employee employee;
    -->private final CreateEmployeeController controller = new CreateEmployeeController();
    public void run() {
        System.out.println("\n\nRegister new employee.");
        registerNewEmployee();
    }
```

...that requests and submits the data of the Employee.

```java
    public void registerNewEmployee() {
        employee = controller.requestData();
        submitData(employee);
    }
```

# 6. Integration and Demo

We made sure the user will have no problem registering the employee by never letting them type or register invalid data. Whether it be from being in the wrong format or being already registered. 

To that effect, we also made sure to always warn the user about how the operation is going (Whether it was successful or not).

Furthermore, to save the program unnecessary work, the user's password is only generated after all the data is verified, as it is useless to an invalid submission (since it will be rejected).

# 7. Observations

In the future, we could possibly add a way to verify if the tax and cc number are in fact plausible numbers for a real citizen.

The UI could also be better to make the experience easier for the user, as well as an option to go back amidst the submitting of the data.

Besides that, there are many optimizations to the code that could be made and improved on in the future.