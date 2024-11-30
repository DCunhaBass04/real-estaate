package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Property.*;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertySoldRepositoryTest {
    @Test
    void ensureEmptyPropertyRepositoryIsEmpty(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        propertySoldRepository.isEmpty();
    }

    @Test void ensureLandIsAddedToList(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "26/05/2023", request);
        propertySoldRepository.add(land);
        assertTrue(propertySoldRepository.getProperties().contains(land));
    }
    @Test void ensureApartmentIsAddedToList(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "26/05/2023", request);
        propertySoldRepository.add(apartment);
        assertTrue(propertySoldRepository.getProperties().contains(apartment));
    }
    @Test void ensureHouseIsAddedToList(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "26/05/2023", request);
        propertySoldRepository.add(house);
        assertTrue(propertySoldRepository.getProperties().contains(house));
    }
    @Test void ensureGetDealsWorks(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "26/05/2023", request);
        House house = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "26/05/2023", request);
        propertySoldRepository.add(land);
        propertySoldRepository.add(house);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(land);
        propertyList.add(house);
        assertEquals(propertyList, propertySoldRepository.getProperties());
    }
}
