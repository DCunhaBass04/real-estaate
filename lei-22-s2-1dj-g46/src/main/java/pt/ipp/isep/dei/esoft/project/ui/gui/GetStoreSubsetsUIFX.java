package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSubsetsOfStoresController;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.domain.Tuple;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GetStoreSubsetsUIFX implements Initializable {
    @FXML
    private Label executionTimeLabel;
    private Scene preScene;
    @FXML
    private Button exit;

    @FXML
    private TextArea partition1TextArea;

    @FXML
    private TextArea partition2TextArea;

    @FXML
    private Label propertyDifferenceLabel;

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }
    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    /**
     * This method will print the partitions calculated in the controller, as well as showing the execution time and difference in total properties between partitions
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Store> stores = Repositories.getInstance().getStoreRepository().getStores();
        CreateSubsetsOfStoresController ctrl = new CreateSubsetsOfStoresController();
        long startTime = System.currentTimeMillis();
        List<List<Tuple>> partitions = ctrl.generatePartitions(stores);
        long endTime = System.currentTimeMillis();
        executionTimeLabel.setText("Execution time in milliseconds: " + (endTime-startTime));
        StringBuilder stringBuilder = new StringBuilder();
        for (Tuple tuple : partitions.get(0)) {
            stringBuilder.append(String.format("%s%n",tuple));
        }
        stringBuilder.append(String.format("%n"));
        stringBuilder.append(String.format("Total number of properties: %d", ctrl.getTotalProperties(partitions.get(0))));
        partition1TextArea.setText(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        for (Tuple tuple : partitions.get(1)) {
            stringBuilder.append(String.format("%s%n",tuple));
        }
        stringBuilder.append(String.format("%n"));
        stringBuilder.append(String.format("Total number of properties: %d", ctrl.getTotalProperties(partitions.get(1))));
        partition2TextArea.setText(stringBuilder.toString());
        int difference = Math.abs(ctrl.getTotalProperties(partitions.get(0)) - ctrl.getTotalProperties(partitions.get(1)));
        String propertyOrProperties = "properties";
        if (difference == 1) {
            propertyOrProperties = "property";
        }
        propertyDifferenceLabel.setText(String.format("Difference of Properties between partitions: %d %s.%n", difference, propertyOrProperties));
    }
}

