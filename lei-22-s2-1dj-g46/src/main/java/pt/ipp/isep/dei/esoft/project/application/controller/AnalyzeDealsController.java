package pt.ipp.isep.dei.esoft.project.application.controller;

import org.apache.commons.math4.legacy.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math4.legacy.stat.regression.SimpleRegression;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.AgentRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertySoldRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;

public class AnalyzeDealsController {
    private OLSMultipleLinearRegression olsMultipleLinearRegression = new OLSMultipleLinearRegression();
    private final int NUMBEROFPARAMETERS = 5;
    private final float KMTOMILE = 0.62137f;
    private final float EUROTODOLLAR = 1.07f;
    private final float METER2TOFEET2 = 10.7639f;
    private final String AREA = "area";
    private final String DISTANCE = "distance from city centre";
    private final String NUMBEROFBEDROOMS = "bedrooms";
    private final String NUMBEROFBATHROOMS = "bathrooms";
    private final String NUMBEROFPARKINGSPACES = "parking spaces";

    /**
     * From the list of all properties, this one gets only apartments and houses (lands can't be used in this study)
     * @return list of apartments and houses
     */
    public List<Property> getApartmentsOrHouses(){
        EditAndSortLists editAndSortLists = new EditAndSortLists();
        return editAndSortLists.getPropertiesIfTheyAreApartmentsOrHouses(Repositories.getInstance().getPropertySoldRepository().getProperties());
    }

    /**
     * This one gets the parameters for the 'Area' to 'Price' Simple Linear Regression
     * @return a, b, R and R^2
     */
    public double[] getAreaToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getAreaArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
    /**
     * This one gets the parameters for the 'Distance' to 'Price' Simple Linear Regression
     * @return a, b, R and R^2
     */
    public double[] getDistanceToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getDistanceArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
    /**
     * This one gets the parameters for the 'Bedrooms' to 'Price' Simple Linear Regression
     * @return a, b, R and R^2
     */
    public double[] getNumOfBedroomsToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getBedroomArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
    /**
     * This one gets the parameters for the 'Bathrooms' to 'Price' Simple Linear Regression
     * @return a, b, R and R^2
     */
    public double[] getNumOfBathroomsToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getBathroomsArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
    /**
     * This one gets the parameters for the 'Parking Spaces' to 'Price' Simple Linear Regression
     * @return a, b, R and R^2
     */
    public double[] getNumOfParkingSpacesToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getParkingSpacesArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
    /**
     * This method is used to get the array of all prices
     * @return price array
     */
    public double[] getPriceArray(List<Property> properties){
        double[] prices = new double[properties.size()];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = properties.get(i).getFinalPrice() * EUROTODOLLAR;
        }
        return prices;
    }
    /**
     * This method is used to get the array of all areas
     * @return area array
     */
    public double[] getAreaArray(List<Property> properties){
        double[] areas = new double[properties.size()];
        for (int i = 0; i < areas.length; i++) {
            areas[i] = properties.get(i).getArea() * METER2TOFEET2;
        }
        return areas;
    }
    /**
     * This method is used to get the array of all distance
     * @return distance array
     */
    public double[] getDistanceArray(List<Property> properties){
        double[] distances = new double[properties.size()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = properties.get(i).getDistance() * KMTOMILE;
        }
        return distances;
    }
    /**
     * This method is used to get the array of all number of bedrooms
     * @return bedroom array
     */
    public double[] getBedroomArray(List<Property> properties){
        double[] bedrooms = new double[properties.size()];
        for (int i = 0; i < bedrooms.length; i++) {
            bedrooms[i] = properties.get(i).getNumOfBedrooms();
        }
        return bedrooms;
    }
    /**
     * This method is used to get the array of all number of bathrooms
     * @return bathroom array
     */
    public double[] getBathroomsArray(List<Property> properties){
        double[] bathrooms = new double[properties.size()];
        for (int i = 0; i < bathrooms.length; i++) {
            bathrooms[i] = properties.get(i).getNumOfBathrooms();
        }
        return bathrooms;
    }
    /**
     * This method is used to get the array of all number of bedrooms
     * @return bedroom array
     */
    public double[] getParkingSpacesArray(List<Property> properties){
        double[] parkingSpaces = new double[properties.size()];
        for (int i = 0; i < parkingSpaces.length; i++) {
            parkingSpaces[i] = properties.get(i).getNumOfParkingSpaces();
        }
        return parkingSpaces;
    }

    /**
     * This method is used to get the data for a simple linear regression equation
     * @param x x array
     * @param y y array
     * @return the list of values needed for the equation
     */
    public double[] getEquation(double[] x, double[] y){
        SimpleRegression simpleRegression = new SimpleRegression();
        for (int i = 0; i < x.length; i++) {
            simpleRegression.addData(x[i], y[i]);
        }
        return new double[]{simpleRegression.getSlope(), simpleRegression.getIntercept(), simpleRegression.getR(), simpleRegression.getRSquare()};
    }

    /**
     * This method is used to get the data for a multiple linear regression equation
     * @return the list of values needed for the equation
     */
    public double[] getMultipleLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        double[] y = getPriceArray(apartmentsAndHouses);
        double[][] x = getX(apartmentsAndHouses);
        olsMultipleLinearRegression.newSampleData(y, x);
        double[] parameters = olsMultipleLinearRegression.estimateRegressionParameters();
        double rSquare = olsMultipleLinearRegression.calculateRSquared();
        double r = Math.sqrt(rSquare);
        double adjustedRSquare = olsMultipleLinearRegression.calculateAdjustedRSquared();
        return new double[]{parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], r, rSquare, adjustedRSquare};
    }

    /**
     * This method is used to get a matrix of all the areas, distances, bedrooms, bathrooms and parking spaces
     * @param properties list of properties
     * @return the matrix with all needed data
     */
    private double[][] getX(List<Property> properties){
        double[][] x = new double[properties.size()][NUMBEROFPARAMETERS];
        for (int i = 0; i < x.length; i++) {
            x[i][0] = properties.get(i).getArea() * METER2TOFEET2;
            x[i][1] = properties.get(i).getDistance() * KMTOMILE;
            x[i][2] = properties.get(i).getNumOfBedrooms();
            x[i][3] = properties.get(i).getNumOfBathrooms();
            x[i][4] = properties.get(i).getNumOfParkingSpaces();
        }
        return x;
    }

    /**
     * This method receives a number from 0 to 1 and rates it
     * @param r r value
     * @return the rating
     */
    public String getRRating(double r){
        String rating = "";
        if(r <= 0.25){
            rating = "Very Unreliable";
        }else if(r <= 0.50){
            rating = "Unreliable";
        }else if(r <= 0.70){
            rating = "Reliable";
        }else if(r <= 0.90){
            rating = "Very Reliable";
        }else{
            rating = "Extremely Reliable";
        }
        return rating;
    }

    /**
     * This is used to filter (or not) the property list with only 'for rent' or 'for sale' properties
     * @param properties list of properties
     * @param sc scanner
     * @return filtered list
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
    /**
     * This is used to filter (or not) the property list with only apartments or houses
     * @param properties list of properties
     * @param sc scanner
     * @return filtered list
     */
    public List<Property> askAboutTypeOfProperty(List<Property> properties, Scanner sc){
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do{
            option = verifyInput.verifyInt(sc, "Type of Property");
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
                    properties = editAndSortLists.getPropertiesIfTheyAreApartment(properties);
                    break;
                case 2:
                    properties = editAndSortLists.getPropertiesIfTheyAreHouse(properties);
                    break;
            }
        }
        return properties;
    }
    /**
     * This is used to filter (or not) the property list with only properties from a certain agent
     * @param properties list of properties
     * @param sc scanner
     * @param agentList list of all agents
     * @return filtered list
     */
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

    /**
     * This method is used to get the sorting criteria the user desires
     * @param sc scanner
     * @return the sorting criteria
     */
    public int askAboutSortSetting(Scanner sc) {
        boolean valid = false;
        int option;
        VerifyInput verifyInput = new VerifyInput();
        do{
            option = verifyInput.verifyInt(sc, "Sort Value");
            if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6 || option == 7 || option == 8){
                valid = true;
            } else {
                System.out.println("Please insert a valid number.");
            }
        }while(!valid);
        return option;
    }

    /**
     * After getting the sorting criteria, this method is used to get the sorting order as the user desires
     * @param sc scanner
     * @return the sorting order
     */
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

    /**
     * After getting the sorting criteria and order, this method will sort the list according to those previous items
     * @param properties list of properties
     * @param sortItem sorting criteria
     * @param sortOrder sorting order
     * @return sorted property list
     */
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
                    case 5:
                        properties = editAndSortLists.sortPropertiesByAscendingNumOfBedrooms(properties);
                        break;
                    case 6:
                        properties = editAndSortLists.sortPropertiesByAscendingNumOfBathrooms(properties);
                        break;
                    case 7:
                        properties = editAndSortLists.sortPropertiesByAscendingNumOfParkingSpaces(properties);
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
                    case 5:
                        properties = editAndSortLists.sortPropertiesByDescendingNumOfBedrooms(properties);
                        break;
                    case 6:
                        properties = editAndSortLists.sortPropertiesByDescendingNumOfBathrooms(properties);
                        break;
                    case 7:
                        properties = editAndSortLists.sortPropertiesByDescendingNumOfParkingSpaces(properties);
                        break;
                }
                break;
        }
        return properties;
    }

    /**
     * This method uses the y = ax + b formula to calculate the estimated price of a simple linear regression from a certain X item
     * @param data the list of simple linear regression values
     * @param item the X item
     * @param property the property we want the estimated price for
     * @return the calculated price
     */
    public double calculateSimpleLinearPrice(double[] data, String item, Property property) {
        double x = getX(item, property);
        return (data[0] * x + data[1]);
        //y = a * x + b
    }

    /**
     * This method uses the y = b0 + b1x1 + ... + bnxn formula to calculate the estimated price of the multiple linear regression
     * @param data the list of multiple linear regression values
     * @param property the property we want the estimated price for
     * @return the calculated price
     */
    public double calculateMultipleLinearPrice(double[] data, Property property){
        return (data[1] * (property.getArea() * METER2TOFEET2)) + (data[2] * (property.getDistance() * KMTOMILE)) + (data[3] * property.getNumOfBedrooms()) + (data[4] * property.getNumOfBathrooms()) + (data[5] * property.getNumOfParkingSpaces()) + data[0];
    }

    /**
     * This method is used to get a certain X value of a certain property
     * @param item the desired X
     * @param property the property we want that X value of
     * @return the value
     */
    private double getX(String item, Property property){
        double value = 0;
        switch (item){
            case AREA:
                value = property.getArea() * METER2TOFEET2;
                break;
            case DISTANCE:
                value = property.getDistance() * KMTOMILE;
                break;
            case NUMBEROFBEDROOMS:
                value = property.getNumOfBedrooms();
                break;
            case NUMBEROFBATHROOMS:
                value = property.getNumOfBathrooms();
                break;
            case NUMBEROFPARKINGSPACES:
                value = property.getNumOfParkingSpaces();
                break;
        }
        return value;
    }
}