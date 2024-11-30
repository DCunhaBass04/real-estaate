package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClientUI implements Runnable{
    public ClientUI() {}

    public void run(){
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Send Property Request(s) to Agent", new SendPropertyRequestUI()));
        options.add(new MenuItem("See Announcement Notifications", new SeeAnnouncementNotificationsUI()));
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
        options.add(new MenuItem("Request to visit a property", new LeaveMessageUI()));
        options.add(new MenuItem("Place an Order", new PlaceOrderUI()));
        options.add(new MenuItem("Check Responses to Orders", new CheckResponsesToOrdersUI()));
        options.add(new MenuItem("Read Response to Appointments", new ReadResponseUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
