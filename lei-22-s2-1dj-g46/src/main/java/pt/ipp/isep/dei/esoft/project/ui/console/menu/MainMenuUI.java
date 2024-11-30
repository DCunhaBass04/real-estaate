package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListPropertiesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.SignUpUI;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class MainMenuUI implements Runnable {

    public MainMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Sign up", new SignUpUI()));
        options.add(new MenuItem("List all Properties", new ListPropertiesUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }


}
