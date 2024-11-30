package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ResetSystemController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.Scanner;

public class ResetSystemUI implements Runnable {
    private ResetSystemController ctrl = new ResetSystemController();

    /**
     * Asks if the user wants to reset the system.
     * If the answer is yes, it is almost completely reset (details are given in that print)
     */
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WARNING! This option will reset the entire system, deleting every property, account (except yours, the network manager's and the network manager's) and store.");
        VerifyInput verifyInput = new VerifyInput();
        boolean proceed = verifyInput.verifyBoolean(sc, "Do you wish to proceed");
        if(proceed){
            ctrl.resetSystem();
            System.out.println("Operation Successful");
        }
        System.out.println("Press ENTER to continue.");
        sc.nextLine();
    }
}
