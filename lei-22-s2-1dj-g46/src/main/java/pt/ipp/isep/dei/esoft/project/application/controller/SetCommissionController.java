package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementNotification;
import pt.ipp.isep.dei.esoft.project.domain.Property.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.SetCommissionValue;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SetCommissionController {

    /**
     * This method gets all properties assigned to the agent currently using the system
     * @return list of properties assigned to current agent
     */
    public List<Property> getAllPropertiesFromCurrentAgent(){
        String agentEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        PropertyRequestRepository requestRepository = Repositories.getInstance().getPropertyRequestRepository();
        return requestRepository.getPropertyRequestedToXAgent(agentEmail);
    }

    /**
     * a method used to only get properties with x type of request ('for rent' or 'for sale')
     * @param properties list of properties
     * @param sc the scanner
     * @return an updated list
     */
    public List<Property> getListOfXTypeOfRequest(List<Property> properties, Scanner sc){
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

    /**
     * a method used to only get properties with x type of property ('land', 'apartment' or 'house')
     * @param properties list of properties
     * @param sc the scanner
     * @return an updated list
     */
    public List<Property> getListOfXTypeOfProperty(List<Property> properties, Scanner sc){
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

    /**
     * This method sets the commission from the Owner.
     *
     */
    public void setCommissionFromOwner(List<Property> propertyList){
        Scanner ler = new Scanner(System.in);
        PropertyRequestRepository requestRepository = Repositories.getInstance().getPropertyRequestRepository();
        System.out.println();
        int option = -1;
        while(!propertyList.isEmpty() && option != 0) {
            option = choosePropertyRequest(requestRepository, propertyList, ler);
        }
    }

    /**
     * This method lists all the Property Requests in the propertyRequests list.
     *
     * @param propertyRequests The list of Property Requests.
     */
    public void printPropertyRequests(List<Property> propertyRequests){
        for (int i = 0; i < propertyRequests.size(); i++) {
            System.out.printf("%d.%n%s%n%n", (i+1), propertyRequests.get(i));
        }
        System.out.printf("0. - Exit%n%n");
    }

    /**
     * This method lets the user choose a Property Request to set a commission for.
     *
     * @param requestRepository The Property Request Repository.
     * @param propertyRequests The List of Property Requests
     * @param ler The Scanner to scan input from the keyboard.
     */
    public int choosePropertyRequest(PropertyRequestRepository requestRepository, List<Property> propertyRequests, Scanner ler){
        boolean valid1 = false;
        int propertyNumber;
        do {
            printPropertyRequests(propertyRequests);
            VerifyInput verifyInput = new VerifyInput();
            boolean valid2 = false;
            do {
                System.out.println("What property request do you want to set a commission to?");
                propertyNumber = verifyInput.verifyInt(ler, "Property Request number");
                if (propertyNumber < 0 || propertyNumber > propertyRequests.size()) {
                    System.out.println("Please insert a valid number.");
                } else if (propertyNumber == 0) {
                    valid1 = true;
                    valid2 = true;
                } else {
                    setCommission(propertyRequests.get(propertyNumber-1), verifyInput, ler);
                    requestRepository.remove(propertyRequests.get(propertyNumber-1));
                    propertyRequests.remove(propertyNumber-1);
                    valid2 = true;
                }
            } while (!valid2 && !requestRepository.isEmpty());
        } while(!valid1 && !requestRepository.isEmpty());
        return propertyNumber;
    }

    /**
     * This method lets the user set the commission for a Property Request
     *
     * @param propertyRequest The Property request to set a commission for.
     * @param verifyInput The VerifyInput class Object that will call the verifyInt method.
     * @param input The Scanner to scan input from the keyboard.
     */
    public void setCommission(Property propertyRequest, VerifyInput verifyInput, Scanner input){
        String commissionValue = propertyRequest.getRequest().getCommissionValue();
        String type = propertyRequest.getRequest().getType();
        boolean agreement1 = verifyInput.verifyBoolean(input, "Is there an agreement on the commission value");
        if(!agreement1) {
            SetCommissionValue setCommissionValue = new SetCommissionValue();
            commissionValue = setCommissionValue.defineCommissionValue(input, verifyInput, propertyRequest.getRequest().getPrice());
        }
        boolean agreement2 = verifyInput.verifyBoolean(input, "Is there an agreement on the final price");
        if(!agreement2){
            float finalPrice = verifyInput.verifyFloat(input, "Final Price");
            propertyRequest.setFinalPrice(finalPrice);
        }
        PropertyAnnouncementRepository propertyAnnouncementRepository = Repositories.getInstance().getPropertyRepository();
        LocalDate localDate = LocalDate.now();
        String date = localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
        propertyRequest.setAnnouncementDate(date);
        Request request = new Request(type, propertyRequest.getRequest().getPrice(), propertyRequest.getRequest().getPriceUnit(), "Published", propertyRequest.getRequest().getCommissionDate(), propertyRequest.getRequest().getOwner(), commissionValue, propertyRequest.getRequest().getDuration());
        propertyRequest.setRequest(request);
        AnnouncementNotificationRepository announcementNotificationRepository = Repositories.getInstance().getAnnouncementNotificationRepository();
        announcementNotificationRepository.add(new AnnouncementNotification(propertyRequest, request.getOwner(), date, propertyRequest.getAgent().getName(), propertyRequest.getAgent().getPhoneNumber()));
        propertyAnnouncementRepository.add(propertyRequest);
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        storeRepository.getStoreByName(propertyRequest.getAgent().getStore()).addProperty(propertyRequest);
        System.out.println("Request published!");
        input.nextLine();
    }
}
