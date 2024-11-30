package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The ClientRepository class is responsible for managing and accessing client data.
 * It provides methods to add and retrieve client objects.
 */
public class ClientRepository implements Serializable {
    private List<Client> clients = new ArrayList<>();

    /**
     * Checks if the client repository is empty.
     *
     * @return True if the client repository is empty, false otherwise.
     */
    public boolean isEmpty() {
        return clients.isEmpty();
    }

    /**
     * Adds a new client to the repository.
     *
     * @param client The client object to be added.
     * @return An optional containing the newly added client if the operation is successful, otherwise an empty optional.
     */
    public Optional<Client> add(Client client) {

        Optional<Client> newClient = Optional.empty();
        boolean operationSuccess = false;

        if (validateClient(client)) {
            newClient = Optional.of(client.clone());
            operationSuccess = clients.add(newClient.get());
        }

        if (!operationSuccess) {
            newClient = Optional.empty();
        }

        return newClient;

    }

    public Client getClientEmailFromTheirPhoneNumber(String phoneNumber){
        for (Client client : clients){
            if(client.getPhoneNumber().equals(phoneNumber)){
                return client;
            }
        }
        return null;
    }

    /**
     * Validates if a client is unique (not already present in the repository).
     *
     * @param client The client object to be validated.
     * @return True if the client is unique, false otherwise.
     */
    private boolean validateClient(Client client) {
        return !clients.contains(client);
    }

    /**
     * Retrieves a client object from the repository based on the provided email address.
     *
     * @param email The email address of the client to be retrieved.
     * @return The client object associated with the email address, or null if not found.
     */
    public Client getClientByEmail(String email) {
        for (Client client : clients) {
            if (email.equals(client.getEmail())) {
                return client;
            }
        }
        return null;
    }

    /**
     * Retrieves a copy of the list of clients in the repository.
     *
     * @return The list of clients in the repository.
     */
    public List<Client> getClients() {
        return List.copyOf(clients);
    }
}
