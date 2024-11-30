package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgentRepositoryTest {

    @Test void ensureAgentRepositoryIsCreated(){
        AgentRepository agentRepository = new AgentRepository();
        assertNotEquals(agentRepository, null);
    }
    @Test void agentSuccessfullyAdded(){
        AgentRepository agentRepository = new AgentRepository();
        Agent agent = new Agent("johndoe@this.company.app");
        agentRepository.add(agent);
        assertTrue(agentRepository.getAgents().contains(agent));
    }
    @Test void ensureGetAgentByEmailForExistingAgent(){
        AgentRepository agentRepository = new AgentRepository();
        String email = "johndoe@this.company.app";
        Agent agent1 = new Agent(email);
        agentRepository.add(agent1);
        Agent agent2 = agentRepository.getAgentByEmail(email);
        assertEquals(agent1, agent2);
    }
    @Test void ensureGetAgentByEmailFailsForNonExistingAgent(){
        AgentRepository agentRepository = new AgentRepository();
        String email = "johndoe@this.company.app";
        Agent agent1 = new Agent("");
        agentRepository.add(agent1);
        Agent agent2 = agentRepository.getAgentByEmail(email);
        assertNull(agent2);
    }
    @Test void ensureGetAgents(){
        AgentRepository agentRepository = new AgentRepository();
        List<Agent> listOfAgents = new ArrayList<>();
        Agent agent1 = new Agent("johndoe@this.company.app");
        Agent agent2 = new Agent("janedoe@this.company.app");
        agentRepository.add(agent1);
        agentRepository.add(agent2);
        listOfAgents.add(agent1);
        listOfAgents.add(agent2);
        assertEquals(listOfAgents, agentRepository.getAgents());
    }
}
