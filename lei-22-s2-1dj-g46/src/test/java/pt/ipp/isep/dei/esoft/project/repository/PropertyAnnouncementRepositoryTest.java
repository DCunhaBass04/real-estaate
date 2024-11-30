package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Property.*;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyAnnouncementRepositoryTest {

    @Test void ensureGetPropertiesWorks(){
        List<Property> propertyList = Repositories.getInstance().getPropertySoldRepository().getProperties();
        assertFalse(propertyList.isEmpty());
    }

    @Test void ensureEmptyPropertyRepositoryIsEmpty(){
        PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
        propertyAnnouncementRepository.isEmpty();
    }

    @Test void ensureLandIsAddedToList(){
        PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        propertyAnnouncementRepository.add(land);
        assertTrue(propertyAnnouncementRepository.getProperties().contains(land));
    }
    @Test void ensureApartmentIsAddedToList(){
        PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "n/a", request);
        propertyAnnouncementRepository.add(apartment);
        assertTrue(propertyAnnouncementRepository.getProperties().contains(apartment));
    }
    @Test void ensureHouseIsAddedToList(){
        PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "n/a", request);
        propertyAnnouncementRepository.add(house);
        assertTrue(propertyAnnouncementRepository.getProperties().contains(house));
    }
}
