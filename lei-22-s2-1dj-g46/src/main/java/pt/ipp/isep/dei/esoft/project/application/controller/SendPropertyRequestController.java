package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property.Apartment;
import pt.ipp.isep.dei.esoft.project.domain.Property.House;
import pt.ipp.isep.dei.esoft.project.domain.Property.Land;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.SetCommissionValue;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class SendPropertyRequestController {
    /**
     * Defines a public method chooseAgent which takes two parameters, a Scanner object named input and a VerifyInput object named verifyInput. The method returns an Agent object.
     * The purpose of this method is to prompt the user to select an agent from a list of available agents and return the selected agent.
     * The method begins by printing a message asking the user to choose an agent. It then retrieves an AgentRepository object from a Repositories singleton object, and prints a list of available agents using the printAgents method of the AgentRepository.
     * The method then enters a do-while loop that prompts the user to enter a valid integer option for the selected agent. It uses the verifyInput object to verify that the input is a valid integer, and then checks if the option is within the range of valid options. If the option is valid, the loop exits and the method returns the selected agent using the getxAgent method of the AgentRepository.
     * If the option is not valid, the loop continues to prompt the user to enter a valid option until a valid option is entered.
     * @param input Scanner that will read from the user's keyboard
     * @param verifyInput Object that will verify if an input is valid
     * @return The Agent that matches the chosen one
     */
    public Agent chooseAgent(Scanner input, VerifyInput verifyInput){
        System.out.printf("%nWhich agent do you want to send the request(s) to?%n");
        AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
        agentRepository.printAgents();
        int numOfOptions = agentRepository.getSize();
        int option;
        boolean valid = false;
        do{
            option = verifyInput.verifyInt(input, "Agent Number");
            if (!(option < 1 || option > numOfOptions)) {
                valid = true;
            } else {
                System.out.println("Invalid number, try again.");
            }
        }while(!valid);
        return agentRepository.getxAgent(option);
    }
    /**
     * This method lets the user select the number of Property Requests they want to make.
     *
     * @param input The Scanner to scan input from the keyboard.
     * @param agentName The name of the Agent the Property Request will be sent to.
     * @param verifyInput The VerifyInput class Object that will call the verifyInt method.
     * @return The option chosen by the user.
     */
    public int chooseNumberOfProperties(Scanner input, String agentName, VerifyInput verifyInput){
        System.out.printf("%nHow many property requests do you want to send to Agent " + agentName + "? (more than 0)%n");
        boolean valid = false;
        int option;
        do{
            option = verifyInput.verifyInt(input, "Number of Property Requests");
            if (option > 0){
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        }while (!valid);
        return option;
    }

    /**
     * The method first prints a message to the console asking the user how many property requests they want to send to the agent. Then it enters a loop that continues until the user inputs a valid integer greater than zero.     * @param input:Scanner object that reads input from the console.
     * Within the loop, the verifyInput.verifyInt() method is called with two arguments: the input object and a String message indicating the type of input expected ("Number of Property Requests"). This method reads the user input and returns an integer.
     * If the integer returned is greater than zero, the loop is exited and the input value is returned. Otherwise, a message is printed to the console asking the user to input a valid number and the loop continues.
     * @param agent: String that represents the name of the agent to whom the requests will be sent.
     * @param verifyInput: object that implements the VerifyInput interface and provides methods for verifying user input.
     */
    public void fillRequest(Scanner input, Agent agent, VerifyInput verifyInput){
        System.out.printf("%nInsert the data in the appropriate format.%nThe same Property cannot be requested more than once.%n%n");
        Request request = createRequest(input, verifyInput);
        int typeOfProperty = insertTypeOfProperty(input, verifyInput);
        float area = verifyInput.verifyFloat(input, "Area (in m^2)");
        String location = verifyInput.verifyComplexString(input, "Location");
        float distanceFromCityCentre = verifyInput.verifyFloat(input, "Distance from the City Centre (in kms)");
        File[] photographs = insertPhotographs(input, verifyInput);
        switch (typeOfProperty) {
            case 1 :
                PropertyRequestRepository requestRepository = Repositories.getInstance().getPropertyRequestRepository();
                requestRepository.add(new Land(area, location, distanceFromCityCentre, agent, photographs, request.getPrice(), "n/a", "n/a", request));
                break;
            case 2 :
                fillRequestForApartment(area, location, distanceFromCityCentre, agent, request, photographs, input, verifyInput);
                break;
            case 3 :
                fillRequestForHouse(area, location, distanceFromCityCentre, agent, request, photographs, input, verifyInput);
                break;
        }
        System.out.println("Request sent to the specified Agent!");
    }

    /**
     * This Java method fills a request for an apartment by creating an ApartmentRequest object and adding it to a PropertyRequestRepository.
     * @param area: float that represents the area of the apartment.
     * @param location: String that represents the location of the apartment.
     * @param distanceFromCityCentre: float that represents the distance of the apartment from the city center.
     * @param agent: Agent object that represents the agent responsible for handling the request.
     * @param request: Request that represents the details for the announcement request.
     * @param photographs: array of File objects that represents photographs of the apartment.
     * @param input: Scanner object that reads input from the console.
     * @param verifyInput: object that implements the VerifyInput interface and provides methods for verifying user input.
     */
    public void fillRequestForApartment(float area, String location, float distanceFromCityCentre, Agent agent, Request request, File[] photographs, Scanner input, VerifyInput verifyInput){
        int numOfBedrooms = verifyInput.verifyInt(input, "Number Of Bedrooms");
        int numOfBathrooms = verifyInput.verifyInt(input, "Number of Bathrooms");
        int numOfParkingSpaces = verifyInput.verifyInt(input, "Number of Parking Spaces");
        boolean centralHeating = verifyInput.verifyBoolean(input, "Is there central heating");
        boolean airConditioning = verifyInput.verifyBoolean(input, "Is there air conditioning");
        PropertyRequestRepository requestRepository = Repositories.getInstance().getPropertyRequestRepository();
        requestRepository.add(new Apartment(area, location, distanceFromCityCentre, agent, numOfBedrooms, numOfBathrooms, numOfParkingSpaces, centralHeating, airConditioning, photographs, request.getPrice(), "n/a", "n/a", request));
    }

    /**
     * The method prompts the user to enter the number of bedrooms, bathrooms, parking spaces, available equipment, basement, inhabitable loft, and sun exposure direction for the house. It then creates a new HouseRequest object with the given parameters and adds it to a PropertyRequestRepository object.
     * @param area: The area of the house.
     * @param location: The location of the house.
     * @param distanceFromCityCentre: The distance of the house from the city centre.
     * @param agent: The agent who is filling the request for the house.
     * @param request: Request that represents the details for the announcement request.
     * @param photographs: An array of photographs of the house.
     * @param input: A scanner object to read input from the user.
     * @param verifyInput: A custom class to verify user input.
     */
    public void fillRequestForHouse(float area, String location, float distanceFromCityCentre, Agent agent, Request request, File[] photographs, Scanner input, VerifyInput verifyInput){
        int numOfBedrooms = verifyInput.verifyInt(input, "Number Of Bedrooms");
        int numOfBathrooms = verifyInput.verifyInt(input, "Number of Bathrooms");
        int numOfParkingSpaces = verifyInput.verifyInt(input, "Number of Parking Spaces");
        boolean centralHeating = verifyInput.verifyBoolean(input, "Is there central heating");
        boolean airConditioning = verifyInput.verifyBoolean(input, "Is there air conditioning");
        boolean basement = verifyInput.verifyBoolean(input, "Is there a basement");
        boolean inhabitableLoft = verifyInput.verifyBoolean(input, "Is there loft");
        String sunExposure = verifyInput.verifySimpleString(input, "Sun Exposure Direction");
        PropertyRequestRepository requestRepository = Repositories.getInstance().getPropertyRequestRepository();
        requestRepository.add(new House(area, location, distanceFromCityCentre, agent, numOfBedrooms, numOfBathrooms, numOfParkingSpaces, centralHeating, airConditioning, basement, inhabitableLoft, sunExposure, photographs, request.getPrice(), "n/a", "n/a", request));
    }

    /**
     * The method displays a message to the user, asking them to enter the type of property they want to send to the agent.
     * The method then prompts the user to enter a valid number, using the verifyInput object to validate the input. The method will keep asking for input until a valid number is entered, and it will return the selected option as an integer.
     * @param input: A scanner object to read input from the user.
     * @param verifyInput: A custom class to verify user input.
     * @return the chosen option.
     */
    public int insertTypeOfProperty(Scanner input, VerifyInput verifyInput){
        System.out.printf("%nInsert the type of Property you want to send to the Agent.%n1 - Land%n2 - Apartment%n3 - House%n");
        boolean valid = false;
        int option;
        do{
            option = verifyInput.verifyInt(input, "Type of Property");
            if (option == 1 || option == 2 || option == 3){
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        }while(!valid);
        return option;
    }

    /**
     * It takes a Scanner and a VerifyInput object as input parameters.
     * It prompts the user to input the number of photographs they want to send and then calls the verifyInt method of the VerifyInput object to validate the input
     * It then calls the verifyFileArray method of the VerifyInput object to get an array of File objects, which represent the photographs.
     * The number of File objects returned by the verifyFileArray method is equal to the number of photographs entered by the user.
     * Finally, the method returns the array of File objects.
     * @param input: A scanner object to read input from the user.
     * @param verifyInput: A custom class to verify user input.
     * @return the array of the property's photographs.
     */
    public File[] insertPhotographs(Scanner input, VerifyInput verifyInput){
        System.out.printf("%nHow many photographs of the property do you want to send?%n");
        int numOfPhotographs = verifyInput.verifyInt(input, "Number of photographs");
        return verifyInput.verifyFileArray(input, numOfPhotographs);
    }

    /**
     * The method displays a message to the user, asking them to enter the type of request they want to send to the agent.
     * The method then prompts the user to enter a valid number, using the verifyInput object to validate the input. The method will keep asking for input until a valid number is entered, and it will return the selected option as an integer.
     * @param input: A scanner object to read input from the user.
     * @param verifyInput: A custom class to verify user input.
     * @return the respective type of request.
     */
    public String insertTypeOfRequest(Scanner input, VerifyInput verifyInput){
        System.out.printf("Insert the type of Request you want to send to the Agent.%n1 - For sale%n2 - For rent%n");
        boolean valid = false;
        int option;
        String typeOfRequest;
        do{
            option = verifyInput.verifyInt(input, "Type of Request");
            if (option == 1 || option == 2){
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        }while(!valid);
        if(option == 1){
            typeOfRequest = "for sale";
        } else {
            typeOfRequest = "for rent";
        }
        return typeOfRequest;
    }

    /**
     * This method is used to finalize the request and send it to the agent
     * @param input the scanner
     * @param verifyInput the object used to verify all inputs
     * @return the finished request object
     */
    public Request createRequest(Scanner input, VerifyInput verifyInput){
        String typeOfSale = insertTypeOfRequest(input, verifyInput);
        String priceUnit;
        if(typeOfSale.equals("for sale")){
            priceUnit = "€";
        } else {
            priceUnit = "€/month";
        }
        float requestedPrice = verifyInput.verifyFloat(input, String.format("Requested Price (in %s)", priceUnit));
        int duration = 1;
        if(typeOfSale.equals("for rent")){
            duration = verifyInput.verifyInt(input, "duration of payment (in months)");
        }
        LocalDate localDate = LocalDate.now();
        String currentDate = localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        String email = authenticationRepository.getCurrentUserSession().getUserId().toString();
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        Client currentClient = clientRepository.getClientByEmail(email);
        SetCommissionValue setCommissionValue = new SetCommissionValue();
        String commissionValue = setCommissionValue.defineCommissionValue(input, verifyInput, requestedPrice);
        return new Request(typeOfSale, requestedPrice, priceUnit, "Requested", currentDate, currentClient, commissionValue, duration);
    }
}