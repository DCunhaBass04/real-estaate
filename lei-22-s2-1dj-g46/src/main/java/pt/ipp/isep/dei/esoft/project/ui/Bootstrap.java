package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Users.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.SaveAndReadData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Bootstrap implements Runnable {

    public void run() {
        try {
            SaveAndReadData saveAndReadData = new SaveAndReadData();
            saveAndReadData.readFile("repositories.dat");
            createUsersWithSavedData();
        } catch (IOException e) {
            addStore();
            addUsers();
            try {
                clearPasswords();
            } catch (IOException i) {
                throw new RuntimeException(i);
            }
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    private void createUsersWithSavedData(){
        List<User> users = Repositories.getInstance().getUserRepository().getUsers();
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_OWNER, AuthenticationController.ROLE_OWNER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_MANAGER, AuthenticationController.ROLE_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);
        for (User user : users){
            if (user instanceof Client){
                authenticationRepository.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), AuthenticationController.ROLE_OWNER);
            } else if (user instanceof Employee){
                switch (((Employee) user).getRole()){
                    case "Employee":
                        authenticationRepository.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), AuthenticationController.ROLE_EMPLOYEE);
                        break;
                    case "Network Manager":
                        authenticationRepository.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), AuthenticationController.ROLE_MANAGER);
                        break;
                    case "Main Administrator":
                        authenticationRepository.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), AuthenticationController.ROLE_ADMIN);
                        break;
                    case "Agent":
                        authenticationRepository.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), AuthenticationController.ROLE_AGENT);
                        break;
                    case "Store Manager":
                        authenticationRepository.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), AuthenticationController.ROLE_STORE_MANAGER);
                        break;
                }
            }
        }
    }
    private void clearPasswords() throws IOException {
        String filename = "Passwords.txt";
        FileWriter writer = new FileWriter(filename, false);
        writer.write("");
        writer.close();

    }

//    private void addOrganization() {
//        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
//        Organization organization = new Organization("This Company");
//        organization.addEmployee(new Employee("admin@this.app"));
//        organization.addEmployee(new Employee("employee@this.app"));
//        organization.addEmployee(new Employee("agent@this.app"));
//        organization.addEmployee(new Employee("owner@this.app"));
//        organizationRepository.add(organization);
//    }
//
//    private void addTaskCategories() {
//        //TODO: add bootstrap Task Categories here
//
//        //get task category repository
//        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
//        taskCategoryRepository.add(new TaskCategory("Analysis"));
//        taskCategoryRepository.add(new TaskCategory("Design"));
//        taskCategoryRepository.add(new TaskCategory("Implementation"));
//        taskCategoryRepository.add(new TaskCategory("Development"));
//        taskCategoryRepository.add(new TaskCategory("Testing"));
//        taskCategoryRepository.add(new TaskCategory("Deployment"));
//        taskCategoryRepository.add(new TaskCategory("Maintenance"));
//    }

    private void addStore() {
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        storeRepository.add(new Store("Store", "store street", "987-654-3210", 123, "store@this.store"));
    }
    private void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_OWNER, AuthenticationController.ROLE_OWNER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_MANAGER, AuthenticationController.ROLE_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);
        UserRepository userRepository = Repositories.getInstance().getUserRepository();

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);
        userRepository.add(new Employee("Main Administrator", "admin@this.app", "admin", "Main Administrator"));


        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "employee",
                AuthenticationController.ROLE_EMPLOYEE);
                EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
                Employee employee = new Employee(1234, "employee@this.app", "Employee", "Employee", "956-2154-743", "Store", 1412, "Address 1", "employee");
                employeeRepository.add(employee);
                StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
                storeRepository.getStoreByName(employee.getStore()).addEmployee(employee);
                userRepository.add(employee);

        authenticationRepository.addUserWithRole("Agent", "agent@this.app", "agent",
                AuthenticationController.ROLE_AGENT);
                AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
                Agent agent = new Agent(4321, "agent@this.app", "Agent", "934-7361-124", "Store", 1351, "Address 2", "agent");
                employeeRepository.add(agent);
                agentRepository.add(agent);
                storeRepository.getStoreByName(agent.getStore()).addEmployee(agent);
                userRepository.add(agent);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "client",
                AuthenticationController.ROLE_OWNER);
                ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
                Client client = new Client("client", "client@this.app", "client");
                clientRepository.add(client);
                userRepository.add(client);

        authenticationRepository.addUserWithRole("Network Manager", "networkmanager@this.app", "networkmanager",
                AuthenticationController.ROLE_MANAGER);
                userRepository.add(new Employee("Network Manager", "networkmanager@this.app", "networkmanager", "Network Manager"));

        authenticationRepository.addUserWithRole("Store Manager", "storemanager@this.app", "storemanager",
                AuthenticationController.ROLE_STORE_MANAGER);
                userRepository.add(new Employee("Store Manager", "storemanager@this.app", "storemanager", "Store Manager", "Store"));
                StoreManager storeManager = new StoreManager(5678, "storemanager@this.app", "Store Manager", "974-1345-162", "Store", 6472, "Address 5", "storemanager");
                employeeRepository.add(storeManager);
                storeRepository.getStoreByName(storeManager.getStore()).addEmployee(storeManager);
                userRepository.add(storeManager);
    }


}
