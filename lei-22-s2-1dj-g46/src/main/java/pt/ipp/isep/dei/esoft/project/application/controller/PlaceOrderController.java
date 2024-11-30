package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;

/**
 * The PlaceOrderController class represents a controller for placing an order.
 */
public class PlaceOrderController {
    private OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
    Scanner scanner = new Scanner(System.in);
    private float correctAmount;

    /**
     * Asks the user about the type of sale (for sale or for rent) and filters the list of properties accordingly.
     *
     * @param properties the list of properties
     * @param sc         the scanner object for input
     * @return the filtered list of properties
     */
    public static List<Property> askAboutTypeOfSale(List<Property> properties, Scanner sc) {
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do {
            option = verifyInput.verifyInt(sc, "Type of Request");
            if (option == 1 || option == 2 || option == 3) {
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        } while (!valid);
        if (option == 1 || option == 2) {
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            switch (option) {
                case 1:
                    properties = editAndSortLists.getPropertiesWithXTypeOfSale(properties, "for sale");
                    break;
                case 2:
                    properties = editAndSortLists.getPropertiesWithXTypeOfSale(properties, "for rent");
                    break;
            }
        }
        return properties;
    }

    /**
     * Asks the user about the type of property (land, apartment, or house) and filters the list of properties accordingly.
     *
     * @param properties the list of properties
     * @param sc         the scanner object for input
     * @return the filtered list of properties
     */
    public static List<Property> askAboutTypeOfProperty(List<Property> properties, Scanner sc) {
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do {
            option = verifyInput.verifyInt(sc, "Type of Property");
            if (option == 1 || option == 2 || option == 3 || option == 4) {
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        } while (!valid);
        if (option == 1 || option == 2 || option == 3) {
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            switch (option) {
                case 1:
                    properties = editAndSortLists.getPropertiesIfTheyAreLand(properties);
                    break;
                case 2:
                    properties = editAndSortLists.getPropertiesIfTheyAreApartment(properties);
                    break;
                case 3:
                    properties = editAndSortLists.getPropertiesIfTheyAreHouse(properties);
                    break;
            }
        }
        return properties;
    }

    /**
     * Chooses a property from the list of properties based on the provided option.
     *
     * @param properties the list of properties
     * @param option     the chosen option
     */
    public void chooseProperty(List<Property> properties, int option) {
        if (option >= 1 && option <= properties.size()) {
            int i = option - 1;
            Property chosenProperty = Repositories.getInstance().getPropertyRepository().getProperties().get(i);
            System.out.println("Chosen Property: " + chosenProperty);
        }
    }

    /**
     * Requests the amount for the given property.
     *
     * @param property the selected property
     */
    public void requestAmount(Property property) {
        float amount = property.getFinalPrice();
        correctAmount = amount;
        System.out.println("Please send the correct amount");
        System.out.println(amount);
    }

    /**
     * Verifies if the provided amount is valid.
     *
     * @param amount the provided amount
     */
    public void verifyAmount(float amount) {
        boolean valid = false;
        while (!valid) {
            if (amount <= correctAmount && amount >= 0) {
                valid = true;
            } else {
                System.out.println("Incorrect amount\nPlease insert the amount again.");
                amount = scanner.nextFloat();
            }
        }

    }

    /**
     * Verifies if the order is valid based on the provided amount, email, and property.
     *
     * @param amount   the provided amount
     * @param email    the client email
     * @param property the selected property
     * @return true if the order is valid, false otherwise
     */
    public boolean verifyOrder(float amount, String email, Property property) {
        List<Order> orderList = orderRepository.getOrders();
        boolean valid = true;
        boolean already = true;
        Order orderToCreate = new Order(email, property, amount, "Pending");
        for (int i = 0; i < orderList.size() && valid; i++) {
            if (orderToCreate.getProperty() == orderList.get(i).getProperty()) {
                if (orderToCreate.getClientEmail().equals(orderList.get(i).getClientEmail())) {
                    System.out.println("Order already placed for this property");
                    valid = false;
                    already = false;
                }
                if (orderToCreate.getAmount() == orderList.get(i).getAmount() && already) {
                    System.out.println("An order with the same amount has already been registered by another client for this property");
                    valid = false;
                }
            }
        }
        return valid;
    }

    /**
     * Creates a new order with the provided amount, email, and property, and registers it in the order repository.
     *
     * @param amount   the provided amount
     * @param email    the client email
     * @param property the selected property
     */
    public void createAndRegisterNewOrder(float amount, String email, Property property) {
        Order order = new Order(email, property, amount, "Pending");
        orderRepository.add(order);
    }
}
