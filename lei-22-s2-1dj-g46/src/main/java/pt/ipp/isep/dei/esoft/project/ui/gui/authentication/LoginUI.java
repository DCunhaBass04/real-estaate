package pt.ipp.isep.dei.esoft.project.ui.gui.authentication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.AgentUIFX;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.ClientUIFX;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.MenuItemFX;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.NetworkManagerUIFX;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.StoreManagerUIFX;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LoginUI {
    private Scene preScene;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button login;
    @FXML
    private Button back;

    /**
     * This method is used to go to the previous menu
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

    /**
     * This method is used to login the user to their account, checking the credentials
     * @param event
     */
    @FXML
    void doLogin(ActionEvent event) {
        AuthenticationController ctrl = new AuthenticationController();
        boolean success = ctrl.doLogin(email.getText(), pwd.getText());
        if (!success){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong credentials");
            alert.setHeaderText(null);
            alert.setContentText("Invalid UserId and/or Password.");
            alert.showAndWait();
        } else {
            List<UserRoleDTO> roles = ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Role not found");
                alert.setHeaderText(null);
                alert.setContentText("No role assigned to user.");
                alert.showAndWait();
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItemFX> rolesUI = getMenuItemForRoles();
                    redirectToRoleUI(rolesUI, role, event);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Role not found");
                    alert.setHeaderText(null);
                    alert.setContentText("No role selected.");
                }
            }
        }
    }

    /**
     * This method creates the UI for each user in JavaFX
     * @return all users' UIs
     */
    private List<MenuItemFX> getMenuItemForRoles() {
        List<MenuItemFX> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItemFX(AuthenticationController.ROLE_AGENT, new AgentUIFX(), preScene, "/fxml/Agent.fxml"));
        rolesUI.add(new MenuItemFX(AuthenticationController.ROLE_OWNER, new ClientUIFX(), preScene, "/fxml/Client.fxml"));
        rolesUI.add(new MenuItemFX(AuthenticationController.ROLE_MANAGER, new NetworkManagerUIFX(), preScene, "/fxml/NetworkManager.fxml"));
        rolesUI.add(new MenuItemFX(AuthenticationController.ROLE_STORE_MANAGER, new StoreManagerUIFX(), preScene, "/fxml/StoreManager.fxml"));

        return rolesUI;
    }

    /**
     * This method is used to redirect the user to their respective UI
     * @param rolesUI the UI for every role
     * @param role the current user's role
     * @param event
     */
    private void redirectToRoleUI(List<MenuItemFX> rolesUI, UserRoleDTO role, ActionEvent event) {
        boolean found = false;
        Iterator<MenuItemFX> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItemFX item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                try {
                    item.run(event);
                } catch (IOException e){
                    found = false;
                }
            }
        }
        if (!found) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("UI not found");
            alert.setHeaderText(null);
            alert.setContentText("No UI found for this role.");
            alert.showAndWait();
        }
    }

    /**
     * This method gets the current user's role. If they have multiple roles, the method asks what role they want this time
     * @param roles list of possible roles
     * @return specific user's role
     */
    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }
}
