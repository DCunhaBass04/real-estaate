package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyRequestRepository implements Serializable {
    private List<Property> requests = new ArrayList<>();

    /**
     * This method check if the requests list is empty.
     *
     * @return The Boolean value of the statement "The requests list is empty.".
     */
    public boolean isEmpty(){
        return requests.isEmpty();
    }

    /**
     * This method adds a Property to the propertyRequests list.
     *
     * @param request The Request to be added to the List.
     * @return A Copy of the Property Request.
     */
    public Optional<Property> add(Property request) {

        Optional<Property> newRequest = Optional.empty();
        boolean operationSuccess = false;

        if (validateRequest(request)) {
            newRequest = Optional.of(request.clone());
            operationSuccess = requests.add(newRequest.get());
        }

        if (!operationSuccess) {
            newRequest = Optional.empty();
        }

        return newRequest;

    }
    /**
     * This method removes a Request from the propertyRequests List.
     *
     * @param o The Request to be removed.
     */
    public void remove(Object o){
        requests.remove(o);
    }
    /**
     * This method validates the Request to be added to the propertyRequests List.
     *
     * @param request The Request to be added.
     * @return The boolean value of the statement "The Request is valid.".
     */
    private boolean validateRequest(Property request) {return !requests.contains(request);}

    /**
     * This method gets the Request sent to an agent.
     *
     * @param agentEmail The Email of the Agent.
     * @return The List of the Requests to said Agent.
     */
    public List<Property> getPropertyRequestedToXAgent(String agentEmail){
        List<Property> requestsToXAgent = new ArrayList<>();
        for (Property request : requests) {
            if (request.getAgentEmail().equals(agentEmail)) {
                requestsToXAgent.add(request);
            }
        }
        return requestsToXAgent;
    }
    /**
     * This method makes a copy of the requests list.
     *
     * @return The Copy of the requests List.
     */
    public List<Property> getRequests() {return List.copyOf(requests);}
    //This is a defensive copy, so that the repository cannot be modified from the outside.

}
