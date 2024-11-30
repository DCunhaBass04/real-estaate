package pt.ipp.isep.dei.esoft.project.domain.PropertyTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property.Land;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
public class LandTest {
    @Test void ensureTwoLandsWithSameDataEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        assertEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentAreaNotEquals() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(888.88f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentLocationNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "there", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentDistanceNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "here", 66.66f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentAgentNotEquals(){
        Agent agent1 = new Agent("john.doe@this.company.com");
        Agent agent2 = new Agent("jane.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent1, photograph, 22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "here", 55.55f, agent2, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentPhotographNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph1, 22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "here", 55.55f, agent, photograph2, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentFinalPriceNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph1, 22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "here", 55.55f, agent, photograph2, 33333, "17/05/2023", "n/a", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentAnnouncementDateNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph1, 22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "here", 55.55f, agent, photograph2, 22222, "17/04/2023", "n/a", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentSaleDateNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph1, 22222, "17/05/2023", "19/07/2023", request);
        Land land2 = new Land(999.99f, "here", 55.55f, agent, photograph2, 22222, "17/05/2023", "19/06/2023", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentAmountOfPhotographsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph1 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg")};
        File[] photograph2 = {new File("C:\\Users\\35193\\Desktop\\test1.jpg"), new File("C:\\Users\\35193\\Desktop\\test2.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph1, 22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "here", 55.55f, agent, photograph2, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandsWithDifferentRequestsNotEquals(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request1 = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Request request2 = new Request("for sale", 33333, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request1);
        Land land2 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request2);
        assertNotEquals(land1, land2);
    }
    @Test void ensureLandDoesNotEqualNull() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(land1, null);
    }

    @Test void ensureLandDoesNotEqualOtherObject() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        assertNotEquals(land1, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        assertEquals(land1, land1);
    }
    @Test void ensureLandIsProperty(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        assertInstanceOf(Property.class, land1);
    }
    @Test void ensureCloneWorks(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        Land clone = land1.clone();
        assertEquals(land1, clone);
    }
}
