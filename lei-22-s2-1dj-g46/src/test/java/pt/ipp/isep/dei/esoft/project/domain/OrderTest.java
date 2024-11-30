package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Property.Land;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test void ensureTwoOrdersWithTheSameDataEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        Order order2 = new Order("client@this.app", land, 22221, "Pending");
        assertEquals(order1, order2);
    }
    @Test void ensureTwoOrdersFromDifferentClientNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client1@this.app", land, 22221, "Pending");
        Order order2 = new Order("client2@this.app", land, 22221, "Pending");
        assertNotEquals(order1, order2);
    }
    @Test void ensureTwoOrdersToDifferentPropertiesNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(888.88f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land1, 22221, "Pending");
        Order order2 = new Order("client@this.app", land2, 22221, "Pending");
        assertNotEquals(order1, order2);
    }
    @Test void ensureTwoOrdersWithDifferentAmountsNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        Order order2 = new Order("client@this.app", land, 22220, "Pending");
        assertNotEquals(order1, order2);
    }
    @Test void ensureTwoOrdersWithDifferentStatesNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        Order order2 = new Order("client@this.app", land, 22221, "Declined");
        assertNotEquals(order1, order2);
    }
    @Test void ensureAnOrderIsNotNull(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        assertNotEquals(order1, null);
    }
    @Test void ensureAnOrderIsNotOtherObject(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        assertNotEquals(order1, new Object());
    }
    @Test void ensureSetStateWorks(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        order1.setState("Declined");
        Order order2 = new Order("client@this.app", land, 22221, "Declined");
        assertEquals(order1, order2);
    }
    @Test void ensureCloneWorks(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order = new Order("client@this.app", land, 22221, "Pending");
        Order clone = order.clone();
        assertEquals(order, clone);
    }
    @Test void ensureCompareToWorks(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        Order order2 = new Order("client@this.app", land, 22220, "Pending");
        assertTrue(order1.compareTo(order2)>0);
    }
}
