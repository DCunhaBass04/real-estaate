package pt.ipp.isep.dei.esoft.project.domain.PropertyTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {
    @Test void ensureTwoRequestsWithSameDataEqual(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        Request request2 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        assertEquals(request1, request2);
    }
    @Test void ensureTwoRequestsWithDifferentTypesNotEqual(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        Request request2 = new Request("for rent", 444.44f, "€/month", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 2);
        assertNotEquals(request1, request2);
    }
    @Test void ensureTwoRequestsWithDifferentPricesNotEqual(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        Request request2 = new Request("for sale", 333.33f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        assertNotEquals(request1, request2);
    }
    @Test void ensureTwoRequestsWithDifferentPublishedStatesNotEqual(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        Request request2 = new Request("for sale", 444.44f, "€", "Published", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        assertNotEquals(request1, request2);
    }
    @Test void ensureTwoRequestsWithDifferentCommissionDatesNotEqual(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        Request request2 = new Request("for sale", 444.44f, "€", "Requested", "25/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        assertNotEquals(request1, request2);
    }
    @Test void ensureTwoRequestsWithDifferentOwnerEmailsNotEqual(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner1", "owner1@this.app", "password"), "3.2%", 1);
        Request request2 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner2", "owner2@this.app", "password"), "3.2%", 1);
        assertNotEquals(request1, request2);
    }
    @Test void ensureTwoRequestsWithDifferentCommissionValuesNotEqual(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        Request request2 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "4.3%", 1);
        assertNotEquals(request1, request2);
    }
    @Test void ensureRequestIsNotEqualToNull(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        assertNotEquals(request1, null);
    }
    @Test void ensureRequestIsNotEqualToOtherObject(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        assertNotEquals(request1, new Object());
    }
    @Test void ensureSameObjectIsEqual(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        assertEquals(request1, request1);
    }
    @Test void ensureCloneWorks(){
        Request request1 = new Request("for sale", 444.44f, "€", "Requested", "23/04/2023", new Client("owner", "owner@this.app", "password"), "3.2%", 1);
        Request clone = request1.clone();
        assertEquals(request1, clone);
    }
}
