package pt.ipp.isep.dei.esoft.project.domain.Property;

import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.Serializable;

public class Request implements Serializable {
    private String type;
    //'for sale' or 'for rent'
    private float price;
    private String priceUnit;
    //$ if type is 'for sale', $/month if 'for rent'
    private String state;
    //'Requested' if still a request, 'Published' if announced, 'Sold' if sold
    private String commissionDate;
    private Client client;
    //owner responsible for this request
    private String commissionValue;
    private int duration;

    public Request(String type, float price, String priceUnit, String state, String commissionDate, Client client, String commissionValue, int duration){
        this.type = type;
        this.price = price;
        this.priceUnit = priceUnit;
        this.state = state;
        this.commissionDate = commissionDate;
        this.client = client;
        this.commissionValue = commissionValue;
        this.duration = duration;
    }

    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        Request request = (Request) o;
        return type.equals(request.type) && price == request.price && priceUnit.equals(request.priceUnit) &&
                state.equals(request.state) && commissionDate.equals(request.commissionDate) &&
                client.equals(request.client) && commissionValue.equals(request.commissionValue) && duration == request.duration;
    }
    public void setPrice(float price){this.price = price;}
    public void setState(String state){this.state = state;}
    public void setCommissionDate(String commissionDate){this.commissionDate = commissionDate;}
    public void setCommissionValue(String commissionValue){this.commissionValue = commissionValue;}
    public float getPrice(){return price;}
    public float getTotalPrice(){return price*duration;}
    public int getDuration(){return duration;}
    public String getType(){return type;}
    public String getCommissionValue(){return commissionValue;}
    public String getCommissionDate(){return commissionDate;}
    public Client getOwner(){return client;}
    public String getPriceUnit(){return priceUnit;}
    public String getState(){return state;}

    public Request clone(){return new Request(this.type, this.price, this.priceUnit, this.state, this.commissionDate, this.client, this.commissionValue, this.duration);}

    public String toString(){
        String stringToReturn = String.format("Property %s", type);
        if(state.equals("Requested")){
            stringToReturn += String.format(" from Owner %s for %.2f%s ", client.getName(), price, priceUnit);
            if(type.equals("for rent")){
                stringToReturn += String.format("for %d months", duration);
            }
            stringToReturn += String.format("%nCommission value: %s", commissionValue);
            stringToReturn += String.format("%nRequested on %s", commissionDate);
        }
        return stringToReturn;
    }
}
