package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreRepositoryTest {
    @Test
    void ensureStoreRepositoryIsCreated(){
        StoreRepository storeRepository = new StoreRepository();
        assertNotEquals(storeRepository, null);
    }
    @Test void storeSuccessfullyAdded(){
        StoreRepository storeRepository = new StoreRepository();
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        storeRepository.add(store1);
        assertTrue(storeRepository.getStores().contains(store1));
    }
}