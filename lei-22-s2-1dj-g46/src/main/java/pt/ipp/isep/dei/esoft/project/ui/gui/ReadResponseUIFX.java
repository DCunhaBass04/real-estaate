package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ReadResponseController;
import pt.ipp.isep.dei.esoft.project.domain.Response;

public class ReadResponseUIFX implements Initializable {
    private Scene preScene;
    @FXML
    private Button listResponsesButton;
    @FXML
    private TextArea listResponsesText;
    @FXML
    private Button exit;
    @FXML
    private ListView<Response> printResponses;
    @FXML
    private Button selectResponse;
    private final ReadResponseController controller = new ReadResponseController();

    /**
     * This method initializes the scene, setting the selectResponse button as invisible
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectResponse.setVisible(false);
    }

    /**
     * This method lists all responses into the list view
     */
    public void listResponses() {
        List<Response> responses = controller.getResponsesToClient();

        ObservableList<Response> observableResponses = FXCollections.observableArrayList(responses);
        printResponses.setItems(observableResponses);
        selectResponse.setVisible(true);
        selectResponse.disableProperty().bind(printResponses.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * This method picks the selected response and sends it to the next scene
     * @param event
     * @throws IOException if the next scene is not found
     */
    @FXML
    public void acceptOrDeclineResponse(ActionEvent event) throws IOException {
        Response selectedResponse = printResponses.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AcceptOrDeclineResponse.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AcceptOrDeclineResponseUIFX acceptOrDeclineResponseUIFX = fxmlLoader.getController();
        acceptOrDeclineResponseUIFX.setPreScene(selectResponse.getScene());
        acceptOrDeclineResponseUIFX.setPrePreScene(preScene);
        acceptOrDeclineResponseUIFX.setSelectedResponse(selectedResponse);
        acceptOrDeclineResponseUIFX.displaySelectedResponse();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("AcceptOrDeclineResponse");
        stage.setScene(scene);
        stage.show();
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

