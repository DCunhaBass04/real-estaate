package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.ListAllDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListAllDealsUIFX implements Initializable {
    private Scene preScene;
    private final ListAllDealsController controller = new ListAllDealsController();
    @FXML
    private Button mergeDescendingButton;

    @FXML
    private Button ascendingButton;

    @FXML
    private Button bubbleAscendingButton;

    @FXML
    private Button bubbleDescendingButton;

    @FXML
    private Button descendingButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button mergeAscendingButton;
    @FXML
    private TextArea areaText;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bubbleAscendingButton.setVisible(false);
        mergeAscendingButton.setVisible(false);
        bubbleDescendingButton.setVisible(false);
        mergeDescendingButton.setVisible(false);
    }
    @FXML
    void ascending(ActionEvent event) {
        bubbleAscendingButton.setVisible(true);
        mergeAscendingButton.setVisible(true);
        bubbleDescendingButton.setVisible(false);
        mergeDescendingButton.setVisible(false);
    }
    @FXML
    void descending(ActionEvent event) {
        bubbleAscendingButton.setVisible(false);
        mergeAscendingButton.setVisible(false);
        bubbleDescendingButton.setVisible(true);
        mergeDescendingButton.setVisible(true);
    }
    @FXML
    void bubbleAscending(ActionEvent event) {
        List<Property> properties = controller.getProperties();
        properties = controller.sortProperties(1, 1, properties);

        StringBuilder sb = new StringBuilder();
        for (Property property : properties) {
            sb.append(property.toString()).append("\n");
        }
        areaText.clear();
        areaText.setText(sb.toString());
    }
    @FXML
    void mergeAscending(ActionEvent event) {
        List<Property> properties = controller.getProperties();
        properties = controller.sortProperties(1, 2, properties);

        StringBuilder sb = new StringBuilder();
        for (Property property : properties) {
            sb.append(property.toString()).append("\n");
        }
        areaText.clear();
        areaText.setText(sb.toString());
    }
    @FXML
    void bubbleDescending(ActionEvent event) {
        List<Property> properties = controller.getProperties();
        properties = controller.sortProperties(2, 1, properties);

        StringBuilder sb = new StringBuilder();
        for (Property property : properties) {
            sb.append(property.toString()).append("\n");
        }
        areaText.clear();
        areaText.setText(sb.toString());
    }
    @FXML
    void mergeDescending(ActionEvent event) {
        List<Property> properties = controller.getProperties();
        properties = controller.sortProperties(2, 2, properties);

        StringBuilder sb = new StringBuilder();
        for (Property property : properties) {
            sb.append(property.toString()).append("\n");
        }
        areaText.clear();
        areaText.setText(sb.toString());
    }

    /**
     * This sends you to the previous menu
     * @param event
     */
    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }
    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }
}
