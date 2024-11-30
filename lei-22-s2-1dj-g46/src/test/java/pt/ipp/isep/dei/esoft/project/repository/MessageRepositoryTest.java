package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.Property.Land;
import pt.ipp.isep.dei.esoft.project.domain.Property.Request;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageRepositoryTest {

    @Test void testAddMessage() {
        MessageRepository messageRepository = new MessageRepository();
        Agent agent = new Agent("agent@this.app");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Requested", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land property = new Land(123,"Isep",321, agent, photograph, 1000, "25/12", "30/12", request);
        Message message = new Message("agent@this.app", property, "Test", "Client", new Client("Bazinga", "bazinga@this.app", "bazinga"), new Date(), 1, 3);
        messageRepository.add(message);
        assertTrue(messageRepository.getMessages().contains(message));
    }

    @Test void ensureGetMessagesWorks(){
        MessageRepository messageRepository = new MessageRepository();
        List <Message> messageList = messageRepository.getMessages();
    }

    @Test boolean ensureValidateMessageWorks(Message message){
        MessageRepository messageRepository = new MessageRepository();
        boolean valid = true;
        String client = message.getClient().getPhoneNumber(), otherClient;
        Date preferredDate = message.getPreferredDate(), otherPreferredDate;
        List<Message> messages = messageRepository.getMessages();
        for (int i = 0; i < messages.size() && valid; i++) {
            otherClient = messages.get(i).getClient().getPhoneNumber();
            otherPreferredDate = messages.get(i).getPreferredDate();
            if (Objects.equals(otherClient, client) && messageRepository.checkIfTimeSlotsOverlap(message, messages.get(i)) && Objects.equals(otherPreferredDate, preferredDate)){
                valid = false;
                System.out.printf("This visit request overlaps with another made previously.%n");
            }
        }
        messageRepository.add(message);
        return valid;
    }

    @Test boolean checkIfTimeSlotsOverlap(Message message, Message otherMessage){
        int startOfVisit = message.getStartOfVisit(), endOfVisit = message.getEndOfVisit(), otherStartOfVisit = otherMessage.getStartOfVisit(), otherEndOfVisit = otherMessage.getEndOfVisit();
        if (startOfVisit > otherStartOfVisit && endOfVisit < otherEndOfVisit){
            return true;
        }
        if (otherStartOfVisit > startOfVisit && endOfVisit > otherEndOfVisit){
            return true;
        }
        if (otherEndOfVisit < endOfVisit && startOfVisit > otherStartOfVisit){
            return true;
        }
        return false;
    }

}
