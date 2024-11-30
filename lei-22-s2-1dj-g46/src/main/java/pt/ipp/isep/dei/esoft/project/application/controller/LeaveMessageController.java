package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.time.LocalDate;
import java.util.*;

/**
 * The LeaveMessageController class represents a controller for leaving messages.
 * It handles the interaction between the user interface and the message repository.
 */
public class LeaveMessageController {
    private AuthenticationRepository authenticationRepository = null;
    private ClientRepository clientRepository = null;
    private MessageRepository messageRepository = null;
    private Scanner input = new Scanner(System.in);
    private VerifyInput verifyInput = new VerifyInput();

    /**
     * Default constructor for the LeaveMessageController class.
     * It initializes the authentication, client, and message repositories.
     */
    public LeaveMessageController() {
        getAuthenticationRepository();
        getClientRepository();
        getMessageRepository();
    }

    /**
     * Parameterized constructor for the LeaveMessageController class.
     * It sets the authentication, client, and message repositories based on the provided parameters.
     *
     * @param authenticationRepository The authentication repository to be set.
     * @param clientRepository         The client repository to be set.
     * @param messageRepository        The message repository to be set.
     */
    public LeaveMessageController(AuthenticationRepository authenticationRepository, ClientRepository clientRepository, MessageRepository messageRepository) {
        this.authenticationRepository = authenticationRepository;
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * Retrieves the authentication repository.
     * If the repository is not set, it retrieves it from the repositories instance.
     *
     * @return The authentication repository.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Retrieves the client repository.
     * If the repository is not set, it retrieves it from the repositories instance.
     *
     * @return The client repository.
     */
    private ClientRepository getClientRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }

    /**
     * Retrieves the message repository.
     * If the repository is not set, it retrieves it from the repositories instance.
     *
     * @return The message repository.
     */
    private MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            Repositories repositories = Repositories.getInstance();
            messageRepository = repositories.getMessageRepository();
        }
        return messageRepository;
    }

    /**
     * Verifies if a message is valid.
     *
     * @param message The message to be verified.
     * @return True if the message is valid, false otherwise.
     */
    public boolean verifyMessage(Message message) {
        boolean valid;
        valid = messageRepository.validateMessage(message);
        return valid;
    }

    /**
     * Selects a property from a list of properties.
     *
     * @param propertyList The list of properties.
     * @param input        The scanner object for user input.
     * @param verifyInput  The VerifyInput object for input verification.
     * @return The selected property.
     */
    public Property selectProperty(List<Property> propertyList, Scanner input, VerifyInput verifyInput) {
        int option = verifyInput.verifyInt(input, "the number of the property you want to visit");
        while (option > propertyList.size()) {
            System.out.printf("Invalid option.%n");
            option = verifyInput.verifyInt(input, "the number of the property you want to visit");
        }
        return propertyList.get(option - 1);
    }

    /**
     * Requests message data from the user.
     *
     * @param propertyList The list of properties.
     * @return The created message.
     */
    public Message requestData(List<Property> propertyList) {
        String email = authenticationRepository.getCurrentUserSession().getUserId().toString();
        Client currentClient = clientRepository.getClientByEmail(email);

        VerifyInput verifyInput = new VerifyInput();
        Property property = selectProperty(propertyList, input, verifyInput);
        String agentID = property.getAgentEmail();

        boolean insertASubject = verifyInput.verifyBoolean(input, "Do you want to insert a subject");
        String subject = "";
        if (insertASubject){
            subject = verifyInput.verifyComplexString(input, "your subject");
        }

        System.out.printf("%nPlease only press ENTER when you are ready to submit data.%nYou cannot write paragraphs.%n");
        String text = verifyInput.verifyComplexString(input, "content of the message");
        Date preferredDate = requestPreferredDate("Make sure to type the Date in the DD-MM-YYYY format.");
        System.out.printf("Type the following data in hours, following the 24 hours format.%nExample: 15%nmeaning 3 PM.%n");
        int startOfVisit = getStartOfVisit();
        int endOfVisit = getEndOfVisit(startOfVisit);
        if(!insertASubject) {
            return new Message(agentID, property, text, currentClient, preferredDate, startOfVisit, endOfVisit);
        } else {
            return new Message(agentID, property, subject, text, currentClient, preferredDate, startOfVisit, endOfVisit);
        }
    }

    public int getStartOfVisit(){
        int startOfVisit = verifyInput.verifyInt(input, "Start of Visit");
        while (startOfVisit > 23 || startOfVisit < 0){
            System.out.printf("Invalid hour. Try again.%n");
            startOfVisit = verifyInput.verifyInt(input, "Start of Visit");
        }
        return startOfVisit;
    }

    public Date requestPreferredDate(String prompt){
        boolean valid = false;
        LocalDate localDate = LocalDate.now();
        int currentDate = Integer.parseInt(String.format("%4d%02d%02d",localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()));
        Date preferredDate = Utils.readDateFromConsole(prompt);
        while (!valid){
            int day = preferredDate.getDate();
            int month = preferredDate.getMonth()+1;
            int year = preferredDate.getYear()+1900;
            int preferredDateInt = Integer.parseInt(String.format("%4d%02d%02d",year, month, day));
            if (preferredDateInt >= currentDate){
                valid = true;
            } else {
                System.out.printf("You cannot request to visit a property in a day prior to the current one. Try again.%n");
                preferredDate = Utils.readDateFromConsole(prompt);
            }
        }
        return preferredDate;
    }

    /**
     * Retrieves the end time of a visit.
     *
     * @param start The start time of the visit.
     * @return The end time of the visit.
     */
    public int getEndOfVisit(int start) {
        int end = verifyInput.verifyInt(input, "End of Visit");
        while (end <= start || end > 24 || end < 1) {
            System.out.printf("You have chosen an hour prior to the chosen start of the visit, or chosen an invalid hour.%nTry again.%n");
            end = verifyInput.verifyInt(input, "End of Visit");
        }
        return end;
    }

    /**
     * Asks the user about the type of sale for properties.
     *
     * @param properties The list of properties.
     * @param sc         The scanner object for user input.
     * @return The updated list of properties based on the selected type of sale.
     */
    public List<Property> askAboutTypeOfSale(List<Property> properties, Scanner sc) {
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do {
            option = verifyInput.verifyInt(sc, "Type of Request");
            if (option == 1 || option == 2 || option == 3) {
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        } while (!valid);
        if (option == 1 || option == 2) {
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            switch (option) {
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

    /**
     * Asks the user about the type of property.
     *
     * @param properties The list of properties.
     * @param sc         The scanner object for user input
     * @return The updated list of properties based on the selected type of property.
     */
    public List<Property> askAboutTypeOfProperty(List<Property> properties, Scanner sc) {
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do {
            option = verifyInput.verifyInt(sc, "Type of Property");
            if (option == 1 || option == 2 || option == 3 || option == 4) {
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        } while (!valid);
        if (option == 1 || option == 2 || option == 3) {
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            switch (option) {
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

    /**
     * Asks the user about the agent for properties.
     *
     * @param properties The list of properties.
     * @param sc         The scanner object for user input.
     * @param agentList  The list of agents.
     * @return The updated list of properties based on the selected agent.
     */
    public List<Property> askAboutAgent(List<Property> properties, Scanner sc, AgentRepository agentList) {
        int numOfOptions = agentList.getSize();
        int option;
        boolean valid = false;
        VerifyInput verifyInput = new VerifyInput();
        do {
            option = verifyInput.verifyInt(sc, "Agent Number");
            if (!(option < 0 || option > numOfOptions)) {
                valid = true;
            } else {
                System.out.println("Invalid number, try again.");
            }
        } while (!valid);
        if (option != 0) {
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            properties = editAndSortLists.getPropertiesByXAgent(properties, agentList.getAgents().get(option - 1).getEmail());
        }
        return properties;
    }

    /**
     * Sorts the list of properties.
     *
     * @param properties The list of properties.
     * @return The sorted list of properties.
     */
    public List<Property> sortList(List<Property> properties) {
        List<Property> mutablePropertyList = new ArrayList<>(properties);
        Collections.reverse(mutablePropertyList);
        return Collections.unmodifiableList(mutablePropertyList);
    }
}