package pt.ipp.isep.dei.esoft.project.domain.Property;

import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

public class House implements Property, Serializable {
    private float area;
    private String location;
    private float distanceFromCityCentre;
    private Agent agent;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private int numOfParkingSpaces;
    private boolean centralHeating;
    private boolean airConditioning;
    private boolean basement;
    private boolean inhabitableLoft;
    private String sunExposure;
    //North, South, East and West
    private File[] photographs;
    private float finalPrice;
    private String announcementDate;
    private String saleDate;
    private Request request;
    private Store store;
    private final Agent DEFAULT_AGENT = new Agent("notanagent@this.app");

    public House(float area, String location, float distanceFromCityCentre, Agent agent, int numOfBedrooms,
                     int numOfBathrooms, int numOfParkingSpaces, boolean centralHeating, boolean airConditioning,
                     boolean basement, boolean inhabitableLoft, String sunExposure, File[] photographs, float finalPrice,
                     String announcementDate, String saleDate, Request request){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        this.agent = agent;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfParkingSpaces = numOfParkingSpaces;
        this.centralHeating = centralHeating;
        this.airConditioning = airConditioning;
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
        this.photographs = photographs;
        this.finalPrice = finalPrice;
        this.announcementDate = announcementDate;
        this.saleDate = saleDate;
        this.request = request;
        store = Repositories.getInstance().getStoreRepository().getStoreByName(agent.getStore());
    }
    public House(float area, String location, float distanceFromCityCentre, int numOfBedrooms,
                 int numOfBathrooms, int numOfParkingSpaces, boolean centralHeating, boolean airConditioning,
                 boolean basement, boolean inhabitableLoft, String sunExposure, File[] photographs, float finalPrice,
                 String announcementDate, String saleDate, Request request, Store store){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        agent = DEFAULT_AGENT;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfParkingSpaces = numOfParkingSpaces;
        this.centralHeating = centralHeating;
        this.airConditioning = airConditioning;
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
        this.photographs = photographs;
        this.finalPrice = finalPrice;
        this.announcementDate = announcementDate;
        this.saleDate = saleDate;
        this.request = request;
        this.store = store;
    }
    public House(float area, String location, float distanceFromCityCentre, Agent agent, int numOfBedrooms,
                 int numOfBathrooms, int numOfParkingSpaces, boolean centralHeating, boolean airConditioning,
                 boolean basement, boolean inhabitableLoft, String sunExposure, File[] photographs, float finalPrice,
                 String announcementDate, String saleDate, Request request, Store store){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        this.agent = agent;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfParkingSpaces = numOfParkingSpaces;
        this.centralHeating = centralHeating;
        this.airConditioning = airConditioning;
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
        this.photographs = photographs;
        this.finalPrice = finalPrice;
        this.announcementDate = announcementDate;
        this.saleDate = saleDate;
        this.request = request;
        this.store = store;
    }
    @Override
    public Agent getAgent() {return agent;}
    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (!(object instanceof House)) {
            return false;
        }
        House house = (House) object;
        return area == house.area && location.equals(house.location) && distanceFromCityCentre == house.distanceFromCityCentre &&
                agent.equals(house.agent) && numOfBedrooms == house.numOfBedrooms &&
                numOfBathrooms == house.numOfBathrooms && numOfParkingSpaces == house.numOfParkingSpaces &&
                airConditioning == house.airConditioning && centralHeating == house.centralHeating &&
                basement == house.basement && inhabitableLoft == house.inhabitableLoft && sunExposure.equals(house.sunExposure) &&
                Arrays.equals(photographs, house.photographs) && finalPrice == house.finalPrice &&
                announcementDate.equals(house.announcementDate) && saleDate.equals(house.saleDate) && request.equals(house.request) && store.equals(house.store);

    }

    public void setRequest(Request request){this.request = request;}

    public void setFinalPrice(float finalPrice){this.finalPrice = finalPrice;}

    @Override
    public void setAnnouncementDate(String announcementDate) {this.announcementDate = announcementDate;}

    public float getArea(){return this.area;}
    public float getFinalPrice(){return finalPrice;}
    public float getDistance(){return this.distanceFromCityCentre;}
    public int getNumOfPhotographs(){return this.photographs.length;}
    public Request getRequest(){return this.request;}
    public String getAgentEmail(){return agent.getEmail();}
    public String getStoreEmail(){return store.getEmail();}
    @Override
    public int getNumOfBedrooms() {return numOfBedrooms;}
    @Override
    public int getNumOfBathrooms() {return numOfBathrooms;}
    @Override
    public int getNumOfParkingSpaces() {return numOfParkingSpaces;}
    public String toString(){
        String centralHeatingString = centralHeating ? "Yes" : "No";
        String airConditioningString = airConditioning ? "Yes" : "No";
        String basementString = basement ? "Yes" : "No";
        String inhabitableLoftString = inhabitableLoft ? "Yes" : "No";
        String stringToReturn = String.format("%s%nHouse in %s ", request, location);
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
        "%d%n-Parking Spaces: %d%nCentral Heating? %s%nAir Conditioning? %s%nBasement? %s%nInhabitable Loft? %s%nSun Exposure: %s%n",
                area, distanceFromCityCentre, numOfBedrooms, numOfBathrooms, numOfParkingSpaces, centralHeatingString,
                airConditioningString, basementString, inhabitableLoftString, sunExposure);
        return stringToReturn;
    }
    @Override
    public House clone(){return new House(this.area, this.location, this.distanceFromCityCentre, this.agent, this.numOfBedrooms,
            this.numOfBathrooms, this.numOfParkingSpaces, this.centralHeating, this.airConditioning, this.basement,
            this.inhabitableLoft, this.sunExposure, this.photographs, this.finalPrice, this.announcementDate, this.saleDate, this.request, this.store);}
}
