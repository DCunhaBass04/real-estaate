package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CheckResponsesToOrdersController;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Scanner;

/**
 * The CheckResponsesToOrdersUI class represents the user interface for checking responses to orders.
 * It interacts with the CheckResponsesToOrdersController to retrieve and display order responses.
 */
public class CheckResponsesToOrdersUI implements Runnable {
    /**
     * Constructs a new CheckResponsesToOrdersUI object.
     */
    public CheckResponsesToOrdersUI() {
    }

    private CheckResponsesToOrdersController ctrl = new CheckResponsesToOrdersController();

    /**
     * Runs the check responses to orders user interface.
     * Retrieves the current user's email and lists the orders associated with the user.
     * Displays the state of each order and prompts the user to continue.
     */
    @Override
    public void run() {
        Scanner ler = new Scanner(System.in);
        String currentUserEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<Order> orderList = ctrl.getOrdersByXClient(currentUserEmail);
        if (!orderList.isEmpty()) {
            for (Order order : orderList) {
                viewAllActiveOrders(order);
            }
        } else {
            System.out.println("There are no active orders.");
        }
        System.out.println("Press ENTER to continue.");
        ler.nextLine();
    }

    /**
     * Displays the state of an order for a specific property.
     * Prints the property and its corresponding order state.
     *
     * @param order The order for which to display the state.
     */
    public void viewAllActiveOrders(Order order) {
        String state = order.getState();
        System.out.printf("Your order for the following property:%n%s%n", order.getProperty());
        if (state.equals("Pending")) {
            System.out.printf("is still %s%n", state);
        } else {
            System.out.printf("was %s%n", state);
        }
    }
}
