package pt.ipp.isep.dei.esoft.project.domain.Users;

import java.io.Serializable;

public class Client implements Serializable, User {
    private String name;
    private int passportNumber;
    private String socialSecurityNumber;
    private String email;
    private String phoneNumber;
    private String pwd;
    private final int DEFAULT_PASSPORTNUMBER = 111111111;
    private final String DEFAULT_SOCIALSECURITYNUMBER = "XXX-XX-XXXX";
    private final String DEFAULT_PHONENUMBER = "XXX-XXX-XXXX";
    private final String DEFAULT_PASSWORD = "no password";
    public Client(String name, String email, String pwd){
        this.name = name;
        passportNumber = DEFAULT_PASSPORTNUMBER;
        socialSecurityNumber = DEFAULT_SOCIALSECURITYNUMBER;
        this.email = email;
        phoneNumber = DEFAULT_PHONENUMBER;
        this.pwd = pwd;
    }
    public Client(String name, int passportNumber, String socialSecurityNumber, String email, String phoneNumber){
        this.name = name;
        this.passportNumber = passportNumber;
        this.socialSecurityNumber = socialSecurityNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        pwd = DEFAULT_PASSWORD;
    }
    public Client(String name, int passportNumber, String socialSecurityNumber, String email, String phoneNumber, String pwd){
        this.name = name;
        this.passportNumber = passportNumber;
        this.socialSecurityNumber = socialSecurityNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pwd = pwd;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return name.equals(client.name) && passportNumber == client.passportNumber && socialSecurityNumber.equals(client.socialSecurityNumber) &&
                email.equals(client.email) && phoneNumber.equals(client.phoneNumber) && pwd.equals(client.pwd);
    }
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return pwd;}
    public String getPhoneNumber(){return phoneNumber;}
    public String toString(){return String.format("%s --------- %s", name, email);}
    public Client clone(){return new Client(this.name, this.passportNumber, this.socialSecurityNumber, this.email, this.phoneNumber, this.pwd);}
}