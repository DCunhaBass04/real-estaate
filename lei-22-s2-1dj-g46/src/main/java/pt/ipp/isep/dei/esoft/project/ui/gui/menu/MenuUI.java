package pt.ipp.isep.dei.esoft.project.ui.gui.menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.authentication.LoginUI;

import java.io.IOException;

public class MenuUI {

    @FXML
    private Button developmentButton;

    @FXML
    private Label label1;

    @FXML
    private Button exit;

    @FXML
    private Button loginButton;

    /**
     * This option will terminate the program, saving the progress you may have done until this point
     * @param event the button click
     */
    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * This option will send you to the login menu
     * @param event the button click
     * @throws IOException if the '/fxml/Login.fxml' doesn't exist
     */
    @FXML
    void login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginUI loginUI = fxmlLoader.getController();
        loginUI.setPreScene(loginButton.getScene());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This option will generate an alert that will show information about the software's objective, as well as the team who developed it
     * @param event the button click
     */
    @FXML
    void showDevelopment(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Development Info");
        alert.setHeaderText("Information about Development");
        alert.setContentText("The application was developed for the purpose of the sale and rental of real estate.\nSuch a simple purpose however encompasses many different processes, such as allowing clients to book a visit to a property of their interest, or something more internal to the company, like making it so the system administrator can register employees in the system.\n\nDeveloped by:\n-> António Costa - 1221285@isep.ipp.pt \n-> Diogo Cunha - 1221071@isep.ipp.pt \n-> Tomás Peixoto - 1221948@isep.ipp.pt \n");

        alert.showAndWait();
    }

}