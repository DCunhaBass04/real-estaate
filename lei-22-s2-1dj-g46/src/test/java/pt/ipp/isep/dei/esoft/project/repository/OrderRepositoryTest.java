package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.Property.Land;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest {
    @Test
    void ensureOrderRepositoryIsCreated(){
        OrderRepository orderRepository = new OrderRepository();
        assertNotEquals(orderRepository, null);
    }
    @Test void ensureOrderIsAdded(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order = new Order("client@this.app", land, 22221, "Pending");
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.add(order);
        assertTrue(orderRepository.getOrders().contains(order));
    }
    @Test void ensureOrderIsRemoved(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order = new Order("client@this.app", land, 22221, "Pending");
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.add(order);
        orderRepository.remove(order);
        assertFalse(orderRepository.getOrders().contains(order));
    }
}
