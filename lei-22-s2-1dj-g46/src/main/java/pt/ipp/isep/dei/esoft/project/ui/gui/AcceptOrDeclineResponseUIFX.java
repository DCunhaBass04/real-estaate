package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import pt.ipp.isep.dei.esoft.project.application.controller.ReadResponseController;
import pt.ipp.isep.dei.esoft.project.domain.Response;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceptOrDeclineResponseUIFX implements Initializable {
    private Scene preScene;
    private Response selectedResponse;
    private final ReadResponseController controller = new ReadResponseController();
    @FXML
    private Button acceptButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Button declineButton;
    @FXML
    private Button exit;
    @FXML
    private TextArea responseTextArea;
    @FXML
    private TextArea reasonTextArea;
    private Scene prePreScene;

    /**
     * This method is used to set some parts of the fxml as invisible.
     * They will be visible again after a certain action from the user
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displaySelectedResponse();
        confirmButton.setVisible(false);
        reasonTextArea.setVisible(false);
        reasonTextArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String reason = reasonTextArea.getText();
                controller.changeResponseWithReason(selectedResponse, reason);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(prePreScene);
                stage.show();
            }
        });
    }

    public void setSelectedResponse(Response selectedResponse) {
        this.selectedResponse = selectedResponse;
    }
    public void displaySelectedResponse() {
        if (selectedResponse != null) {
            responseTextArea.setText(selectedResponse.toString());
        } else {
            responseTextArea.setText("No response selected.");
        }
    }

    /**
     * This method sets the Confirm button as visible, and the Decline Reason text area as invisible
     * @param event
     */
    @FXML
    void accept(ActionEvent event) {
        confirmButton.setVisible(true);
        reasonTextArea.setVisible(false);
    }

    /**
     * This method sets the Decline Reason text area as visible, and the Confirm button as invisible
     * @param event
     */
    @FXML
    void decline(ActionEvent event) {
        reasonTextArea.setVisible(true);
        confirmButton.setVisible(false);
    }

    /**
     * This method sets the state of the selected response as 'Accepted' and sends you back to the Client menu
     * @param event
     */
    @FXML
    void confirm(ActionEvent event) {
        controller.changeResponse(selectedResponse);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(prePreScene);
        stage.show();
    }

    /**
     * This method sends you back to the previous menu
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
    public void setPrePreScene(Scene preScene) {
        this.prePreScene = preScene;
    }
}
