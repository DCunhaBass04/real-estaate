package pt.ipp.isep.dei.esoft.project.domain.Property;

import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

public class Apartment implements Property, Serializable {
    private float area;
    private String location;
    private float distanceFromCityCentre;
    private Agent agent;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private int numOfParkingSpaces;
    private boolean centralHeating;
    private boolean airConditioning;
    private File[] photographs;
    private float finalPrice;
    private String announcementDate;
    private String saleDate;
    private Request request;
    private Store store;
    private final Agent DEFAULT_AGENT = new Agent("notanagent@this.app");

    public Apartment(float area, String location, float distanceFromCityCentre, Agent agent, int numOfBedrooms,
                     int numOfBathrooms, int numOfParkingSpaces, boolean centralHeating, boolean airConditioning,
                     File[] photographs, float finalPrice, String announcementDate, String saleDate, Request request){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        this.agent = agent;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfParkingSpaces = numOfParkingSpaces;
        this.centralHeating = centralHeating;
        this.airConditioning = airConditioning;
        this.photographs = photographs;
        this.finalPrice = finalPrice;
        this.announcementDate = announcementDate;
        this.saleDate = saleDate;
        this.request = request;
        store = Repositories.getInstance().getStoreRepository().getStoreByName(agent.getStore());
    }
    public Apartment(float area, String location, float distanceFromCityCentre, int numOfBedrooms,
                     int numOfBathrooms, int numOfParkingSpaces, boolean centralHeating, boolean airConditioning,
                     File[] photographs, float finalPrice, String announcementDate, String saleDate, Request request, Store store){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        agent = DEFAULT_AGENT;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfParkingSpaces = numOfParkingSpaces;
        this.centralHeating = centralHeating;
        this.airConditioning = airConditioning;
        this.photographs = photographs;
        this.finalPrice = finalPrice;
        this.announcementDate = announcementDate;
        this.saleDate = saleDate;
        this.request = request;
        this.store = store;
    }
    public Apartment(float area, String location, float distanceFromCityCentre, Agent agent, int numOfBedrooms,
                     int numOfBathrooms, int numOfParkingSpaces, boolean centralHeating, boolean airConditioning,
                     File[] photographs, float finalPrice, String announcementDate, String saleDate, Request request, Store store){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        this.agent = agent;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfParkingSpaces = numOfParkingSpaces;
        this.centralHeating = centralHeating;
        this.airConditioning = airConditioning;
        this.photographs = photographs;
        this.finalPrice = finalPrice;
        this.announcementDate = announcementDate;
        this.saleDate = saleDate;
        this.request = request;
        this.store = store;
    }
    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (!(object instanceof Apartment)) {
            return false;
        }
        Apartment apartment = (Apartment) object;
        return area == apartment.area && location.equals(apartment.location) && distanceFromCityCentre == apartment.distanceFromCityCentre &&
                agent.equals(apartment.agent) && numOfBedrooms == apartment.numOfBedrooms &&
                numOfBathrooms == apartment.numOfBathrooms && numOfParkingSpaces == apartment.numOfParkingSpaces &&
                centralHeating == apartment.centralHeating && airConditioning == apartment.airConditioning && Arrays.equals(photographs, apartment.photographs)
                && finalPrice == apartment.finalPrice && announcementDate.equals(apartment.announcementDate)
                && saleDate.equals(apartment.saleDate) && request.equals(apartment.request) && store.equals(apartment.store);

    }

    @Override
    public Agent getAgent() {return agent;}
    public void setRequest(Request request){this.request = request;}
    public void setFinalPrice(float finalPrice){this.finalPrice = finalPrice;}

    @Override
    public void setAnnouncementDate(String announcementDate) {this.announcementDate = announcementDate;}
    public float getArea(){return this.area;}
    public float getDistance(){return this.distanceFromCityCentre;}
    public int getNumOfPhotographs(){return this.photographs.length;}
    @Override
    public int getNumOfBedrooms() {return numOfBedrooms;}
    @Override
    public int getNumOfBathrooms() {return numOfBathrooms;}
    @Override
    public int getNumOfParkingSpaces() {return numOfParkingSpaces;}

    public String getAgentEmail(){return agent.getEmail();}
    public String getStoreEmail(){return store.getEmail();}
    public float getFinalPrice(){return finalPrice;}
    public Request getRequest(){return this.request;}


    public String toString(){
        String centralHeatingString = centralHeating ? "Yes" : "No";
        String airConditioningString = airConditioning ? "Yes" : "No";
        String stringToReturn = String.format("%s%nApartment in %s ", request, location);
        if(!request.getState().equals("Requested")){
            if(!agent.equals(DEFAULT_AGENT)){
                stringToReturn += String.format("by Agent %s ", agent.getName());
            } else {
                stringToReturn += String.format("by Store %s ", store.getStoreName());
            }
            stringToReturn += String.format("for %.2f%s ", finalPrice, request.getPriceUnit());
            if(request.getType().equals("for rent")){
                stringToReturn += String.format("for %d months", request.getDuration());
            }
            stringToReturn += String.format("%nPublished on %s", announcementDate);
        }
        stringToReturn += String.format("%nArea: %.2f meters square%nDistance from city centre: %.2f km%nNumber of...%n-Bedrooms: %d%n-Bathrooms: " +
                "%d%n-Parking Spaces: %d%nCentral Heating? %s%nAir Conditioning? %s%n", area, distanceFromCityCentre,
                numOfBedrooms, numOfBathrooms, numOfParkingSpaces, centralHeatingString, airConditioningString);
        return stringToReturn;
    }
    @Override
    public Apartment clone(){return new Apartment(this.area, this.location, this.distanceFromCityCentre, this.agent, this.numOfBedrooms, this.numOfBathrooms,
                                                  this.numOfParkingSpaces, this.centralHeating, this.airConditioning, this.photographs,
                                                  this.finalPrice, this.announcementDate, this.saleDate, this.request, this.store);}
}
