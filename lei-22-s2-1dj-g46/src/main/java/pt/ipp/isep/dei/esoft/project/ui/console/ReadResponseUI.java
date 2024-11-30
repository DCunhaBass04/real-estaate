package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ReadResponseController;
import pt.ipp.isep.dei.esoft.project.domain.Response;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;


/**
 * The type Read response ui.
 */
public class ReadResponseUI implements Runnable {

    private final ReadResponseController controller = new ReadResponseController();
    private final Scanner sc = new Scanner(System.in);
    private final VerifyInput verifyInput = new VerifyInput();

    /**
     * This method checks if the current user is n agent or a client.
     * If it's an agent, it will print all responses sent by them and their states
     * If it's a client, it will print all current 'Pending' responses sent to said client, asking them to respond to it/them
     */
    public void run() {
        Agent agent = controller.getCurrentAgentEmail();
        List<Response> responseList;
        if (agent == null) {
            responseList = controller.getResponsesToClient();
            printResponseList(responseList);
            if (responseList.size() != 0) {
                System.out.println("Select a response to accept it or decline");
                Response choosenResponse = chooseResponse(responseList);
                boolean option = acceptOrDeclineResponse();
                String reason;
                if (option) {
                    controller.changeResponse(choosenResponse);
                } else {
                    reason = specifyTheReasonToDecline();
                    controller.changeResponseWithReason(choosenResponse, reason);
                }
                System.out.println("Press Enter to continue");
                sc.nextLine();
            }
        } else {
            responseList = controller.getResponsesToAgent();
            printResponseList(responseList);
            System.out.println("Press Enter to continue");
            sc.nextLine();
        }
    }

    /**
     * Print response list.
     *
     * @param responseList the response list
     */
    public void printResponseList (List<Response> responseList) {
        if (!responseList.isEmpty()) {
            System.out.println();
            for (int i = 0; i < responseList.size(); i++) {
                Response response = responseList.get(i);
                System.out.println((i + 1) + ". " + response);
            }
        } else {
            System.out.println("There are no responses");
            System.out.println("Press Enter to continue");
            sc.nextLine();
        }
    }

    /**
     * Choose response response.
     *
     * @param responseList the response list
     * @return the response
     */
    public Response chooseResponse(List<Response> responseList) {
        int option = verifyInput.verifyInt(sc, "option");
        return responseList.get(option-1);
    }

    /**
     * Accept or decline response boolean.
     *
     * @return the boolean
     */
    public boolean acceptOrDeclineResponse() {
        return verifyInput.verifyBoolean(sc, "Do you accept this response");
    }

    /**
     * Specify the reason to decline string.
     *
     * @return the string
     */
    public String specifyTheReasonToDecline() {
        System.out.println("Please specify the reason to decline");
        return sc.nextLine();
    }
}
