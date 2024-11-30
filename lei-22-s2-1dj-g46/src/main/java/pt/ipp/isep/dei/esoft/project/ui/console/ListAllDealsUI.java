package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListAllDealsController;

import java.util.List;
import java.util.Scanner;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

/**
 * The type List all deals ui.
 */
public class ListAllDealsUI implements Runnable {
    private final ListAllDealsController controller = new ListAllDealsController();
    private final Scanner sc = new Scanner(System.in);
    private final VerifyInput verifyInput = new VerifyInput();
    public void run() {
        List<Property> properties = controller.getProperties();
        if (!properties.isEmpty()) {
            int option = getOption();
            int option2 = choosingAlgorithm();
            properties = controller.sortProperties(option, option2, properties);
            printPropertyList(properties);
        } else {
            System.out.println("There are no sold properties yet");
        }
        System.out.println("Press ENTER to continue");
        sc.nextLine();
    }

    /**
     * Gets option.
     *
     * @return the option
     */
    public int getOption() {
        System.out.println("Select and option with 1 or 2:");
        System.out.println("1 - Sort all Properties by area in ascending order");
        System.out.println("2 - Sort all Properties by area in descending order");
        boolean validate = true;
        int option = 0;
        while (validate) {
            option = verifyInput.verifyInt(sc, "option");
            if (option == 1 || option == 2) {
                validate = false;
            } else {
                System.out.println("Select an option with 1 or 2:");
            }
        }
        return option;
    }

    /**
     * Choosing algorithm int.
     *
     * @return the int
     */
    public int choosingAlgorithm() {
        System.out.println("Select and option with 1 or 2:");
        System.out.println("1 - Bubble Sort");
        System.out.println("2 - Merge Sort");
        boolean validate = true;
        int option = 0;
        while (validate) {
            option = verifyInput.verifyInt(sc, "option");
            if (option == 1 || option == 2) {
                validate = false;
            } else {
                System.out.println("Select and option with 1 or 2:");
            }
        }
        return option;
    }

    /**
     * Print property list.
     *
     * @param propertyList the property list
     */
    public void printPropertyList(List<Property> propertyList) {
        System.out.println();
        for (int i = 0; i < propertyList.size(); i++) {
            Property property = propertyList.get(i);
            System.out.println((i + 1) + ". " + property);
        }
    }
}

