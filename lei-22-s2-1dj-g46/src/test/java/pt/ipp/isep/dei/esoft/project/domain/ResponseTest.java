package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest {
    @Test void ensureTwoResponsesWithSameDataEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        assertEquals(response1, response2);
    }
    @Test void ensureTwoResponsesWithDifferentSubjectsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test #1", "This is a text", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test #2", "This is a text", agent, client, "Pending", "n/a");
        assertNotEquals(response1, response2);
    }
    @Test void ensureTwoResponsesWithDifferentTextsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text #1", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text #2", agent, client, "Pending", "n/a");
        assertNotEquals(response1, response2);
    }
    @Test void ensureTwoResponsesWithDifferentAgentsNotEqual(){
        Agent agent1 = new Agent("johndoeagent@this.app");
        Agent agent2 = new Agent("janedoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text", agent1, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent2, client, "Pending", "n/a");
        assertNotEquals(response1, response2);
    }
    @Test void ensureTwoResponsesWithDifferentClientsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client1 = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Client client2 = new Client("Jane Doe", "janedoeclient@this.app", "janedoe");
        Response response1 = new Response("Response Test", "This is a text", agent, client1, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent, client2, "Pending", "n/a");
        assertNotEquals(response1, response2);
    }
    @Test void ensureTwoResponsesWithDifferentStatesNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent, client, "Accepted", "n/a");
        assertNotEquals(response1, response2);
    }
    @Test void ensureTwoResponsesWithDifferentReasonsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text #1", agent, client, "Declined", "I don't agree");
        Response response2 = new Response("Response Test", "This is a text #2", agent, client, "Declined", "agree don't I");
        assertNotEquals(response1, response2);
    }
    @Test void ensureResponseIsNotNull(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        assertNotNull(response);
    }
    @Test void ensureCloneWorks(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        Response clone = response.clone();
        assertEquals(response, clone);
    }
    @Test void ensureResponseDoesNotEqualObject(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        assertNotEquals(response, new Object());
    }
    @Test void ensureTheSameObjectIsEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        assertEquals(response, response);
    }
}
