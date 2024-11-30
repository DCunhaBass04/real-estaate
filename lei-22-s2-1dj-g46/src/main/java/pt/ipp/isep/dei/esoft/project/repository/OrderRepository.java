package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The OrderRepository class is responsible for managing and accessing order data.
 * It provides methods to add, remove, and retrieve order objects.
 */
public class OrderRepository implements Serializable {
    private List<Order> orders;

    /**
     * Constructs a new OrderRepository object.
     */
    public OrderRepository() {
        orders = new ArrayList<>();
    }

    /**
     * Adds a new order to the repository.
     *
     * @param order The order object to be added.
     * @return An optional containing the newly added order if the operation is successful, otherwise an empty optional.
     */

    public Optional<Order> add(Order order) {

        Optional<Order> newOrder = Optional.empty();
        boolean operationSuccess = false;

        if (validateOrder(order)) {
            newOrder = Optional.of(order.clone());
            operationSuccess = orders.add(newOrder.get());
        }

        if (!operationSuccess) {
            newOrder = Optional.empty();
        }

        return newOrder;
    }

    /**
     * Removes an order from the repository.
     *
     * @param o The order object to be removed.
     */
    public void remove(Object o) {
        orders.remove(o);
    }

    /**
     * Validates if an order is unique (not already present in the repository).
     *
     * @param order The order object to be validated.
     * @return True if the order is unique, false otherwise.
     */

    private boolean validateOrder(Order order) {
        return !orders.contains(order);
    }

    /**
     * Retrieves the list of orders in the repository.
     *
     * @return The list of orders in the repository.
     */

    public List<Order> getOrders() {
        return orders;
    }
}
