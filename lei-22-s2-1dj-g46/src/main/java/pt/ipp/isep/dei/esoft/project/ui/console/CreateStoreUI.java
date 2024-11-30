package pt.ipp.isep.dei.esoft.project.ui.console;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateStoreController;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.*;

/**
 * Register Store UI (console). This option is only available for administrators.
 */

public class CreateStoreUI implements Runnable{
    private Store store;

    private final CreateStoreController controller = new CreateStoreController();

    private CreateStoreController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\nRegister a Store.");
        registerNewStore();
    }

    /**
     *1. Calls the requestData() method of CreateStoreController class to retrieve store data.
     *2. Assigns the returned store data to a variable named store.
     *3. Calls the submitData() method to submit the store data.
     */
    public void registerNewStore() {
        store = controller.requestData();
        submitData(store);
    }

    /**
     *
     * @param store : The store object to submit
     *
     * Initializes a new instance of the Scanner class to read user input from the console.
     * Initializes a new instance of the VerifyInput class, which likely contains methods to validate user input.
     * Defines a boolean variable named newStore to store whether or not the store data is new or already exists in the system.
     * Prompts the user to input an option, and verifies that the input is an integer using the verifyInt() method.
     * If the user selects option 1, the method calls the verifyData() method of the CreateStoreController class to check if the store data is new or already exists in the system.
     * If newStore is true, meaning the store data is new, the method prints "Operation success!" to the console and calls the createStore() method of the CreateStoreController class to create the new store.
     * If newStore is false, meaning the store data already exists in the system, the method prints "This store is already registered." to the console.
     */
    public void submitData(Store store){
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        boolean newStore;
        String password;
        int option = verifyInput.verifyInt(input, "an option.%nSubmit data?%n1 - Yes.%n(Select any other NUMBER%nto go back to the Administrator menu.)");
        if (option == 1){
            newStore = controller.verifyData(store);
            if (newStore){
                System.out.println("Operation success!");
                controller.createStore(store);
            }
            else {
                System.out.println("This store is already registered.");
            }
        }
    }
}