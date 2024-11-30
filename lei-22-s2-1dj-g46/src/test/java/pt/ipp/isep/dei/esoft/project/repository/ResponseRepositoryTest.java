package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Response;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseRepositoryTest {
    @Test
    void ensureRepositoryIsCreated(){
        ResponseRepository responseRepository = new ResponseRepository();
        assertNotEquals(responseRepository, null);
    }
    @Test
    void ensureEmptyRepositoryIsEmpty(){
        ResponseRepository responseRepository = new ResponseRepository();
        assertTrue(responseRepository.isEmpty());
    }
    @Test void responseSuccessfullyAdded(){
        ResponseRepository responseRepository = new ResponseRepository();
        Response response = new Response("Response Test", "This is a text", new Agent("johndoeagent@this.app"), new Client("John Doe", "johndoeclient@this.app", "johndoe"), "Pending", "n/a");
        responseRepository.add(response);
        assertTrue(responseRepository.getResponses().contains(response));
    }
    @Test void responseSuccessfullyRemoved(){
        ResponseRepository responseRepository = new ResponseRepository();
        Response response = new Response("Response Test", "This is a text", new Agent("johndoeagent@this.app"), new Client("John Doe", "johndoeclient@this.app", "johndoe"), "Pending", "n/a");
        responseRepository.add(response);
        responseRepository.remove(response);
        assertFalse(responseRepository.getResponses().contains(response));
    }
    @Test void ensureGetResponses(){
        ResponseRepository responseRepository = new ResponseRepository();
        List<Response> listOfResponses = new ArrayList<>();
        Response response1 = new Response("Response Test #1", "This is a text #1", new Agent("johndoeagent@this.app"), new Client("John Doe", "johndoeclient@this.app", "johndoe"), "Pending", "n/a");
        Response response2 = new Response("Response Test #2", "This is a text #2", new Agent("janedoeagent@this.app"), new Client("Jane Doe", "janedoeclient@this.app", "janedoe"), "Pending", "n/a");
        responseRepository.add(response1);
        responseRepository.add(response2);
        listOfResponses.add(response1);
        listOfResponses.add(response2);
        assertEquals(listOfResponses, responseRepository.getResponses());
    }
}
