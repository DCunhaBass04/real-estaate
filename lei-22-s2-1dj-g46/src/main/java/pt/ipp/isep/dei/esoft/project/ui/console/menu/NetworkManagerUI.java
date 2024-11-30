package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NetworkManagerUI implements Runnable{
    public NetworkManagerUI(){}
    public void run(){
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("List All Employees by Store", new ListEveryEmployeeUI()));
        options.add(new MenuItem("Divide Stores into Subsets of Stores", new CreateSubsetsOfStoresUI()));
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
        options.add(new MenuItem("List All Deals", new ListAllDealsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nNetwork Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
