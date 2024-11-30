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
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Users.User;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.gui.AnalyzeDealsUIFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StoreManagerUIFX implements Initializable, UserUIFX {

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
        placeHolderRole.setText("Store Manager");
        name.setText(currentUser.getName());
    }
    @FXML
    private Button analyzeDealsButton;
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

    @FXML
    void analyzeDeals(ActionEvent event) throws IOException {
        List<Property> listOfApartmentsAndHouses = Repositories.getInstance().getPropertySoldRepository().getProperties();
        EditAndSortLists editAndSortLists = new EditAndSortLists();
        listOfApartmentsAndHouses = editAndSortLists.getPropertiesIfTheyAreApartmentsOrHouses(listOfApartmentsAndHouses);
        if(listOfApartmentsAndHouses.size() > 2) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AnalyzeDeals.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AnalyzeDealsUIFX analyzeDealsUIFX = fxmlLoader.getController();
            analyzeDealsUIFX.setPreScene(analyzeDealsButton.getScene());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Analyze Deals");
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
    void logout(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }
}
