package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgentRepository implements Serializable {
    private List<Agent> agents = new ArrayList<>();

    /**
     * This method checks if the agents List is empty or not.
     *
     * @return The Boolean value of the statement "The agents list is empty".
     */
    public boolean isEmpty(){
        return agents.isEmpty();
    }

    /**
     * This method adds an Agent to the agents list.
     *
     * @param agent The Agent to be added.
     * @return A copy of the Agent.
     */
    public Optional<Agent> add(Agent agent) {

        Optional<Agent> newAgent = Optional.empty();
        boolean operationSuccess = false;

        if (validateAgent(agent)) {
            newAgent = Optional.of(agent.clone());
            operationSuccess = agents.add(newAgent.get());
        }

        if (!operationSuccess) {
            newAgent = Optional.empty();
        }

        return newAgent;

    }

    /**
     * This method validates the Agent about to be added.
     *
     * @param agent The Agent to be added.
     * @return The Boolean value of the statement "The agent is valid.".
     */
    private boolean validateAgent(Agent agent) {return !agents.contains(agent);}

    /**
     * This method shows the agents List.
     *
     */
    public void printAgents(){
        for(int i = 0 ; i < agents.size(); i++){
            System.out.println((i+1) + " - " + agents.get(i) + " from " + agents.get(i).getStore());
            System.out.println();
        }
    }

    /**
     * This method gets the size of the agents list
     *
     * @return The size of the agents list.
     */
    public int getSize(){return agents.size();}

    /**
     * This method gets the selected Agent from the agents list.
     *
     * @return The selected Agent.
     */
    public Agent getxAgent(int x){return agents.get(x-1).clone();}

    /**
     * This Agent finds and Agent in the agents list by their email.
     *
     * @param email The Agent's email.
     * @return  The Agent.
     */
    public Agent getAgentByEmail(String email){
        for (Agent agent : agents) {
            if (email.equals(agent.getEmail())) {
                return agent;
            }
        }
        return null;
    }
    /**
     * This method makes a copy of the agents list.
     *
     * @return The Copy of the agents List.
     */
    public List<Agent> getAgents() {return List.copyOf(agents);}

}
