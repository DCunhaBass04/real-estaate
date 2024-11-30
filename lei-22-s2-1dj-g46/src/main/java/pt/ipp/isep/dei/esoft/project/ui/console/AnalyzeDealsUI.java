package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AnalyzeDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.AgentRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;

public class AnalyzeDealsUI implements Runnable {
    private AnalyzeDealsController ctrl = new AnalyzeDealsController();
    private VerifyInput verifyInput = new VerifyInput();
    private Scanner sc = new Scanner(System.in);
    private String item;
    private final float EUROTODOLLAR = 1.07f;
    private final String AREA = "area";
    private final String DISTANCE = "distance from city centre";
    private final String NUMBEROFBEDROOMS = "bedrooms";
    private final String NUMBEROFBATHROOMS = "bathrooms";
    private final String NUMBEROFPARKINGSPACES = "parking spaces";

    /**
     * This method runs the functionality, letting you choose from a list of regression models
     */
    @Override
    public void run() {
        List<Property> soldApartmentsOrHouses = ctrl.getApartmentsOrHouses();
        if(!(soldApartmentsOrHouses.size() < 2)) {
            System.out.printf("What linear regression model do you desire to see?%n1 - 'Area' to 'Price'.%n2 - 'Distance from city centre' to 'Price'" +
                    "%n3 - 'Number of Bedrooms' to 'Price'%n4 - 'Number of Bathrooms' to 'Price'%n5 - 'Number of Parking Spaces' to 'Price'%n6 - Multilinear regression%n0 - Exit%n");
            int option = getOption();
            if(option != 0) {
                double[] listOfValues;
                if(option != 6) {
                    listOfValues = getDesiredLinearRegression(option);
                    doLinearRegression(listOfValues);
                } else {
                    listOfValues = ctrl.getMultipleLinearRegression();
                    doMultipleLinearRegression(listOfValues);
                }
                    boolean proceed = verifyInput.verifyBoolean(sc, "Do you desire to use this formula to predict property prices");
                    if (proceed) {
                        soldApartmentsOrHouses = askAboutFilters(soldApartmentsOrHouses);
                        soldApartmentsOrHouses = askAboutSortSetting(soldApartmentsOrHouses);
                        printPropertyList(soldApartmentsOrHouses, listOfValues);
                    }
                }
        } else {
            System.out.println("There are no sold properties to study yet.");
        }
        System.out.println("Press ENTER to continue.");
        sc.nextLine();
    }

    /**
     * This method is used to force the user to pick one of the possible options-
     * @return the valid option chosen by the user
     */
    private int getOption(){
        int option;
        boolean valid = false;
        VerifyInput verifyInput = new VerifyInput();
        do {
            option = verifyInput.verifyInt(sc, "your desired option");
            if (option >= 0 && option <= 6) {
                valid = true;
            } else {
                System.out.printf("%nInvalid number. Try again.%n");
                sc.nextLine();
            }
        } while (!valid);
        return option;
    }

    /**
     * This method is used to print a simple linear regression's equation
     * @param listOfValues the list of values (a, b, R and R^2)
     */
    private void doLinearRegression(double[] listOfValues){
        System.out.printf("price = %.2f * %s + %.2f%n", listOfValues[0], item, listOfValues[1]);
        //y = ax + b
        System.out.printf("R = %.2f, %s%nR^2 = %.2f, %s%n", listOfValues[2], ctrl.getRRating(listOfValues[2]), listOfValues[3], ctrl.getRRating(listOfValues[3]));
    }

    /**
     * This method is used to update the list of values with the supposed calculations
     * @param option the chosen simple linear regression
     * @return a, b, R and R^2
     */
    private double[] getDesiredLinearRegression(int option){
        double[] listOfValues = new double[4];
        //slope, intercept, r, r^2
        switch(option){
            case 1:
                listOfValues = ctrl.getAreaToPriceLinearRegression();
                item = AREA;
                break;
            case 2:
                listOfValues = ctrl.getDistanceToPriceLinearRegression();
                item = DISTANCE;
                break;
            case 3:
                listOfValues = ctrl.getNumOfBedroomsToPriceLinearRegression();
                item = NUMBEROFBEDROOMS;
                break;
            case 4:
                listOfValues = ctrl.getNumOfBathroomsToPriceLinearRegression();
                item = NUMBEROFBATHROOMS;
                break;
            case 5:
                listOfValues = ctrl.getNumOfParkingSpacesToPriceLinearRegression();
                item = NUMBEROFPARKINGSPACES;
                break;
        }
        return listOfValues;
    }

    /**
     * This method will print a multiple linear regression's equation
     * @param listOfValues b0, b1, ..., bn, R, R^2 and Adjusted R^2
     */
    private void doMultipleLinearRegression(double[] listOfValues){
        //x0, betaArea, betaDistance, betaBedrooms, betaBathrooms, betaParkingSpaces, r, r2, adjusted r2
        System.out.printf("price = %.2f * %s + %.2f * %s + %.2f * %s + %.2f * %s + %.2f * %s + %.2f%n", listOfValues[1], AREA, listOfValues[2], DISTANCE, listOfValues[3],
                NUMBEROFBEDROOMS, listOfValues[4], NUMBEROFBATHROOMS, listOfValues[5], NUMBEROFPARKINGSPACES, listOfValues[0]);
        //y = ax + b
        System.out.printf("R = %.2f, %s%nR^2 = %.2f, %s%nAdjusted R^2 = %.2f, %s%n", listOfValues[6], ctrl.getRRating(listOfValues[6]), listOfValues[7], ctrl.getRRating(listOfValues[7]), listOfValues[8], ctrl.getRRating(listOfValues[8]));
    }

    /**
     * This method is used to filter the list of properties as wished by the user
     * @param properties list of properties
     * @return filtered list of properties
     */
    public List<Property> askAboutFilters(List<Property> properties) {
        System.out.printf("Do you want to only see properties...%n1 - For Sale%n2 - For Rent%n3 - Both%n");
        properties = ctrl.askAboutTypeOfSale(properties, sc);
        System.out.printf("Do you want to only see...%n1 - Apartments%n2 - House%n3 - All of them");
        properties = ctrl.askAboutTypeOfProperty(properties, sc);
        System.out.printf("%nWhat agent you want to see properties from%n0 - Any agent%n");
        AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
        agentRepository.printAgents();
        properties = ctrl.askAboutAgent(properties, sc, agentRepository);
        return properties;
    }

    /**
     * This method is used to sort the list of properties as wished by the user
     * @param properties list of properties
     * @return sorted list of properties
     */
    public List<Property> askAboutSortSetting(List<Property> properties) {
        System.out.printf("%nDo you want to sort in ascending/descending order of..%n1 - Price%n2 - Area%n3 - Distance from city centre%n4 - Number of photographs" +
                "%n5 - Number of Bedrooms%n6 - Number of Bathrooms%n7 - Number of Parking Spaces%n8 - No specific sorting setting");
        int sortItem = ctrl.askAboutSortSetting(sc);
        if (sortItem != 8) {
            System.out.printf("%nIn what order?%n1 - Ascending%n2 - Descending");
            int sortOrder = ctrl.askAboutOrder(sc);
            properties = ctrl.sortList(properties, sortItem, sortOrder);
        }
        return properties;
    }

    /**
     * This method will print the list of deals, filtered and sorted just like the user asked. Each deal will also contain its calculated estimated price
     * @param propertyList the list of deals
     * @param data a, b, R and R^2 for simple linear regressions, b0, b1, ..., bn, R, R^2 and Adjusted R^2 for multiple linear regressions
     */
    public void printPropertyList(List<Property> propertyList, double[] data) {
        if (!propertyList.isEmpty()) {
            System.out.println();
            if(data.length == 4) {
                for (Property property : propertyList) {
                    double estimatedPrice = ctrl.calculateSimpleLinearPrice(data, item, property);
                    System.out.printf("%sEstimated Price: %.2f$ (%.2f€)%n%n", property, estimatedPrice, estimatedPrice / EUROTODOLLAR);
                }
            } else {
                for (Property property : propertyList) {
                    double estimatedPrice = ctrl.calculateMultipleLinearPrice(data, property);
                    System.out.printf("%sEstimated Price: %.2f$ (%.2f€)%n%n", property, estimatedPrice, estimatedPrice / EUROTODOLLAR);
                }
            }
        } else {
            System.out.println("There are no properties with the requested characteristics");
        }
    }
}
