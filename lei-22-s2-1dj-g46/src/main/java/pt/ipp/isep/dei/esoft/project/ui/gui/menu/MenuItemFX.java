package pt.ipp.isep.dei.esoft.project.ui.gui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

public class MenuItemFX {
    private final String description;
    private final Initializable ui;
    private Scene preScene;
    private String nextSceneName;

    /**
     * The constructor for this object.
     * @param description the following scene's description
     * @param ui the following scene's initializable UI
     * @param preScene the previous scene
     * @param nextSceneName this scene's name
     */
    public MenuItemFX(String description, Initializable ui, Scene preScene, String nextSceneName) {
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        }
        if (Objects.isNull(ui)) {
            throw new IllegalArgumentException("MenuItem does not support a null UI.");
        }

        this.description = description;
        this.ui = ui;
        this.preScene = preScene;
        this.nextSceneName = nextSceneName;
    }

    /**
     * This sets the scene for the following user UI
     * @param event pressing the button to login
     * @throws IOException if there isn't a UI for the next scene
     */
    public void run(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(nextSceneName));
        Scene scene = new Scene(fxmlLoader.load());
        UserUIFX userUI = fxmlLoader.getController();
        userUI.setPreScene(preScene);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        cropSceneName(nextSceneName);
        stage.setTitle(nextSceneName);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This sets the scene name as the fxml name
     * @param nextSceneName the new scene name
     */
    private void cropSceneName(String nextSceneName) {
        nextSceneName = nextSceneName.replace("/fxml/", "");
        nextSceneName = nextSceneName.replace(".fxml", "");
        setNextSceneName(nextSceneName + "UI");
    }

    public boolean hasDescription(String description) {
        return this.description.equals(description);
    }
    public void setNextSceneName(String nextSceneName){this.nextSceneName = nextSceneName;}

    public String toString() {
        return this.description;
    }
//    public Scene getPreScene(){return this.preScene;}
//    public void setPreScene(Scene preScene){this.preScene = preScene;}
}
