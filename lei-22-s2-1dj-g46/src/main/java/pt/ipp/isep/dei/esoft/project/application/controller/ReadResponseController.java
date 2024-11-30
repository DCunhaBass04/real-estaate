package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Response;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;

import java.util.List;

/**
 * The type Read response controller.
 */
public class ReadResponseController {
    private final EditAndSortLists editAndSortLists = new EditAndSortLists();

    /**
     * Gets current agent email.
     *
     * @return the current agent email
     */
    public Agent getCurrentAgentEmail() {
        String currentUserEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        return Repositories.getInstance().getAgentRepository().getAgentByEmail(currentUserEmail);
    }

    /**
     * Gets responses to agent.
     *
     * @return the responses to agent
     */
    public List<Response> getResponsesToAgent() {
        String currentUserEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        Agent currentAgent = Repositories.getInstance().getAgentRepository().getAgentByEmail(currentUserEmail);
        List<Response> responseList = Repositories.getInstance().getResponseRepository().getResponses();
        responseList = editAndSortLists.getResponsesByAgent(responseList, currentAgent);
        return responseList;
    }

    /**
     * Gets responses to client.
     *
     * @return the responses to client
     */
    public List<Response> getResponsesToClient() {
        String currentUserEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        Client currentClient = Repositories.getInstance().getClientRepository().getClientByEmail(currentUserEmail);
        List<Response> responseList = Repositories.getInstance().getResponseRepository().getResponses();
        responseList = editAndSortLists.getResponsesByClient(responseList, currentClient);
        responseList = editAndSortLists.getResponsesPending(responseList);
        return responseList;
    }

    /**
     * Change response.
     *
     * @param response the response
     */
    public void changeResponse(Response response) {
        response.setState("Accepted");
    }

    /**
     * Change response with reason.
     *
     * @param response the response
     * @param reason   the reason
     */
    public void changeResponseWithReason(Response response, String reason) {
        response.setState("Declined");
        response.setReason(reason);
    }
}
