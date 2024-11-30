package pt.ipp.isep.dei.esoft.project.domain.Users;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Comparable<Employee>, Serializable, User {

    private final String email;
    private final String name;
    private final String role;
    private final String phoneNumber;
    private final String address;
    private final String store;
    private final int ccNumber;
    private final int taxNumber;
    private final String password;
    private final String DEFAULT_NAME = "no name";
    private final String DEFAULT_ROLE = "no role";
    private final String DEFAULT_PHONENUMBER = "no phone number";
    private final String DEFAULT_ADDRESS = "no address";
    private final String DEFAULT_STORE = "no agency";
    private final int DEFAULT_CCNUMBER = 0;
    private final int DEFAULT_TAXNUMBER = 0;
    private final String DEFAULT_PASSWORD = "no password";

    //with address with password
    public Employee(int ccNumber,String email, String name, String role, String phoneNumber, String store, int taxNumber, String address, String password) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.store = store;
        this.ccNumber = ccNumber;
        this.taxNumber = taxNumber;
        this.address = address;
        this.password = password;
    }

    //with address without password
    public Employee(int ccNumber, String email, String name, String role, String phoneNumber, String store, int taxNumber, String address) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.store = store;
        this.ccNumber = ccNumber;
        this.taxNumber = taxNumber;
        password = DEFAULT_PASSWORD;
    }

    //with password without address
    public Employee(String email, String name, String role, String phoneNumber, String store, int ccNumber, int taxNumber, String password) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.store = store;
        this.ccNumber = ccNumber;
        this.taxNumber = taxNumber;
        this.password = password;
        address = DEFAULT_ADDRESS;
    }

    //without address without password
    public Employee(String email, String name, String role, String phoneNumber, String store, int ccNumber, int taxNumber) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.store = store;
        this.ccNumber = ccNumber;
        this.taxNumber = taxNumber;
        this.address = DEFAULT_ADDRESS;
        this.password = DEFAULT_PASSWORD;
    }
    public Employee(String email){
        this.email = email;
        name = DEFAULT_NAME;
        role = DEFAULT_ROLE;
        phoneNumber = DEFAULT_PHONENUMBER;
        address = DEFAULT_ADDRESS;
        store = DEFAULT_STORE;
        ccNumber = DEFAULT_CCNUMBER;
        taxNumber = DEFAULT_TAXNUMBER;
        password = DEFAULT_PASSWORD;
    }
    public Employee(String name, String email, String password, String role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        phoneNumber = DEFAULT_PHONENUMBER;
        address = DEFAULT_ADDRESS;
        store = DEFAULT_STORE;
        ccNumber = DEFAULT_CCNUMBER;
        taxNumber = DEFAULT_TAXNUMBER;
    }
    public Employee(String name, String email, String password, String role, String store){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        phoneNumber = DEFAULT_PHONENUMBER;
        address = DEFAULT_ADDRESS;
        this.store = store;
        ccNumber = DEFAULT_CCNUMBER;
        taxNumber = DEFAULT_TAXNUMBER;
    }
    /**
     * This method returns the Password of the Employee to be registered.
     *
     * @return The Password.
     */
    public String getPassword(){
        return password;
    }
    /**
     * This method returns the Tax Number of the Employee to be registered.
     *
     * @return The Tax Number.
     */
    public int getTaxNumber() {
        return taxNumber;
    }
    /**
     * This method returns the CC Number of the Employee to be registered.
     *
     * @return The CC Number.
     */
    public int getCcNumber() {
        return ccNumber;
    }
    /**
     * This method returns the Email of the Employee to be registered.
     *
     * @return The Email.
     */
    public String getEmail(){
        return email;
    }
    /**
     * This method returns the Name of the Employee to be registered.
     *
     * @return The Name.
     */
    public String getName(){
        return name;
    }
    /**
     * This method returns the Role of the Employee to be registered.
     *
     * @return The Role.
     */
    public String getRole(){
        return role;
    }
    /**
     * This method returns the Address of the Employee to be registered.
     *
     * @return The Address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * This method returns the Store of the Employee to be registered.
     *
     * @return The Store.
     */
    public String getStore() {
        return store;
    }
    /**
     * This method returns the phone number of the Employee to be registered.
     *
     * @return The Phone Number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return email.equals(employee.email);
    }

    @Override
    public int compareTo(Employee otherEmployee) {
        return name.compareToIgnoreCase(otherEmployee.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Employee clone() {
        return new Employee(this.ccNumber, this.email, this.name, this.role, this.phoneNumber, this.address, this.taxNumber, this.store, this.password);
    }
    public String toString() {
        return String.format("%s %s%n-Phone Number: %s%n-Email: %s%n", role, name, phoneNumber, email);
    }
}
