package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepositoryTest {
    @Test void ensureAClientRepositoryIsCreated(){
        ClientRepository clientRepository = new ClientRepository();
        assertNotEquals(clientRepository, null);
    }
    @Test void ensureAClientIsAddedToTheRepository(){
        ClientRepository clientRepository = new ClientRepository();
        Client client = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        clientRepository.add(client);
        assertTrue(clientRepository.getClients().contains(client));
    }
}
