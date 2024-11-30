package pt.ipp.isep.dei.esoft.project.domain.PropertyTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property.House;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseTest {
    @Test void ensureTwoHousesWithSameDataEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertEquals(house1, house2);
    }
    @Test
    void ensureHousesWithDifferentAreaNotEquals() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(888.88f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentLocationNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "there", 55.55f, agent,  2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentDistanceNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 66.66f, agent,  2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentAgentNotEquals(){
        Agent agent1 = new Agent("john.doe@this.company.com");
        Agent agent2 = new Agent("jane.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent1, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent2,  2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentNumOfBedroomsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 3, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentNumOfBathroomsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 2, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentNumOfParkingSpacesNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 6, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentCentralHeatingNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, false, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentAirConditioningNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, false, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentBasementStatusNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, false, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentInhabitableLoftNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, false, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentSunExposureDirectionNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "south", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentPhotographNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph1, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph2, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentAmountOfPhotographsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg"), new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph1, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph2, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentFinalPriceNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 33333, "17/05/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentAnnouncementDateNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/04/2023", "n/a", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentSaleDateNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "19/07/2023", request);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "19/08/2023", request);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHousesWithDifferentRequestsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request1 = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Request request2 = new Request("for sale", 33333, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request1);
        House house2 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request2);
        assertNotEquals(house1, house2);
    }
    @Test void ensureHouseDoesNotEqualNull() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, null);
    }

    @Test void ensureHouseDoesNotEqualOtherObject() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(house1, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertEquals(house1, house1);
    }
    @Test void ensureHouseIsProperty(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        assertInstanceOf(Property.class, house1);
    }
    @Test void ensureCloneWorks(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house1 = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        House clone = house1.clone();
        assertEquals(house1, clone);
    }
}