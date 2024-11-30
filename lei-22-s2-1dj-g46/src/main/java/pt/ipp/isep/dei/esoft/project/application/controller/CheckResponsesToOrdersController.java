package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * The CheckResponsesToOrdersController class represents a controller for checking responses to orders.
 */
public class CheckResponsesToOrdersController {
    /**
     * Retrieves the list of orders associated with a specific client's email.
     *
     * @param email the email of the client
     * @return the list of orders associated with the client
     */
    public List<Order> getOrdersByXClient(String email) {
        List<Order> allOffers = Repositories.getInstance().getOrderRepository().getOrders();
        List<Order> offersByXClient = new ArrayList<>();
        for (Order order : allOffers) {
            if (order.getClientEmail().equals(email)) {
                offersByXClient.add(order);
            }
        }
        return offersByXClient;
    }
}
