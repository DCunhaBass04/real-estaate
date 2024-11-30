package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
        options.add(new MenuItem("List Sold Properties", new ListSoldPropertiesUI()));
        options.add(new MenuItem("Register New Employee", new CreateEmployeeUI()));
        options.add(new MenuItem("Register a Store ", new CreateStoreUI()));
        options.add(new MenuItem("Import from a Legacy System", new ImportLegacySystemUI()));
        options.add(new MenuItem("Reset System", new ResetSystemUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
