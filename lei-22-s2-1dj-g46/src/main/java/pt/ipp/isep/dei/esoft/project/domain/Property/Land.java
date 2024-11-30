package pt.ipp.isep.dei.esoft.project.domain.Property;

import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

public class Land implements Property, Serializable {
    private float area;
    private String location;
    private float distanceFromCityCentre;
    private Agent agent;
    private File[] photographs;
    private float finalPrice;
    private String announcementDate;
    private String saleDate;
    private Request request;
    private Store store;
    private final Agent DEFAULT_AGENT = new Agent("notanagent@this.app");
    public Land(float area, String location, float distanceFromCityCentre, Agent agent, File[] photographs, float finalPrice, String announcementDate, String saleDate, Request request){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        this.agent = agent;
        this.photographs = photographs;
        this.finalPrice = finalPrice;
        this.announcementDate = announcementDate;
        this.saleDate = saleDate;
        this.request = request;
        store = Repositories.getInstance().getStoreRepository().getStoreByName(agent.getStore());
    }
    public Land(float area, String location, float distanceFromCityCentre, File[] photographs, float finalPrice, String announcementDate, String saleDate, Request request, Store store){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        agent = DEFAULT_AGENT;
        this.photographs = photographs;
        this.finalPrice = finalPrice;
        this.announcementDate = announcementDate;
        this.saleDate = saleDate;
        this.request = request;
        this.store = store;
    }
    public Land(float area, String location, float distanceFromCityCentre, Agent agent, File[] photographs, float finalPrice, String announcementDate, String saleDate, Request request, Store store){
        this.area = area;
        this.location = location;
        this.distanceFromCityCentre = distanceFromCityCentre;
        this.agent = agent;
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
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (!(object instanceof Land)) {
            return false;
        }
        Land land = (Land) object;
        return area == land.area && location.equals(land.location) && distanceFromCityCentre == land.distanceFromCityCentre &&
                agent.equals(land.agent) && Arrays.equals(photographs, land.photographs) && finalPrice == land.finalPrice &&
                announcementDate.equals(land.announcementDate) && saleDate.equals(land.saleDate) && request.equals(land.request) && store.equals(land.store);
    }
    public void setRequest(Request request){this.request = request;}

    @Override
    public void setFinalPrice(float finalPrice) {this.finalPrice = finalPrice;}

    @Override
    public void setAnnouncementDate(String announcementDate) {this.announcementDate = announcementDate;}

    public float getArea(){return this.area;}
    public float getFinalPrice(){return finalPrice;}
    public float getDistance(){return this.distanceFromCityCentre;}
    public int getNumOfPhotographs(){return this.photographs.length;}
    @Override
    public int getNumOfBedrooms() {return 0;}
    @Override
    public int getNumOfBathrooms() {return 0;}
    @Override
    public int getNumOfParkingSpaces() {return 0;}
    public String getAgentEmail(){return agent.getEmail();}
    public String getStoreEmail(){return store.getEmail();}
    public Request getRequest(){return this.request;}

    @Override
    public String toString(){
        String stringToReturn = String.format("%s%nLand in %s ", request, location);
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
        stringToReturn += String.format("%nArea: %.2f meters square%nDistance from city centre: %.2f km%n",
                            area, distanceFromCityCentre);
        if(request.getState().equals("Sold")){
            stringToReturn += String.format("Sold on %s.%n", saleDate);
        }
        return stringToReturn;
    }
    @Override
    public Land clone(){return new Land(this.area, this.location, this.distanceFromCityCentre, this.agent, this.photographs, this.finalPrice, this.announcementDate, this.saleDate, this.request, this.store);}
}
