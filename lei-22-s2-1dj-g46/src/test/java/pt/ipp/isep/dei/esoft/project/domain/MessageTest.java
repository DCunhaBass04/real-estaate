package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Property.Land;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    @Test void ensureTwoMessagesWithSameDataAreEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message1 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        Message message2 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        assertEquals(message1, message2);
    }
    @Test void ensureTwoMessagesToDifferentAgentsNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message1 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        Message message2 = new Message("jane.doe@this.company.com", land, "visit subject #1", "visit text #1", client, date, 1, 10);
        assertNotEquals(message1, message2);
    }
    @Test void ensureTwoMessagesAboutDifferentPropertiesNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(999.99f, "there", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message1 = new Message(agent.getEmail(), land1, "visit subject #1", "visit text #1", client, date, 1, 10);
        Message message2 = new Message(agent.getEmail(), land2, "visit subject #1", "visit text #1", client, date, 1, 10);
        assertNotEquals(message1, message2);
    }
    @Test void ensureTwoMessagesWithDifferentSubjectNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message1 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        Message message2 = new Message(agent.getEmail(), land, "visit subject #2", "visit text #1", client, date, 1, 10);
        assertNotEquals(message1, message2);
    }
    @Test void ensureTwoMessagesWithDifferentTextNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message1 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        Message message2 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #2", client, date, 1, 10);
        assertNotEquals(message1, message2);
    }
    @Test void ensureTwoMessagesFromDifferentClientsNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client1 = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        Client client2 = new Client("jane doe client", 123, "1232", "janedoe@this.company.com", "123-4567-890", "janedoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message1 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client1, date, 1, 10);
        Message message2 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client2, date, 1, 10);
        assertNotEquals(message1, message2);
    }
    @Test void ensureTwoMessagesWithDifferentPreferredDatesNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date1 = new Date(2023, Calendar.JANUARY, 6);
        Date date2 = new Date(2023, Calendar.JANUARY, 7);
        Message message1 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date1, 1, 10);
        Message message2 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date2, 1, 10);
        assertNotEquals(message1, message2);
    }
    @Test void ensureTwoMessagesWithDifferentStartOfVisitNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message1 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        Message message2 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 3, 10);
        assertNotEquals(message1, message2);
    }
    @Test void ensureTwoMessagesWithDifferentEndOfVisitNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message1 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        Message message2 = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 12);
        assertNotEquals(message1, message2);
    }
    @Test void ensureMessageIsNotNull(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        assertNotEquals(message, null);
    }
    @Test void ensureMessageIsNotOtherObject(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        assertNotEquals(message, new Object());
    }
    @Test void ensureCloneWorks(){
        Agent agent = new Agent("john.doe@this.company.com");
        Client client = new Client("john doe client", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Date date = new Date(2023, Calendar.JANUARY, 6);
        Message message = new Message(agent.getEmail(), land, "visit subject #1", "visit text #1", client, date, 1, 10);
        Message clone = message.clone();
        assertEquals(message, clone);
    }
}
