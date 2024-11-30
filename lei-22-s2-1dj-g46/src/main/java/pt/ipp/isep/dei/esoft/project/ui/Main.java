package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.SaveAndReadData;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SaveAndReadData saveAndReadData = new SaveAndReadData();
            saveAndReadData.saveFile("repositories.dat");
        }
    }
}
