package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PlaceOrderController;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;

/**
 * The PlaceOrderUI class represents the user interface for placing an order for a property.
 * It interacts with the PlaceOrderController to validate and process the order.
 */
public class PlaceOrderUI implements Runnable {
    private final PlaceOrderController controller = new PlaceOrderController();

    /**
     * Runs the place order user interface.
     * Retrieves the list of properties and applies filters based on user input.
     * Displays the filtered property list and prompts the user to choose a property.
     * Validates the order amount and creates a new order if the amount is valid.
     * Notifies the user about the status of the order.
     */
    public void run() {
        Scanner ler = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        List<Property> propertyList = Repositories.getInstance().getPropertyRepository().getProperties();
        System.out.println();
        String email = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        EditAndSortLists editAndSortLists = new EditAndSortLists();
        propertyList = editAndSortLists.getPropertiesIfNotFromTheSameOwner(propertyList, email);
        if (!propertyList.isEmpty()) {
            propertyList = askAboutFilters(propertyList, ler);
            if (!propertyList.isEmpty()) {
                printPropertyList(propertyList);
                int option = verifyOption(propertyList);
                if (option != 0) {
                    controller.chooseProperty(propertyList, option);
                    controller.requestAmount(propertyList.get(option - 1));
                    float amount = verifyInput.verifyFloat(ler, "order amount");
                    controller.verifyAmount(amount);
                    boolean valid = controller.verifyOrder(amount, email, propertyList.get(option - 1));
                    if (valid) {
                        controller.createAndRegisterNewOrder(amount, email, propertyList.get(option - 1));
                        System.out.println("Order placed!");
                    }
                }
            } else {
                System.out.println("No properties to be listed");
            }
        } else {
            System.out.println("No properties to be listed");
        }
    }

    /**
     * Asks the user about filters to apply on the property list.
     * Filters the list of properties based on the user's input.
     *
     * @param properties The list of properties to filter.
     * @param sc         The Scanner object used for user input.
     * @return The filtered list of properties.
     */
    public List<Property> askAboutFilters(List<Property> properties, Scanner sc) {
        System.out.printf("Do you want to only see properties...%n1 - For Sale%n2 - For Rent%n3 - Both%n");
        properties = controller.askAboutTypeOfSale(properties, sc);
        System.out.printf("Do you want to only see...%n1 - Lands%n2 - Apartments%n3 - House%n4 - All of them");
        properties = controller.askAboutTypeOfProperty(properties, sc);
        return properties;
    }

    /**
     * Prints the list of properties.
     *
     * @param propertyList The list of properties to print.
     */
    public void printPropertyList(List<Property> propertyList) {
        if (!propertyList.isEmpty()) {
            System.out.println();
            for (int i = 0; i < propertyList.size(); i++) {
                Property property = propertyList.get(i);
                System.out.println((i + 1) + ". " + property);
            }
        } else {
            System.out.println("There are no properties with the requested characteristics");
        }
    }

    /**
     * Verifies and validates the user's option for choosing a property.
     *
     * @param properties The list of properties to choose from.
     * @return The validated option chosen by the user.
     */
    public int verifyOption(List<Property> properties) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int option = 0;
        while (!valid) {
            System.out.println("Choose the number of the property that you want to purchase\nOr 0 to cancel");
            option = scanner.nextInt();
            if (option == 0) {
                System.out.println("Canceled.");
                valid = true;
            } else if (option <= properties.size() && option > 0) {
                valid = true;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        return option;
    }
}



