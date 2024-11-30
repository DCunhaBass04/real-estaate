package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class Tuple implements Serializable {
    private int storeID;
    private int numberOfProperties;
    public Tuple(int storeID, int numberOfProperties){
        this.storeID = storeID;
        this.numberOfProperties = numberOfProperties;
    }
    public int getStoreID() {return storeID;}
    public int getNumberOfProperties() {return numberOfProperties;}
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple)) {
            return false;
        }
        Tuple tuple = (Tuple) o;
        return storeID == tuple.storeID && numberOfProperties == tuple.numberOfProperties;
    }
    public Tuple clone(){return new Tuple(this.storeID, this.numberOfProperties);}
    public String toString(){return String.format("Store ID: %s%nNumber of Properties: %s%n", storeID, numberOfProperties);}
}
