package pt.ipp.isep.dei.esoft.project.repository;

import java.io.Serializable;

/**
 * The type Repositories.
 */
public class Repositories implements Serializable {

    private static final Repositories instance = new Repositories();
    /**
     * The Task category repository.
     */
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    /**
     * The Organization repository.
     */
    OrganizationRepository organizationRepository = new OrganizationRepository();
    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    /**
     * The Property sold repository.
     */
    PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
    /**
     * The Property announcement repository.
     */
    PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
    /**
     * The Request repository.
     */
    PropertyRequestRepository requestRepository = new PropertyRequestRepository();
    /**
     * The Agent repository.
     */
    AgentRepository agentRepository = new AgentRepository();
    /**
     * The Employee repository.
     */
    EmployeeRepository employeeRepository = new EmployeeRepository();
    /**
     * The Store repository.
     */
    StoreRepository storeRepository = new StoreRepository();
    /**
     * The Message repository.
     */
    MessageRepository messageRepository = new MessageRepository();
    /**
     * The Response repository.
     */
    ResponseRepository responseRepository = new ResponseRepository();
    /**
     * The Client repository.
     */
    ClientRepository clientRepository = new ClientRepository();
    /**
     * The Order repository.
     */
    OrderRepository orderRepository = new OrderRepository();
    /**
     * The User repository.
     */
    UserRepository userRepository = new UserRepository();
    /**
     * The Announcement notification repository.
     */
    AnnouncementNotificationRepository announcementNotificationRepository = new AnnouncementNotificationRepository();

    private Repositories() {
    }

    /**
     * Instantiates a new Repositories.
     *
     * @param taskCategoryRepository             the task category repository
     * @param organizationRepository             the organization repository
     * @param authenticationRepository           the authentication repository
     * @param propertySoldRepository             the property sold repository
     * @param propertyAnnouncementRepository     the property announcement repository
     * @param requestRepository                  the request repository
     * @param agentRepository                    the agent repository
     * @param employeeRepository                 the employee repository
     * @param storeRepository                    the store repository
     * @param messageRepository                  the message repository
     * @param responseRepository                 the response repository
     * @param clientRepository                   the client repository
     * @param orderRepository                    the order repository
     * @param userRepository                     the user repository
     * @param announcementNotificationRepository the announcement notification repository
     */
    public Repositories(TaskCategoryRepository taskCategoryRepository, OrganizationRepository organizationRepository,
                         AuthenticationRepository authenticationRepository, PropertySoldRepository propertySoldRepository,
                         PropertyAnnouncementRepository propertyAnnouncementRepository, PropertyRequestRepository requestRepository,
                         AgentRepository agentRepository, EmployeeRepository employeeRepository, StoreRepository storeRepository,
                         MessageRepository messageRepository, ResponseRepository responseRepository, ClientRepository clientRepository,
                         OrderRepository orderRepository, UserRepository userRepository, AnnouncementNotificationRepository announcementNotificationRepository){
        this.taskCategoryRepository = taskCategoryRepository;
        this.organizationRepository = organizationRepository;
        this.authenticationRepository = authenticationRepository;
        this.propertySoldRepository = propertySoldRepository;
        this.propertyAnnouncementRepository = propertyAnnouncementRepository;
        this.requestRepository = requestRepository;
        this.agentRepository = agentRepository;
        this.employeeRepository = employeeRepository;
        this.storeRepository = storeRepository;
        this.messageRepository = messageRepository;
        this.responseRepository = responseRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.announcementNotificationRepository = announcementNotificationRepository;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Repositories getInstance() {return instance;}

    /**
     * Get copy repositories.
     *
     * @return the repositories
     */
    public Repositories getCopy(){return instance.clone();}

    /**
     * Set instance.
     *
     * @param repositoriesData the repositories data
     */
    public void setInstance(Repositories repositoriesData){
        instance.setTaskCategoryRepository(repositoriesData.getTaskCategoryRepository());
        instance.setOrganizationRepository(repositoriesData.getOrganizationRepository());
        instance.setAuthenticationRepository(repositoriesData.getAuthenticationRepository());
        instance.setPropertySoldRepository(repositoriesData.getPropertySoldRepository());
        instance.setPropertyAnnouncementRepository(repositoriesData.getPropertyRepository());
        instance.setRequestRepository(repositoriesData.getPropertyRequestRepository());
        instance.setAgentRepository(repositoriesData.getAgentRepository());
        instance.setEmployeeRepository(repositoriesData.getEmployeeRepository());
        instance.setStoreRepository(repositoriesData.getStoreRepository());
        instance.setMessageRepository(repositoriesData.getMessageRepository());
        instance.setResponseRepository(repositoriesData.getResponseRepository());
        instance.setClientRepository(repositoriesData.getClientRepository());
        instance.setOrderRepository(repositoriesData.getOrderRepository());
        instance.setUserRepository(repositoriesData.getUserRepository());
    }

    /**
     * Gets task category repository.
     *
     * @return the task category repository
     */
    public TaskCategoryRepository getTaskCategoryRepository() {return taskCategoryRepository;}

    /**
     * Set task category repository.
     *
     * @param taskCategoryRepository the task category repository
     */
    public void setTaskCategoryRepository(TaskCategoryRepository taskCategoryRepository){this.taskCategoryRepository = taskCategoryRepository;}

    /**
     * Gets organization repository.
     *
     * @return the organization repository
     */
    public OrganizationRepository getOrganizationRepository() {return organizationRepository;}

    /**
     * Set organization repository.
     *
     * @param organizationRepository the organization repository
     */
    public void setOrganizationRepository(OrganizationRepository organizationRepository){this.organizationRepository = organizationRepository;}

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */
    public AuthenticationRepository getAuthenticationRepository() {return authenticationRepository;}

    /**
     * Set authentication repository.
     *
     * @param authenticationRepository the authentication repository
     */
    public void setAuthenticationRepository(AuthenticationRepository authenticationRepository){this.authenticationRepository = authenticationRepository;}

    /**
     * Gets agent repository.
     *
     * @return the agent repository
     */
    public AgentRepository getAgentRepository() {return agentRepository;}

    /**
     * Set agent repository.
     *
     * @param agentRepository the agent repository
     */
    public void setAgentRepository(AgentRepository agentRepository){this.agentRepository = agentRepository;}

    /**
     * Gets property sold repository.
     *
     * @return the property sold repository
     */
    public PropertySoldRepository getPropertySoldRepository() {return propertySoldRepository;}

    /**
     * Set property sold repository.
     *
     * @param propertySoldRepository the property sold repository
     */
    public void setPropertySoldRepository(PropertySoldRepository propertySoldRepository){this.propertySoldRepository = propertySoldRepository;}

    /**
     * Gets property repository.
     *
     * @return the property repository
     */
    public PropertyAnnouncementRepository getPropertyRepository() {return propertyAnnouncementRepository;}

    /**
     * Set property announcement repository.
     *
     * @param propertyAnnouncementRepository the property announcement repository
     */
    public void setPropertyAnnouncementRepository(PropertyAnnouncementRepository propertyAnnouncementRepository){this.propertyAnnouncementRepository = propertyAnnouncementRepository;}

    /**
     * Gets property request repository.
     *
     * @return the property request repository
     */
    public PropertyRequestRepository getPropertyRequestRepository() {return requestRepository;}

    /**
     * Set request repository.
     *
     * @param requestRepository the request repository
     */
    public void setRequestRepository(PropertyRequestRepository requestRepository){this.requestRepository = requestRepository;}

    /**
     * Gets employee repository.
     *
     * @return the employee repository
     */
    public EmployeeRepository getEmployeeRepository() {return employeeRepository;}

    /**
     * Set employee repository.
     *
     * @param employeeRepository the employee repository
     */
    public void setEmployeeRepository(EmployeeRepository employeeRepository){this.employeeRepository = employeeRepository;}

    /**
     * Gets store repository.
     *
     * @return the store repository
     */
    public StoreRepository getStoreRepository() {return storeRepository;}

    /**
     * Set store repository.
     *
     * @param storeRepository the store repository
     */
    public void setStoreRepository(StoreRepository storeRepository){this.storeRepository = storeRepository;}

    /**
     * Get message repository message repository.
     *
     * @return the message repository
     */
    public MessageRepository getMessageRepository(){return messageRepository;}

    /**
     * Set message repository.
     *
     * @param messageRepository the message repository
     */
    public void setMessageRepository(MessageRepository messageRepository){this.messageRepository = messageRepository;}

    /**
     * Get response repository response repository.
     *
     * @return the response repository
     */
    public ResponseRepository getResponseRepository(){return responseRepository;}

    /**
     * Set response repository.
     *
     * @param responseRepository the response repository
     */
    public void setResponseRepository(ResponseRepository responseRepository){this.responseRepository = responseRepository;}

    /**
     * Get client repository client repository.
     *
     * @return the client repository
     */
    public ClientRepository getClientRepository(){return clientRepository;}

    /**
     * Set client repository.
     *
     * @param clientRepository the client repository
     */
    public void setClientRepository(ClientRepository clientRepository){this.clientRepository = clientRepository;}

    /**
     * Get order repository order repository.
     *
     * @return the order repository
     */
    public OrderRepository getOrderRepository(){return orderRepository;}

    /**
     * Set order repository.
     *
     * @param orderRepository the order repository
     */
    public void setOrderRepository(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    /**
     * Get user repository user repository.
     *
     * @return the user repository
     */
    public UserRepository getUserRepository(){return userRepository;}

    /**
     * Set user repository.
     *
     * @param userRepository the user repository
     */
    public void setUserRepository(UserRepository userRepository){this.userRepository = userRepository;}

    /**
     * Get announcement notification repository announcement notification repository.
     *
     * @return the announcement notification repository
     */
    public AnnouncementNotificationRepository getAnnouncementNotificationRepository(){return announcementNotificationRepository;}

    /**
     * Set announcement notification repository.
     *
     * @param announcementNotificationRepository the announcement notification repository
     */
    public void setAnnouncementNotificationRepository(AnnouncementNotificationRepository announcementNotificationRepository){this.announcementNotificationRepository = announcementNotificationRepository;}
    public Repositories clone(){return new Repositories(this.taskCategoryRepository, this.organizationRepository, this.authenticationRepository,
            this.propertySoldRepository, this.propertyAnnouncementRepository, this.requestRepository, this.agentRepository,
            this.employeeRepository, this.storeRepository, this.messageRepository, this.responseRepository, this.clientRepository, this.orderRepository, this.userRepository, this.announcementNotificationRepository);}
}
