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
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.Users.User;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.BookingsUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class AgentUIFX implements Initializable, UserUIFX {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageList = Repositories.getInstance().getMessageRepository().getMessages();
        String currentUserEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        email.setText(currentUserEmail);
        User currentUser = Repositories.getInstance().getUserRepository().getUserByEmail(currentUserEmail);
        placeHolderRole.setText("Agent");
        name.setText(currentUser.getName());
    }
    @FXML
    private Button bookingRequestsButton;

    private List<Message> messageList;


    @FXML
    void bookingRequests(ActionEvent event) throws IOException {
        if (messageList.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Couldn't fulfill request.");
            alert.setHeaderText(null);
            alert.setContentText("There are no booking requests available.");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BookingDates.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            BookingsUI bookingsUI = fxmlLoader.getController();
            bookingsUI.setPreScene(bookingRequestsButton.getScene());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("See Booking Requests");
            stage.setScene(scene);
            stage.show();
        }
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
    @Override
    public void setPreScene(Scene preScene){this.preScene = preScene;}

    /**
     *When you click this option, you will log out, returning to the main menu
     *@param event the button click
     */
    @FXML
    void logout(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }
}
