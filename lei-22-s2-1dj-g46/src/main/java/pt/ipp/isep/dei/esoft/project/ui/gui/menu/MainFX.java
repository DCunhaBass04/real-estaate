package pt.ipp.isep.dei.esoft.project.ui.gui.menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.SaveAndReadData;

import java.io.IOException;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            primaryStage.setTitle("Main Menu");
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            SaveAndReadData saveAndReadData = new SaveAndReadData();
            saveAndReadData.saveFile("repositories.dat");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
