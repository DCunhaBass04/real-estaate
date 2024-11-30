package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.Serializable;

/**
 * The type Announcement notification.
 */
public class AnnouncementNotification implements Serializable {
    private Property property;
    private Client propertyOwner;
    private String announcementDate;
    private String agentName;
    private String agentPhoneNumber;

    /**
     * Instantiates a new Announcement notification.
     *
     * @param property         the property
     * @param propertyOwner    the property owner
     * @param announcementDate the announcement date
     * @param agentName        the agent name
     * @param agentPhoneNumber the agent phone number
     */
    public AnnouncementNotification(Property property, Client propertyOwner, String announcementDate, String agentName, String agentPhoneNumber){
        this.property = property;
        this.propertyOwner = propertyOwner;
        this.announcementDate = announcementDate;
        this.agentName = agentName;
        this.agentPhoneNumber = agentPhoneNumber;
    }
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnnouncementNotification)) {
            return false;
        }
        AnnouncementNotification that = (AnnouncementNotification) o;
        return property.equals(that.property) && propertyOwner.equals(that.propertyOwner) && announcementDate.equals(that.announcementDate) && agentName.equals(that.agentName) && agentPhoneNumber.equals(that.agentPhoneNumber);
    }

    /**
     * Get property owner client.
     *
     * @return the client
     */
    public Client getPropertyOwner(){return propertyOwner;}
    public AnnouncementNotification clone(){return new AnnouncementNotification(this.property, this.propertyOwner, this.announcementDate, this.agentName, this.agentPhoneNumber);}
    public String toString(){return String.format("Your property:%n%swas published on %s%nBy: %s - %s", property, announcementDate, agentName, agentPhoneNumber);}
}
