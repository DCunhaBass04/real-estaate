package pt.ipp.isep.dei.esoft.project.domain.PropertyTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property.Apartment;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ApartmentTest {
    @Test
    void ensureTwoApartmentsWithSameDataEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentAreaNotEquals() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(888.88f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentLocationNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "there", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentDistanceNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 66.66f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentAgentNotEquals(){
        Agent agent1 = new Agent("john.doe@this.company.com");
        Agent agent2 = new Agent("jane.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent1,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent2,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentNumOfBedroomsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  3, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentNumOfBathroomsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 2, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentNumOfParkingSpacesNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 3, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentCentralHeatingNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, false, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentAirConditioningNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, false, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentFinalPriceNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 33333, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentAnnouncementDateNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "19/07/2023", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "19/08/2023", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentSaleDateNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/04/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentPhotographNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph1, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph2, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentAmountOfPhotographsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg"), new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph1, 22222, "17/05/2023", "n/a", request);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph2, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentsWithDifferentRequestsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request1 = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Request request2 = new Request("for sale", 33333, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request1);
        Apartment apartment2 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request2);
        assertNotEquals(apartment1, apartment2);
    }
    @Test void ensureApartmentDoesNotEqualNull() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, null);
    }

    @Test void ensureApartmentDoesNotEqualOtherObject() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(apartment1, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertEquals(apartment1, apartment1);
    }
    @Test void ensureApartmentIsProperty(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        assertInstanceOf(Property.class, apartment1);
    }
    @Test void ensureCloneWorks() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        Apartment clone = apartment1.clone();
        assertEquals(apartment1, clone);
    }
}
