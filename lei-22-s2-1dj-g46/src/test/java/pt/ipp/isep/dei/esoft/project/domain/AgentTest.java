package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;

import static org.junit.jupiter.api.Assertions.*;
public class AgentTest {
    @Test void ensureTwoAgentsWithSameDataEquals(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        assertEquals(agent1, agent2);
    }
    @Test void ensureTwoAgentsWithDifferentCCNumbersNotEqual(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(321, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        assertNotEquals(agent1, agent2);
    }
    @Test void ensureTwoAgentsWithDifferentEmailsNotEqual(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(123, "jane.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        assertNotEquals(agent1, agent2);
    }
    @Test void ensureTwoAgentsWithDifferentNamesNotEqual(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(123, "john.doe@this.company.com", "jane", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        assertNotEquals(agent1, agent2);
    }
    @Test void ensureTwoAgentsWithDifferentPhoneNumbersNotEqual(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(123, "john.doe@this.company.com", "john", "321-7654-098", "store1", 1234, "Rua Daqui", "john");
        assertNotEquals(agent1, agent2);
    }
    @Test void ensureTwoAgentsOfDifferentStoresNotEqual(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store2", 1234, "Rua Daqui", "john");
        assertNotEquals(agent1, agent2);
    }
    @Test void ensureTwoAgentsWithDifferentTaxNumbersNotEqual(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 4321, "Rua Daqui", "john");
        assertNotEquals(agent1, agent2);
    }
    @Test void ensureTwoAgentsWithDifferentAddressesNotEqual(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Dali", "john");
        assertNotEquals(agent1, agent2);
    }
    @Test void ensureTwoAgentsWithDifferentPasswordsNotEqual(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent agent2 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "jane");
        assertNotEquals(agent1, agent2);
    }
    @Test void ensureAgentIsEmployee(){
        Agent agent1 = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        assertTrue(agent1 instanceof Employee);
    }
    @Test void ensureAgentDoesNotEqualNull() {
        Agent agent1 = new Agent("john.doe@this.company.com");
        assertNotEquals(agent1, null);
    }

    @Test void ensureAgentDoesNotEqualOtherObject() {
        Agent agent1 = new Agent("john.doe@this.company.com");
        assertNotEquals(agent1, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {
        Agent agent1 = new Agent("john.doe@this.company.com");
        assertEquals(agent1, agent1);
    }
    @Test void ensureHasEmailWorksForTheSameEmail() {
        String email = "john.doe@this.company.org";
        Agent agent = new Agent(email);
        assertTrue(agent.hasEmail(email));

    }
    @Test void ensureHasEmailFailsForDifferentEmails() {
        String email = "john.doe@this.company.com";
        Agent agent = new Agent(email);
        assertFalse(agent.hasEmail("jane.doe@this.company.com"));

    }
    @Test void ensureCloneWorks(){
        Agent agent = new Agent(123, "john.doe@this.company.com", "john", "123-4567-890", "store1", 1234, "Rua Daqui", "john");
        Agent clone = agent.clone();
        assertEquals(agent, clone);
    }
}
