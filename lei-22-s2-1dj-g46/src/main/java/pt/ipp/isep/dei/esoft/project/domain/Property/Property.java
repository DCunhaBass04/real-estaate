package pt.ipp.isep.dei.esoft.project.domain.Property;

import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;

import java.io.Serializable;

public interface Property extends Serializable {
    boolean equals(Object object);
    Property clone();

    String getAgentEmail();
    String getStoreEmail();
    Request getRequest();
    float getFinalPrice();
    float getArea();
    float getDistance();
    int getNumOfPhotographs();
    int getNumOfBedrooms();
    int getNumOfBathrooms();
    int getNumOfParkingSpaces();
    void setRequest(Request request);
    void setFinalPrice(float finalPrice);
    void setAnnouncementDate(String announcementDate);
    Agent getAgent();
}
