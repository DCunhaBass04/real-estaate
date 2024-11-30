package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.LeaveMessageController;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.AgentRepository;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;

public class LeaveMessageUI implements Runnable {

    private Message message;
    private List<Property> propertyList;
    private final LeaveMessageController controller = new LeaveMessageController();
    /**
     * This method runs the ui.
     *
     */
    public void run() {
        showPropertyList();
        if (!propertyList.isEmpty()){
            requestToVisit();
        } else {
            Scanner input = new Scanner(System.in);
            input.nextLine();
        }
    }

    /**
     * This method makes as many visit requests as the user decides.
     *
     */
    public void requestToVisit(){
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        int requests;
        int requestIndex = 0;
        requests = verifyInput.verifyInt( input, "how many visit requests you want to make");
        while (requests != 0){
            requestIndex++;
            System.out.printf("%nRequest number %d:%n", requestIndex);
            writeMessage();
            sendMessageToAgent();
            requests--;
        }
    }

    /**
     * This method registers the message.
     *
     */
    public void sendMessageToAgent(){
        boolean verified;
        verified = controller.verifyMessage(message);
        if (verified){
            MessageRepository messageRepository = Repositories.getInstance().getMessageRepository();
            messageRepository.add(message);
            System.out.printf("Message sent successfully.%n");
        }
        if (!verified){
            System.out.printf("Message could not be sent.%n");
        }
    }
    /**
     * This method requests data for a message.
     *
     */
    public void writeMessage(){
        message = controller.requestData(propertyList);
    }

    /**
     * This method gets a property list to display.
     *
     */
    public void showPropertyList(){
        Scanner ler = new Scanner(System.in);
        propertyList = Repositories.getInstance().getPropertyRepository().getProperties();
        System.out.println();
        if(!propertyList.isEmpty()) {
            propertyList = askAboutFilters(propertyList, ler);
            propertyList = controller.sortList(propertyList);
            printPropertyList(propertyList);
        } else {
            System.out.print("The Property Repository is empty, publish some sale announcements and try again.");
        }
    }

    /**
     * This method gets the search filters to personalize the property list and makes the list follow them.
     *
     * @param  properties The complete list of properties.
     * @param sc a Scanner object
     */
    public List<Property> askAboutFilters(List<Property> properties, Scanner sc){
        System.out.printf("Do you want to only see properties...%n1 - For Sale%n2 - For Rent%n3 - Both%n");
        properties = controller.askAboutTypeOfSale(properties, sc);
        System.out.printf("Do you want to only see...%n1 - Lands%n2 - Apartments%n3 - House%n4 - All of them");
        properties = controller.askAboutTypeOfProperty(properties, sc);
        System.out.printf("%nWhat agent you want to send the request(s) to?%n0 - Any agent%n");
        AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
        agentRepository.printAgents();
        properties = controller.askAboutAgent(properties, sc, agentRepository);
        return properties;
    }

    /**
     * Displays the property list on the screen.
     *
     * @param propertyList The final property list.
     */
    public void printPropertyList(List<Property> propertyList){
        int properties = 0;
        if(!propertyList.isEmpty()) {
            System.out.println();
            for (Property property : propertyList) {
                properties++;
                System.out.printf("%d.%n", properties);
                System.out.println(property);
            }
        } else {
            System.out.println("There are no properties with the requested characteristics.%n");
        }
    }
}
