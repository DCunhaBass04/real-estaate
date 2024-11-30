package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test void ensureTwoClientsWithSameDataAreEqual(){
        Client client1 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client client2 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        assertEquals(client1, client2);
    }
    @Test void ensureTwoClientsWithDifferentNamesAreNotEqual(){
        Client client1 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client client2 = new Client("jane doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        assertNotEquals(client1, client2);
    }
    @Test void ensureTwoClientsWithDifferentPassportNumbersAreNotEqual(){
        Client client1 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client client2 = new Client("john doe", 321, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        assertNotEquals(client1, client2);
    }
    @Test void ensureTwoClientsWithDifferentSocialSecurityNumbersAreNotEqual(){
        Client client1 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client client2 = new Client("john doe", 123, "2321", "johndoe@this.company.com", "123-4567-890", "johndoe");
        assertNotEquals(client1, client2);
    }
    @Test void ensureTwoClientsWithDifferentEmailsAreNotEqual(){
        Client client1 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client client2 = new Client("john doe", 123, "1232", "janedoe@this.company.com", "123-4567-890", "johndoe");
        assertNotEquals(client1, client2);
    }
    @Test void ensureTwoClientsWithDifferentPhoneNumbersAreNotEqual(){
        Client client1 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client client2 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "098-7654-321", "johndoe");
        assertNotEquals(client1, client2);
    }
    @Test void ensureTwoClientsWithDifferentPasswordsAreNotEqual(){
        Client client1 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client client2 = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "janedoe");
        assertNotEquals(client1, client2);
    }
    @Test void ensureClientIsNotNull(){
        Client client = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        assertNotEquals(client, null);
    }
    @Test void ensureClientIsNotAnotherObject(){
        Client client = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        assertNotEquals(client, new Object());
    }
    @Test void ensureCloneWorks(){
        Client client = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client clone = client.clone();
        assertEquals(client, clone);
    }
}
