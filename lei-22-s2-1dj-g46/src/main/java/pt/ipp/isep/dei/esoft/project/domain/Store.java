package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Comparable<Store>, Serializable {

    private final String storeName;
    private final String address;
    private final String phoneNumber;
    private final int id;
    private final String email;
    private final String DEFAULT_STORE_NAME = "no name";
    private final String DEFAULT_ADDRESS = "no address";
    private final String DEFAULT_PHONE_NUMBER = "no number";
    private final int DEFAULT_ID = 0;
    private final String DEFAULT_EMAIL = "no email";
    private List<Employee> employeeList = new ArrayList<>();
    private List<Property> propertyList = new ArrayList<>();

    /**
     *
     * @param storeName: The name of the store.
     * @param address: The address of the store.
     * @param phoneNumber: The phone number of the store.
     * @param id: The ID number of the store.
     * @param email: The email address of the store.
     */
    public Store(String storeName, String address, String phoneNumber,int id, String email) {
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.email = email;
    }

    public Store(){
        storeName = DEFAULT_STORE_NAME;
        address = DEFAULT_ADDRESS;
        phoneNumber = DEFAULT_PHONE_NUMBER;
        id = DEFAULT_ID;
        email = DEFAULT_EMAIL;
    }

    /**
     * getters of the objects store.
     *
     */
    public String getStoreName() {
        return storeName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public List<Employee> getEmployeeList(){return employeeList;}
    public List<Property> getPropertyList(){return propertyList;}

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }
    public void addProperty(Property property){propertyList.add(property);}
    public boolean containsStoreManager(){
        boolean hasStoreManager = false;
        for (int i = 0; i < employeeList.size() && !hasStoreManager; i++) {
            if(employeeList.get(i).getRole().equals("Store Manager")){
                hasStoreManager = true;
            }
        }
        return hasStoreManager;
    }

    /**
     *
     * This method checks if an object is a type Store.
     * @param o: is an instance of Store, then the method casts "o" to a Store object and compares each attribute of the current Store object with the corresponding attribute of the other Store object.
     * @return The Boolean value of the statement "The object is a type Store..".
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Store)) {
            return false;
        }
        Store store = (Store) o;
        return storeName.equals(store.storeName) && address.equals(store.address) && phoneNumber.equals(store.phoneNumber) && id == store.id && email.equals(store.email);
    }
    @Override
    public int compareTo(Store store) {
        return this.propertyList.size() - store.propertyList.size();
    }

    /**
     * The clone() method is used to create a new object with the same state as the current object.
     * @return returns a new Store object with the same attribute values as the current object.
     */
    public Store clone() {
        return new Store(this.storeName, this.address, this.phoneNumber,this.id, this.email);
    }
    public String toString(){
        return String.format("Store %s%n-Address: %s%n-Phone Number: %s%n-Email: %s%n", storeName, address, phoneNumber, email);
    }
}


