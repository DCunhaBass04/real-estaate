package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTest {
    @Test
    void ensureTwoStoresWithSameDataEquals(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        Store store2 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        assertEquals(store1, store2);
    }
    @Test void ensureStoresWithDifferentNameNotEquals() {
        Store store1 = new Store("RealEstateStore1", "here", "98765", 123, "store@this.company.com");
        Store store2 = new Store("RealEstateStore2", "here", "98765", 123, "store@this.company.com");
        assertNotEquals(store1, store2);
    }
    @Test void ensureStoresWithDifferentAddressesNotEquals(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        Store store2 = new Store("RealEstateStore", "there", "98765", 123, "store@this.company.com");
        assertNotEquals(store1, store2);
    }
    @Test void ensureStoresWithDifferentPhoneNumbersNotEquals(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        Store store2 = new Store("RealEstateStore", "here", "98567", 123, "store@this.company.com");
        assertNotEquals(store1, store2);
    }

    @Test void ensureStoresWithDifferentIDsNotEquals(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        Store store2 = new Store("RealEstateStore", "here", "98765", 321, "store@this.company.com");
        assertNotEquals(store1, store2);
    }

    @Test void ensureStoresWithDifferentEmailsNotEquals(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store1@this.company.com");
        Store store2 = new Store("RealEstateStore", "here", "98765", 123, "store2@this.company.com");
        assertNotEquals(store1, store2);
    }
    @Test void ensureStoreDoesNotEqualNull() {
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        assertNotEquals(store1, null);
    }

    @Test void ensureStoreDoesNotEqualOtherObject() {
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        assertNotEquals(store1, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        assertEquals(store1, store1);
    }
    @Test void ensureCloneWorks(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        Store clone = store1.clone();
        assertEquals(store1, clone);
    }
}