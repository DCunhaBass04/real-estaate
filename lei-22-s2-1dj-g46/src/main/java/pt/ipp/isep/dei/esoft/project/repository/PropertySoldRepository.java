package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Property.Apartment;
import pt.ipp.isep.dei.esoft.project.domain.Property.House;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertySoldRepository implements Serializable {
    private List<Property> soldProperties = new ArrayList<>();
    private EditAndSortLists editAndSortLists = new EditAndSortLists();

    /**
     * This method checks if the property announcements List is empty or not.
     *
     * @return The Boolean value of the statement "The properties list is empty".
     */
    public boolean isEmpty(){
        return soldProperties.isEmpty();
    }

    /**
     * This method adds a Sold Property to the soldProperties list.
     *
     * @param property The Sold Property to be added.
     * @return A copy of the Sold Property.
     */
    public Optional<Property> add(Property property) {

        Optional<Property> newProperty = Optional.empty();
        boolean operationSuccess = false;

        if (validateProperty(property)) {
            newProperty = Optional.of(property.clone());
            operationSuccess = soldProperties.add(newProperty.get());
        }

        if (!operationSuccess) {
            newProperty = Optional.empty();
        }

        return newProperty;
    }
    /**
     * This method validates the Property about to be added.
     *
     * @param property The Sold Property to be added.
     * @return The Boolean value of the statement "The property is valid.".
     */
    private boolean validateProperty(Property property) {return !soldProperties.contains(property);}

    /**
     * This method makes a copy of the sold properties list.
     *
     * @return The Copy of the soldProperties List.
     */
    public List<Property> getProperties() {return List.copyOf(soldProperties);}
    //This is a defensive copy, so that the repository cannot be modified from the outside.

}
