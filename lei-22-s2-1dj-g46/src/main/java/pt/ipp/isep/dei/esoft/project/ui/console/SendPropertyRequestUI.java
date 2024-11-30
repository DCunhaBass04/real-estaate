package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SendPropertyRequestController;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.Scanner;

public class SendPropertyRequestUI implements Runnable{

    public SendPropertyRequestUI(){}

    /**
     * This is a method in a class that is responsible for running a sequence of actions related to sending property requests.
     * The method starts by creating a Scanner object to read input from the console. It also creates a VerifyInput object, which is used to validate the input data.
     * Then, it creates a SendPropertyRequestController object, which is responsible for managing the requests, and calls the chooseAgent() method to select an agent to send the property request to. This method takes the Scanner object and VerifyInput object as parameters.
     * Next, the method calls the chooseNumberOfProperties() method to prompt the user to enter the number of properties they want to send requests for. This method takes the Scanner object, the name of the selected agent, and the VerifyInput object as parameters.
     * Finally, the method uses a for loop to call the fillRequest() method from the SendPropertyRequestController object for each property request. This method takes the Scanner object, the selected agent, and the VerifyInput object as parameters, and prompts the user to enter information about the property they want to request.
     */
    public void run(){
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        SendPropertyRequestController sendPropertyRequestController = new SendPropertyRequestController();
        Agent agent = sendPropertyRequestController.chooseAgent(input, verifyInput);
        int numOfProperties = sendPropertyRequestController.chooseNumberOfProperties(input, agent.getName(), verifyInput);
        for (int i = 0; i < numOfProperties; i++) {
            sendPropertyRequestController.fillRequest(input, agent, verifyInput);
            input.nextLine();
        }
    }
}
