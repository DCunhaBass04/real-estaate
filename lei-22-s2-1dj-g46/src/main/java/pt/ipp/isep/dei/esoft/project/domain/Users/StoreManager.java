package pt.ipp.isep.dei.esoft.project.domain.Users;

import java.io.Serializable;

public class StoreManager extends Employee implements Serializable, User{
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private String store;
    private int ccNumber;
    private int taxNumber;
    private String pwd;
    private final String DEFAULT_NAME = "no name";
    private final String DEFAULT_PWD = "no password";
    private final String DEFAULT_PHONE_NUMBER = "111-2222-333";
    private final String DEFAULT_ADDRESS = "Street no.12";
    private final String DEFAULT_STORE = "store";
    private final int DEFAULT_CCNUMBER = 123;
    private final int DEFAULT_TAXNUMBER = 123;
    public StoreManager(Employee employee){
        super(employee.getCcNumber(), employee.getEmail(), employee.getName(), employee.getRole(), employee.getPhoneNumber(), employee.getStore(), employee.getTaxNumber(), employee.getAddress(), employee.getPassword());
        this.email = employee.getEmail();
        this.name = employee.getName();
        this.phoneNumber = employee.getPhoneNumber();
        this.store = employee.getStore();
        this.ccNumber = employee.getCcNumber();
        this.taxNumber = employee.getTaxNumber();
        this.address = employee.getAddress();
        this.pwd = employee.getPassword();
    }
    public StoreManager(int ccNumber,String email, String name, String phoneNumber, String store, int taxNumber, String address, String pwd) {
        super(ccNumber, email, name, "Store Manager", phoneNumber, store, taxNumber, address, pwd);
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.store = store;
        this.ccNumber = ccNumber;
        this.taxNumber = taxNumber;
        this.address = address;
        this.pwd = pwd;
    }
    public StoreManager(String email){
        super(email);
        this.email = email;
        name = DEFAULT_NAME;
        phoneNumber = DEFAULT_PHONE_NUMBER;
        store = DEFAULT_STORE;
        ccNumber = DEFAULT_CCNUMBER;
        taxNumber = DEFAULT_TAXNUMBER;
        address = DEFAULT_ADDRESS;
        pwd = DEFAULT_PWD;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoreManager)) {
            return false;
        }
        StoreManager storeManager = (StoreManager) o;
        return name.equals(storeManager.name) && email.equals(storeManager.email) && pwd.equals(storeManager.pwd) && store.equals(storeManager.store)
                && ccNumber == storeManager.ccNumber && taxNumber == storeManager.taxNumber && phoneNumber.equals(storeManager.phoneNumber) && address.equals(storeManager.address);
    }

    /**
     * This method checks if the Agent has an email.
     *
     * @return The Boolean value of the statement "The Agent has an Email.".
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * This method gets the Name of the Agent.
     *
     * @return The Name of the Agent.
     */
    public String getName(){return name;}
    /**
     * This method gets the Email of the Agent.
     *
     * @return The Email of the Agent.
     */
    public String getEmail(){return email;}
    public String toString(){return super.toString();}
    public StoreManager clone() {
        return new StoreManager(this.ccNumber, this.email, this.name, this.phoneNumber, this.store, this.taxNumber, this.address, this.pwd);
    }
}
