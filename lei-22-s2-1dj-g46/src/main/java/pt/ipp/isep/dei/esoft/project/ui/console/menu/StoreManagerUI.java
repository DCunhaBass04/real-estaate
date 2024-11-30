package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.AnalyzeDealsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListPropertiesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class StoreManagerUI implements Runnable{
    public StoreManagerUI(){}
    public void run(){
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
        options.add(new MenuItem("Analyze Deals ", new AnalyzeDealsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nStore Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
