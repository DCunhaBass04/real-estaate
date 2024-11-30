package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SetCommissionController;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;

import java.util.List;
import java.util.Scanner;

public class SetCommissionUI implements Runnable{
    public SetCommissionUI(){}

    /**
     * Creates a new SetCommissionController object named setCommissionController.
     * Calls the setCommissionFromOwner() method on the setCommissionController object, which prompts the user to set the commission rate for their property.
     */
    public void run(){
        Scanner ler = new Scanner(System.in);
        SetCommissionController setCommissionController = new SetCommissionController();
        List<Property> propertiesFromCurrentAgent = setCommissionController.getAllPropertiesFromCurrentAgent();
        if(!propertiesFromCurrentAgent.isEmpty()) {
            propertiesFromCurrentAgent = askAboutFilters(propertiesFromCurrentAgent, setCommissionController, ler);
            setCommissionController.setCommissionFromOwner(propertiesFromCurrentAgent);
        } else {
            System.out.print("The Property Request Repository is empty, get some sale announcements and try again.");
        }
        System.out.print("\n\nPress ENTER to continue");
        ler.nextLine();
    }

    /**
     * This method is used to filter the list of property requests according to the user's demands
     * @param properties list of property requests done to the current user
     * @param setCommissionController the controller
     * @param sc the used scanner
     * @return an updated list of property requests
     */
    public List<Property> askAboutFilters(List<Property> properties, SetCommissionController setCommissionController, Scanner sc){
        System.out.printf("%nDo you want to only see properties...%n1 - For Sale%n2 - For Rent%n3 - Both%n");
        properties = setCommissionController.getListOfXTypeOfRequest(properties, sc);
        System.out.printf("Do you want to only see...%n1 - Lands%n2 - Apartments%n3 - House%n4 - All of them");
        properties = setCommissionController.getListOfXTypeOfProperty(properties, sc);
        return properties;
    }
}
