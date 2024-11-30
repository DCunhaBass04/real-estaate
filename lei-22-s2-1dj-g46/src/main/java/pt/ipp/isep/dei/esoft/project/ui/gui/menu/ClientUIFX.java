package pt.ipp.isep.dei.esoft.project.ui.gui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ReadResponseController;
import pt.ipp.isep.dei.esoft.project.domain.Response;
import pt.ipp.isep.dei.esoft.project.domain.Users.User;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.ReadResponseUIFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientUIFX implements Initializable, UserUIFX {

    /**
     * Method performed while initializing this UI
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String currentUserEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        email.setText(currentUserEmail);
        User currentUser = Repositories.getInstance().getUserRepository().getUserByEmail(currentUserEmail);
        placeHolderRole.setText("Client");
        name.setText(currentUser.getName());
    }
    @FXML
    private Button logoutButton;
    @FXML
    private Label placeHolderRole;
    @FXML
    private Label email;

    @FXML
    private Label name;
    private Scene preScene;
    @FXML
    private Button readResponsesButton;
    @FXML
    private Label labelNoResponses;
    private final ReadResponseController controller = new ReadResponseController();
    @Override
    public void setPreScene(Scene preScene){this.preScene = preScene;}
@FXML
    void readResponses(ActionEvent event) throws IOException {
        List<Response> responses = controller.getResponsesToClient();
        if(!responses.isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ReadResponse.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ReadResponseUIFX readResponseUIFX = fxmlLoader.getController();
            readResponseUIFX.setPreScene(readResponsesButton.getScene());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Read Response");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Responses");
            alert.setHeaderText(null);
            alert.setContentText("There aren't any responses in the system to this date");
            alert.showAndWait();
        }
    }
    @FXML
    void logout(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }
}
