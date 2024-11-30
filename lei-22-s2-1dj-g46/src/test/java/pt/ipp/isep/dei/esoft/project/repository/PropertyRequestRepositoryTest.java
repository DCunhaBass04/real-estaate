package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property.Apartment;
import pt.ipp.isep.dei.esoft.project.domain.Property.House;
import pt.ipp.isep.dei.esoft.project.domain.Property.Land;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;


public class PropertyRequestRepositoryTest {

    @Test void ensureEmptyPropertyRequestRepositoryIsEmpty(){
        PropertyRequestRepository requestRepository = new PropertyRequestRepository();
        requestRepository.isEmpty();
    }

    @Test void ensureLandRequestIsAddedToList(){
        PropertyRequestRepository requestRepository = new PropertyRequestRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Requested", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph, 22222,  "n/a", "n/a", request);
        requestRepository.add(land);
        assertTrue(requestRepository.getRequests().contains(land));
    }
    @Test void ensureApartmentRequestIsAddedToList(){
        PropertyRequestRepository requestRepository = new PropertyRequestRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Requested", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "n/a", "n/a", request);
        requestRepository.add(apartment);
        assertTrue(requestRepository.getRequests().contains(apartment));
    }
    @Test void ensureHouseRequestIsAddedToList(){
        PropertyRequestRepository requestRepository = new PropertyRequestRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Requested", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222,  "n/a", "n/a", request);
        requestRepository.add(house);
        assertTrue(requestRepository.getRequests().contains(house));
    }
}
