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
import pt.ipp.isep.dei.esoft.project.application.controller.ListAllDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Users.User;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.GetStoreSubsetsUIFX;
import pt.ipp.isep.dei.esoft.project.ui.gui.ListAllDealsUIFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NetworkManagerUIFX implements Initializable, UserUIFX {

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
        placeHolderRole.setText("Network Manager");
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
    private Button getSubsetsButton;
    @FXML
    private Button listAllDealsButton;
    private final ListAllDealsController controller = new ListAllDealsController();
    @Override
    public void setPreScene(Scene preScene){this.preScene = preScene;}
@FXML
    void getSubsets(ActionEvent event) throws IOException {
    List<Property> deals = Repositories.getInstance().getPropertySoldRepository().getProperties();
    if (!deals.isEmpty()) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GetStoreSubsets.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GetStoreSubsetsUIFX getStoreSubsetsUIFX = fxmlLoader.getController();
        getStoreSubsetsUIFX.setPreScene(getSubsetsButton.getScene());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Get Subsets");
        stage.setScene(scene);
        stage.show();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Not enough deals");
        alert.setHeaderText(null);
        alert.setContentText("There aren't enough deals in the system to do this study.");
        alert.showAndWait();
    }
    }
    @FXML
    void listAllDeals(ActionEvent event) throws IOException {
        List<Property> properties = controller.getProperties();
        if(!properties.isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ListAllDeals.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ListAllDealsUIFX listAllDealsUIFX = fxmlLoader.getController();
            listAllDealsUIFX.setPreScene(listAllDealsButton.getScene());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("List All Deals");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Properties");
            alert.setHeaderText(null);
            alert.setContentText("There are no properties in the system to this date.");
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
