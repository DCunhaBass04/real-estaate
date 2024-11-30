package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListEveryEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.List;
import java.util.Scanner;

/**
 * The ListEveryEmployeeUI class represents the user interface for listing every employee.
 * It interacts with the ListEveryEmployeeController to retrieve and display employee information.
 */
public class ListEveryEmployeeUI implements Runnable {

    private ListEveryEmployeeController ctrl;

    /**
     * Constructs a ListEveryEmployeeUI object.
     * Initializes the controller for managing employee listing.
     */
    public ListEveryEmployeeUI() {
        this.ctrl = new ListEveryEmployeeController();
    }

    /**
     * Runs the list every employee user interface.
     * Retrieves the list of stores from the controller and displays the employees for each store.
     * If there are no stores registered, a message is displayed.
     */
    @Override
    public void run() {
        Scanner ler = new Scanner(System.in);
        List<Store> listOfStoresToPrint = ctrl.getListOfStores();
        if (listOfStoresToPrint.isEmpty()) {
            System.out.println("There are no stores, register some stores in the system and try again");
        } else {
            listOfStoresToPrint = ctrl.sortStoresByNumOfProperties(listOfStoresToPrint);
            printAllStoresAndItsEmployees(listOfStoresToPrint);
        }
        System.out.print("\n\nPress ENTER to continue");
        ler.nextLine();
    }

    private void printAllStoresAndItsEmployees(List<Store> storeList) {
        for (Store store : storeList) {
            System.out.println(store);
            System.out.printf("Number of properties associated with %s: %d%n", store.getStoreName(), store.getPropertyList().size());
            ctrl.printAllEmployeesByStore(store);
        }
    }
}