package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;

import java.util.List;

/**
 * The type List all deals controller.
 */
public class ListAllDealsController {
    private final EditAndSortLists editAndSortLists = new EditAndSortLists();

    /**
     * Get properties list.
     *
     * @return the list
     */
    public List<Property> getProperties(){
        List<Property> properties = Repositories.getInstance().getPropertySoldRepository().getProperties();
        return properties;
    }

    /**
     * Sort properties list.
     *
     * @param option     the option
     * @param option2    the option 2
     * @param properties the properties
     * @return the list
     */
    public List<Property> sortProperties(int option, int option2, List<Property> properties){
        switch (option) {
            case 1:
                switch (option2) {
                    case 1:
                        properties = editAndSortLists.bubbleSortAscending(properties);
                        break;
                    case 2:
                        properties = editAndSortLists.genericMergeSortStart(properties, true);
                        break;
                }
                break;
            case 2:
                switch (option2) {
                    case 1:
                        properties = editAndSortLists.bubbleSortDescending(properties);
                        break;
                    case 2:
                        properties = editAndSortLists.genericMergeSortStart(properties, false);
                        break;
                }
                break;
        }
        return properties;
    }
}
