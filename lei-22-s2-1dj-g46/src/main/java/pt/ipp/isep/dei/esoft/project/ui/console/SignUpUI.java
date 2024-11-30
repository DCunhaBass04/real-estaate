package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SignUpController;

import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;


import java.util.Scanner;

/**
 * The SignUpUI class represents the user interface for signing up a new account.
 * It interacts with the SignUpController to validate and process the user's registration data.
 */
public class SignUpUI implements Runnable {

    private Client user;
    private final SignUpController controller = new SignUpController();

    /**
     * Runs the sign-up user interface.
     * Displays a prompt for registering a new account.
     * Initiates the sign-up process.
     */

    public void run() {
        System.out.println("\n\nRegister new account.");
        signUp();
    }

    /**
     * Initiates the sign-up process.
     * Requests user data from the controller.
     * Submits the user data for verification and registration.
     */
    public void signUp() {
        user = controller.requestData();
        submitData(user);
    }

    /**
     * Submits the user data for verification and registration.
     *
     * @param user The client object containing the user's registration data.
     */
    public void submitData(Client user) {
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        boolean newUser;
        int option = verifyInput.verifyInt(input, "an option.%nSubmit data?%n1 - Yes.%n(Select any other NUMBER%nto go back to the Main menu.)");
        if (option == 1) {
            newUser = controller.verifyData(user);
            if (newUser) {
                System.out.println("Operation success!");
                controller.createNewUser(user);
            } else {
                System.out.println("This user is already registered.");
            }
        }
    }
}
