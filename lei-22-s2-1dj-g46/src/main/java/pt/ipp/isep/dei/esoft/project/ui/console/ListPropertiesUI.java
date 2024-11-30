package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.AgentRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Scanner;

/**
 * The ListPropertiesUI class represents the user interface for listing properties.
 * It interacts with the ListPropertiesController to retrieve and display property information.
 */
public class ListPropertiesUI implements Runnable {
    /**
     * Constructs a ListPropertiesUI object.
     */
    public ListPropertiesUI() {
    }

    /**
     * Runs the list properties user interface.
     * Retrieves the list of properties from the property repository and applies filters and sorting settings based on user input.
     * Displays the filtered and sorted property list or a message if no properties match the criteria.
     */
    public void run() {
        Scanner ler = new Scanner(System.in);
        List<Property> propertyList = Repositories.getInstance().getPropertyRepository().getProperties();
        System.out.println();
        if (!propertyList.isEmpty()) {
            ListPropertiesController listPropertiesController = new ListPropertiesController();
            propertyList = askAboutFilters(propertyList, listPropertiesController, ler);
            propertyList = askAboutSortSetting(propertyList, listPropertiesController, ler);
            printPropertyList(propertyList);
        } else {
            System.out.print("The Property Repository is empty, publish some sale announcements and try again.");
        }
        System.out.print("\n\nPress ENTER to continue");
        ler.nextLine();
    }

    public List<Property> askAboutFilters(List<Property> properties, ListPropertiesController listPropertiesController, Scanner sc) {
        System.out.printf("Do you want to only see properties...%n1 - For Sale%n2 - For Rent%n3 - Both%n");
        properties = listPropertiesController.askAboutTypeOfSale(properties, sc);
        System.out.printf("Do you want to only see...%n1 - Lands%n2 - Apartments%n3 - House%n4 - All of them");
        properties = listPropertiesController.askAboutTypeOfProperty(properties, sc);
        System.out.printf("%nWhat agent you want to see properties from%n0 - Any agent%n");
        AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
        agentRepository.printAgents();
        properties = listPropertiesController.askAboutAgent(properties, sc, agentRepository);
        return properties;
    }

    public List<Property> askAboutSortSetting(List<Property> properties, ListPropertiesController listPropertiesController, Scanner sc) {
        System.out.printf("%nDo you want to sort in ascending/descending order of..%n1 - Price%n2 - Area%n3 - Distance from city centre%n4 - Number of photographs%n5 - No specific sorting setting");
        int sortItem = listPropertiesController.askAboutSortSetting(sc);
        if (sortItem != 5) {
            System.out.printf("%nIn what order?%n1 - Ascending%n2 - Descending");
            int sortOrder = listPropertiesController.askAboutOrder(sc);
            properties = listPropertiesController.sortList(properties, sortItem, sortOrder);
        }
        return properties;
    }

    public void printPropertyList(List<Property> propertyList) {
        if (!propertyList.isEmpty()) {
            System.out.println();
            for (Property property : propertyList) {
                System.out.println(property);
            }
        } else {
            System.out.println("There are no properties with the requested characteristics");
        }
    }
}
