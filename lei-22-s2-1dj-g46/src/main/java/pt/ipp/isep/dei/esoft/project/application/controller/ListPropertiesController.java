package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.AgentRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;

public class ListPropertiesController {
    /**
     * Creates a new Scanner object named ler to read user input from the console.
     * Gets an instance of a PropertyRepository object from the Repositories.
     * Checks if the PropertyRepository is empty. If it's not empty, it calls the printProperties() method on the propertyRepository object, which prints out a list of properties.
     * If the PropertyRepository is empty, it prints a message saying that there are no sale announcements.
     * Prompts the user to press Enter to continue.
     * Waits for the user to press Enter before returning.
     */
    public List<Property> askAboutTypeOfSale(List<Property> properties, Scanner sc){
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do{
            option = verifyInput.verifyInt(sc, "Type of Request");
            if (option == 1 || option == 2 || option == 3){
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        }while(!valid);
        if(option == 1 || option == 2){
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            switch(option){
                case 1:
                    properties = editAndSortLists.getPropertiesWithXTypeOfSale(properties, "for sale");
                    break;
                case 2:
                    properties = editAndSortLists.getPropertiesWithXTypeOfSale(properties, "for rent");
                    break;
            }
        }
        return properties;
    }
    public List<Property> askAboutTypeOfProperty(List<Property> properties, Scanner sc){
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do{
            option = verifyInput.verifyInt(sc, "Type of Property");
            if (option == 1 || option == 2 || option == 3 || option == 4){
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        }while(!valid);
        if(option == 1 || option == 2 || option == 3){
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            switch(option){
                case 1:
                    properties = editAndSortLists.getPropertiesIfTheyAreLand(properties);
                    break;
                case 2:
                    properties = editAndSortLists.getPropertiesIfTheyAreApartment(properties);
                    break;
                case 3:
                    properties = editAndSortLists.getPropertiesIfTheyAreHouse(properties);
                    break;
            }
        }
        return properties;
    }
    public List<Property> askAboutAgent(List<Property> properties, Scanner sc, AgentRepository agentList){
        int numOfOptions = agentList.getSize();
        int option;
        boolean valid = false;
        VerifyInput verifyInput = new VerifyInput();
        do{
            option = verifyInput.verifyInt(sc, "Agent Number");
            if (!(option < 0 || option > numOfOptions)) {
                valid = true;
            } else {
                System.out.println("Invalid number, try again.");
            }
        }while(!valid);
        if(option != 0){
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            properties = editAndSortLists.getPropertiesByXAgent(properties, agentList.getAgents().get(option-1).getEmail());
        }
        return properties;
    }
    public int askAboutSortSetting(Scanner sc) {
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do{
            option = verifyInput.verifyInt(sc, "Sort Value");
            if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5){
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        }while(!valid);
        return option;
    }
    public int askAboutOrder(Scanner sc){
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do{
            option = verifyInput.verifyInt(sc, "Sort Order");
            if (option == 1 || option == 2){
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        }while(!valid);
        return option;
    }
    public List<Property> sortList(List<Property> properties, int sortItem, int sortOrder){
        EditAndSortLists editAndSortLists = new EditAndSortLists();
        switch (sortOrder){
            case 1:
                switch (sortItem){
                    case 1:
                        properties = editAndSortLists.sortPropertiesByAscendingPrice(properties);
                        break;
                    case 2:
                        properties = editAndSortLists.sortPropertiesByAscendingArea(properties);
                        break;
                    case 3:
                        properties = editAndSortLists.sortPropertiesByAscendingDistance(properties);
                        break;
                    case 4:
                        properties = editAndSortLists.sortPropertiesByAscendingNumOfPhotographs(properties);
                        break;
                }
                break;
            case 2:
                switch (sortItem){
                    case 1:
                        properties = editAndSortLists.sortPropertiesByDescendingPrice(properties);
                        break;
                    case 2:
                        properties = editAndSortLists.sortPropertiesByDescendingArea(properties);
                        break;
                    case 3:
                        properties = editAndSortLists.sortPropertiesByDescendingDistance(properties);
                        break;
                    case 4:
                        properties = editAndSortLists.sortPropertiesByDescendingNumOfPhotographs(properties);
                        break;
                }
                break;
        }
        return properties;
    }
}
