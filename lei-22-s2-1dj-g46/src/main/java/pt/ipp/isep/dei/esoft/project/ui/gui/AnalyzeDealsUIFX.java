package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AnalyzeDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnalyzeDealsUIFX implements Initializable {
    private EditAndSortLists listEditor = new EditAndSortLists();
    private final AnalyzeDealsController ctrl = new AnalyzeDealsController();
    private final float EUROTODOLLAR = 1.07f;
    private double[] listOfValues;
    private final String AREA = "area";
    private final String DISTANCE = "distance from city centre";
    private final String NUMBEROFBEDROOMS = "bedrooms";
    private final String NUMBEROFBATHROOMS = "bathrooms";
    private final String NUMBEROFPARKINGSPACES = "parking spaces";

    /**
     * This method is used to fill the choice boxes upon opening this menu
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(regressionModels);
        propertyFilterChoiceBox.getItems().addAll(propertyTypes);
        saleFilterChoiceBox.getItems().addAll(saleTypes);
        sortingCriteriaChoiceBox.getItems().addAll(sortingCriterias);
        storeFilterChoiceBox.getItems().addAll(getStoreNames());
        storeFilterChoiceBox.getItems().add("Any Store");
        choiceBox.setOnAction(this::getRegressionModelEquation);
    }

    /**
     * This method returns a list of all store names currently registered in the system
     * @return list of store names
     */
    private String[] getStoreNames(){
        List<Store> stores = Repositories.getInstance().getStoreRepository().getStores();
        String[] storeNames = new String[stores.size()];
        for (int i = 0; i < storeNames.length; i++) {
            storeNames[i] = stores.get(i).getStoreName();
        }
        return storeNames;
    }

    /**
     * This method sets the R text fields according to the chosen linear regression
     * @param actionEvent
     */
    private void getRegressionModelEquation(ActionEvent actionEvent) {
        switch (choiceBox.getValue()){
            case "'Area' to 'Price'":
                listOfValues = ctrl.getAreaToPriceLinearRegression();
                regressionModelTextField.setText(String.format("price = %.2f * %s + %.2f%n", listOfValues[0], AREA, listOfValues[1]));
                rTextField.setText(String.format("R = %.2f, %s%n", listOfValues[2], ctrl.getRRating(listOfValues[2])));
                rSquaredTextField.setText(String.format("R^2 = %.2f, %s%n", listOfValues[3], ctrl.getRRating(listOfValues[3])));
                adjustedRSquareTextField.setText("n/a");
                break;
            case "'Distance from city centre' to 'Price'":
                listOfValues = ctrl.getDistanceToPriceLinearRegression();
                regressionModelTextField.setText(String.format("price = %.2f * %s + %.2f%n", listOfValues[0], DISTANCE, listOfValues[1]));
                rTextField.setText(String.format("R = %.2f, %s%n", listOfValues[2], ctrl.getRRating(listOfValues[2])));
                rSquaredTextField.setText(String.format("R^2 = %.2f, %s%n", listOfValues[3], ctrl.getRRating(listOfValues[3])));
                adjustedRSquareTextField.setText("n/a");
                break;
            case "'Number of Bedrooms' to 'Price'":
                listOfValues = ctrl.getNumOfBedroomsToPriceLinearRegression();
                regressionModelTextField.setText(String.format("price = %.2f * %s + %.2f%n", listOfValues[0], NUMBEROFBEDROOMS, listOfValues[1]));
                rTextField.setText(String.format("R = %.2f, %s%n", listOfValues[2], ctrl.getRRating(listOfValues[2])));
                rSquaredTextField.setText(String.format("R^2 = %.2f, %s%n", listOfValues[3], ctrl.getRRating(listOfValues[3])));
                adjustedRSquareTextField.setText("n/a");
                break;
            case "'Number of Bathrooms' to 'Price'":
                listOfValues = ctrl.getNumOfBathroomsToPriceLinearRegression();
                regressionModelTextField.setText(String.format("price = %.2f * %s + %.2f%n", listOfValues[0], NUMBEROFBATHROOMS, listOfValues[1]));
                rTextField.setText(String.format("R = %.2f, %s%n", listOfValues[2], ctrl.getRRating(listOfValues[2])));
                rSquaredTextField.setText(String.format("R^2 = %.2f, %s%n", listOfValues[3], ctrl.getRRating(listOfValues[3])));
                adjustedRSquareTextField.setText("n/a");
                break;
            case "'Number of Parking Spaces' to 'Price'":
                listOfValues = ctrl.getNumOfParkingSpacesToPriceLinearRegression();
                regressionModelTextField.setText(String.format("price = %.2f * %s + %.2f%n", listOfValues[0], NUMBEROFPARKINGSPACES, listOfValues[1]));
                rTextField.setText(String.format("R = %.2f, %s%n", listOfValues[2], ctrl.getRRating(listOfValues[2])));
                rSquaredTextField.setText(String.format("R^2 = %.2f, %s%n", listOfValues[3], ctrl.getRRating(listOfValues[3])));
                adjustedRSquareTextField.setText("n/a");
                break;
            case "Multilinear regression":
                listOfValues = ctrl.getMultipleLinearRegression();
                regressionModelTextField.setText(String.format("price = %.2f * %s + %.2f * %s + %.2f * %s + %.2f * %s + %.2f * %s + %.2f%n", listOfValues[1], AREA, listOfValues[2], DISTANCE,
                        listOfValues[3], NUMBEROFBEDROOMS, listOfValues[4], NUMBEROFBATHROOMS, listOfValues[5], NUMBEROFPARKINGSPACES, listOfValues[0]));
                rTextField.setText(String.format("R = %.2f, %s%n", listOfValues[6], ctrl.getRRating(listOfValues[6])));
                rSquaredTextField.setText(String.format("R^2 = %.2f, %s%n", listOfValues[7], ctrl.getRRating(listOfValues[7])));
                adjustedRSquareTextField.setText(String.format("Adjusted R^2 = %.2f, %s%n", listOfValues[8], ctrl.getRRating(listOfValues[8])));
                break;
        }
    }

    private Scene preScene;
    @FXML
    private TextField adjustedRSquareTextField;
    @FXML
    private TextField rSquaredTextField;

    @FXML
    private TextField rTextField;
    @FXML
    private TextField regressionModelTextField;
    @FXML
    private Button exitButton;
    @FXML
    private ChoiceBox<String> storeFilterChoiceBox;
    @FXML
    private ChoiceBox<String> choiceBox;
    private String[] regressionModels = {"'Area' to 'Price'", "'Distance from city centre' to 'Price'", "'Number of Bedrooms' to 'Price'", "'Number of Bathrooms' to 'Price'", "'Number of Parking Spaces' to 'Price'", "Multilinear regression"};
    @FXML
    private ChoiceBox<String> saleFilterChoiceBox;
    private String[] saleTypes = {"For rent", "For sale", "Both"};
    @FXML
    private ChoiceBox<String> sortingCriteriaChoiceBox;
    private String[] sortingCriterias = {"Ascending Order of Area", "Descending Order of Area", "Ascending Order of Distance from City Centre", "Descending Order of Distance from City Centre", "Ascending Order of Bedrooms", "Descending Order of Bedrooms", "Ascending Order of Bathrooms", "Descending Order of Bathrooms", "Ascending Order of Parking Spaces", "Descending Order of Parking Spaces", "No Sorting Criteria"};
    @FXML
    private ChoiceBox<String> propertyFilterChoiceBox;
    private String[] propertyTypes = {"Apartment", "House", "Both"};
    @FXML
    private TextArea propertyListTextArea;
    @FXML
    private Button calculateButton;

    /**
     * This method is used to calculate estimated prices using the formula obtained by the chosen linear regression
     * Then, it prints the list of properties (sorted and filtered as the user requested)
     * @param event
     */
    @FXML
    void calculateEstimatedPrices(ActionEvent event) {
        propertyListTextArea.clear();
        List<Property> properties = goThroughFilters();
        properties = goThroughSorts(properties);
        String choice = choiceBox.getValue();
        if(choice != null){
        switch (choice) {
            case "'Area' to 'Price'":
                printSimpleLinearEstimatedPrices(properties, AREA);
                break;
            case "'Distance from city centre' to 'Price'":
                printSimpleLinearEstimatedPrices(properties, DISTANCE);
                break;
            case "'Number of Bedrooms' to 'Price'":
                printSimpleLinearEstimatedPrices(properties, NUMBEROFBEDROOMS);
                break;
            case "'Number of Bathrooms' to 'Price'":
                printSimpleLinearEstimatedPrices(properties, NUMBEROFBATHROOMS);
                break;
            case "'Number of Parking Spaces' to 'Price'":
                printSimpleLinearEstimatedPrices(properties, NUMBEROFPARKINGSPACES);
                break;
            case "Multilinear regression":
                printMultipleLinearEstimatedPrices(properties);
                break;
            }
        } else {
            propertyListTextArea.setText("Please select a Regression Model");
        }
    }

    /**
     * This method checks what sorting method the user chose and sorts the list
     * @param properties list of properties
     * @return the sorted list
     */
    private List<Property> goThroughSorts(List<Property> properties) {
        switch (sortingCriteriaChoiceBox.getValue()) {
            case "Ascending Order of Area":
                properties = listEditor.sortPropertiesByAscendingArea(properties);
                break;
            case "Descending Order of Area":
                properties = listEditor.sortPropertiesByDescendingArea(properties);
                break;
            case "Ascending Order of Distance from City Centre":
                properties = listEditor.sortPropertiesByAscendingDistance(properties);
                break;
            case "Descending Order of Distance from City Centre":
                properties = listEditor.sortPropertiesByDescendingDistance(properties);
                break;
            case "Ascending Order of Bedrooms":
                properties = listEditor.sortPropertiesByAscendingNumOfBedrooms(properties);
                break;
            case "Descending Order of Bedrooms":
                properties = listEditor.sortPropertiesByDescendingNumOfBedrooms(properties);
                break;
            case "Ascending Order of Bathrooms":
                properties = listEditor.sortPropertiesByAscendingNumOfBathrooms(properties);
                break;
            case "Descending Order of Bathrooms":
                properties = listEditor.sortPropertiesByDescendingNumOfBathrooms(properties);
                break;
            case "Ascending Order of Parking Spaces":
                properties = listEditor.sortPropertiesByAscendingNumOfParkingSpaces(properties);
                break;
            case "Descending Order of Parking Spaces":
                properties = listEditor.sortPropertiesByDescendingNumOfParkingSpaces(properties);
                break;
        }
        return properties;
    }

    /**
     * This method checks all filter choice boxes to see if there is anything to filter
     * @return the filtered list
     */
    private List<Property> goThroughFilters(){
        List<Property> properties = Repositories.getInstance().getPropertySoldRepository().getProperties();
        properties = listEditor.getPropertiesIfTheyAreApartmentsOrHouses(properties);
        properties = filterTypeOfProperty(properties);
        properties = filterTypeOfSale(properties);
        properties = filterStore(properties);
        return properties;
    }

    /**
     * This method checks if it needs to filter the list according to property type.
     * If it is needed, it filters the list as requested
     * @param properties list of properties
     * @return filtered list of properties
     */
    private List<Property> filterTypeOfProperty(List<Property> properties){
        String propertyFilter = propertyFilterChoiceBox.getValue();
        if(propertyFilter != null) {
            switch (propertyFilterChoiceBox.getValue()) {
                case "Apartment":
                    properties = listEditor.getPropertiesIfTheyAreApartment(properties);
                    break;
                case "House":
                    properties = listEditor.getPropertiesIfTheyAreHouse(properties);
                    break;
            }
        }
        return properties;
    }
    /**
     * This method checks if it needs to filter the list according to sale type.
     * If it is needed, it filters the list as requested
     * @param properties list of properties
     * @return filtered list of properties
     */
    private List<Property> filterTypeOfSale(List<Property> properties){
        String saleFilter = saleFilterChoiceBox.getValue();
        if(saleFilter != null) {
            switch (saleFilterChoiceBox.getValue()) {
                case "For rent":
                    properties = listEditor.getPropertiesWithXTypeOfSale(properties, "for rent");
                    break;
                case "For sale":
                    properties = listEditor.getPropertiesWithXTypeOfSale(properties, "for sale");
                    break;
            }
        }
        return properties;
    }
    /**
     * This method checks if it needs to filter the list according to the store responsible for the stores.
     * If it is needed, it filters the list as requested
     * @param properties list of properties
     * @return filtered list of properties
     */
    private List<Property> filterStore(List<Property> properties){
        String storeFilter = storeFilterChoiceBox.getValue();
        if(storeFilter != null) {
            if (!storeFilter.equals("Any Store")) {
                String storeEmail = Repositories.getInstance().getStoreRepository().getStoreByName(storeFilter).getEmail();
                properties = listEditor.getPropertiesByXStore(properties, storeEmail);
            }
        }
        return properties;
    }

    /**
     * This method prints the list, as well as the estimated prices (according to the item they chose for the simple linear regression model)
     * @param properties property list
     * @param type the X item for the simple regression model
     */
    private void printSimpleLinearEstimatedPrices(List<Property> properties, String type){
        if(!properties.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Property property : properties) {
                double estimatedPrice = ctrl.calculateSimpleLinearPrice(listOfValues, type, property);
                stringBuilder.append(String.format("%sEstimated Price: %.2f$ (%.2f€)%n%n%n", property, estimatedPrice, estimatedPrice / EUROTODOLLAR));
            }
            propertyListTextArea.setText(stringBuilder.toString());
        } else {
            propertyListTextArea.setText("There are no properties with the given filters");
        }
    }
    /**
     * This method prints the list, as well as the estimated prices
     * @param properties property list
     */
    private void printMultipleLinearEstimatedPrices(List<Property> properties){
        if(!properties.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Property property : properties) {
                double estimatedPrice = ctrl.calculateMultipleLinearPrice(listOfValues, property);
                stringBuilder.append(String.format("%sEstimated Price: %.2f$ (%.2f€)%n%n%n", property, estimatedPrice, estimatedPrice / EUROTODOLLAR));
            }
            propertyListTextArea.setText(stringBuilder.toString());
        } else {
            propertyListTextArea.setText("There are no properties with the given filters");
        }
    }

    /**
     * This method is used to quit this menu
     * @param event
     */
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }
    public void setPreScene(Scene preScene) {this.preScene = preScene;}
}
