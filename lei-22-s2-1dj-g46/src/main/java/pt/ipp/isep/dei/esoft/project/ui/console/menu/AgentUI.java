package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AgentUI implements Runnable {
    public AgentUI() {}

    public void run(){
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Set Commission from Owner", new SetCommissionUI()));
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
        options.add(new MenuItem("Check property visit requests", new CheckVisitsUI()));
        options.add(new MenuItem("Accept/Decline Property Offers", new AcceptOffersUI()));
        options.add(new MenuItem("Check the state of the Responses", new ReadResponseUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}