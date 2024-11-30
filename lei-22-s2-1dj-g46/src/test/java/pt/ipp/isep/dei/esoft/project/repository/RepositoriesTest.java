package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

//    @Test
//    void testGetOrganizationRepository() {
//        Repositories instance = Repositories.getInstance();
//        assertNotNull(instance.getOrganizationRepository());
//    }
//
//    @Test
//    void testGetTaskCategoryRepository() {
//        Repositories instance = Repositories.getInstance();
//        assertNotNull(instance.getTaskCategoryRepository());
//    }
    @Test
    void testGetAuthenticationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAuthenticationRepository());
    }
    @Test
    void testGetAgentRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAgentRepository());
    }
    @Test
    void testGetPropertyAnnouncementRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getPropertyRepository());
    }
    @Test
    void testGetPropertyRequestRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getPropertyRequestRepository());
    }
    @Test
    void testGetPropertySoldRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getPropertySoldRepository());
    }
    @Test
    void testGetOrderRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getOrderRepository());
    }
    @Test
    void testGetClientRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getClientRepository());
    }
    @Test
    void testGetEmployeeRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getEmployeeRepository());
    }
    @Test
    void testGetMessageRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getMessageRepository());
    }
    @Test
    void testGetStoreRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getStoreRepository());
    }
}