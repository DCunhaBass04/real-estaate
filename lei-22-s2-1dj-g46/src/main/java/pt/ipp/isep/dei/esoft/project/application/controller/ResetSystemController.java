package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Users.StoreManager;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class ResetSystemController {
    /**
     * Erases everything except for the admin and the network manager's accounts.
     */
    public void resetSystem() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        OrganizationRepository organizationRepository = new OrganizationRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
        PropertyRequestRepository requestRepository = new PropertyRequestRepository();
        AgentRepository agentRepository = new AgentRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        StoreRepository storeRepository = new StoreRepository();
        MessageRepository messageRepository = new MessageRepository();
        ResponseRepository responseRepository = new ResponseRepository();
        ClientRepository clientRepository = new ClientRepository();
        OrderRepository orderRepository = new OrderRepository();
        UserRepository userRepository = new UserRepository();
        AnnouncementNotificationRepository announcementNotificationRepository = new AnnouncementNotificationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_MANAGER, AuthenticationController.ROLE_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin", AuthenticationController.ROLE_ADMIN);
        userRepository.add(new Employee("Main Administrator", "admin@this.app", "admin", "Main Administrator"));

        authenticationRepository.addUserWithRole("Network Manager", "networkmanager@this.app", "networkmanager", AuthenticationController.ROLE_MANAGER);
        userRepository.add(new Employee("Network Manager", "networkmanager@this.app", "networkmanager", "Network Manager"));

        authenticationRepository.addUserWithRole("Store Manager", "storemanager@this.app", "storemanager", AuthenticationController.ROLE_STORE_MANAGER);
        userRepository.add(new Employee("Store Manager", "storemanager@this.app", "storemanager", "Store Manager", "Store"));
        StoreManager storeManager = new StoreManager(5678, "storemanager@this.app", "Store Manager", "974-1345-162", "Store", 6472, "Address 5", "storemanager");
        employeeRepository.add(storeManager);

        Repositories newInstance = new Repositories(taskCategoryRepository, organizationRepository, authenticationRepository, propertySoldRepository, propertyAnnouncementRepository,
                requestRepository, agentRepository, employeeRepository, storeRepository, messageRepository, responseRepository, clientRepository, orderRepository, userRepository,
                announcementNotificationRepository);
        newInstance.setInstance(newInstance);
    }
}
