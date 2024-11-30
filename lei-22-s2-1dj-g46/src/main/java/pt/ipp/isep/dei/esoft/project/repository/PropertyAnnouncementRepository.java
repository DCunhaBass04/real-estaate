package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyAnnouncementRepository implements Serializable {

    private List<Property> announcements = new ArrayList<>();

    /**
     * This method checks if the property announcements List is empty or not.
     *
     * @return The Boolean value of the statement "The properties list is empty".
     */
    public boolean isEmpty(){
        return announcements.isEmpty();
    }

    /**
     * This method adds a Property to the propertyAnnouncements list.
     *
     * @param property The Property to be added.
     * @return A copy of the Property.
     */
    public Optional<Property> add(Property property) {

        Optional<Property> newProperty = Optional.empty();
        boolean operationSuccess = false;

        if (validateProperty(property)) {
            newProperty = Optional.of(property.clone());
            operationSuccess = announcements.add(newProperty.get());
        }

        if (!operationSuccess) {
            newProperty = Optional.empty();
        }

        return newProperty;
    }
    /**
     * This method validates the Property about to be added.
     *
     * @param property The Property to be added.
     * @return The Boolean value of the statement "The property is valid.".
     */
    private boolean validateProperty(Property property) {return !announcements.contains(property);}
    public void remove(Object o){
        announcements.remove(o);
    }

    /**
     * This method makes a copy of the properties list.
     *
     * @return The Copy of the properties List.
     */
    public List<Property> getProperties() {return List.copyOf(announcements);}
    //This is a defensive copy, so that the repository cannot be modified from the outside.

}
