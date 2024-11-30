package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Response;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

/**
 * The CheckVisitsController class represents a controller for checking visits and displaying messages.
 */
public class CheckVisitsController {
    private AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    private MessageRepository messageRepository = Repositories.getInstance().getMessageRepository();
    private EditAndSortLists editAndSortLists = new EditAndSortLists();

    /**
     * This method is used to get the list of all messages that fit the date range specified by the user
     * @param messageList list of messages
     * @param beginDate begin date
     * @param endDate end date
     * @return filtered list of messages
     */
    public List<Message> changeListWithTimePeriod(List<Message> messageList,Date beginDate, Date endDate){
        int day = beginDate.getDate();
        int month = beginDate.getMonth() + 1;
        int year = beginDate.getYear() + 1900;
        int beginDateInt = Integer.parseInt(String.format("%4d%02d%02d",year,month,day));
        int endDay = endDate.getDate();
        int endMonth = endDate.getMonth()+1;
        int endYear = endDate.getYear()+1900;
        int endDateInt = Integer.parseInt(String.format("%4d%02d%02d",endYear,endMonth,endDay));
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).getPreferredDateInComparableInt() < beginDateInt || messageList.get(i).getPreferredDateInComparableInt() > endDateInt){
                messageList.remove(messageList.get(i));
            }
        }
        return messageList;
    }

    /**
     * Displays messages associated with the current agent.
     */
    public List<Message> getMessages() {
        String agentID = authenticationRepository.getCurrentUserSession().getUserId().toString();
        List<Message> messages = messageRepository.getMessages();
        messages = editAndSortLists.getMessagesDoneToXAgent(messages, agentID);
        return messages;
    }

    /**
     * This method is used to get all dates from the various messages
     * @param messageList message list
     * @return date list
     */
    public List<Date> getDates(List<Message> messageList){
        List<Date> dateList = new ArrayList<Date>();
        for (int i = 0; i < messageList.size(); i++) {
            dateList.add(messageList.get(i).getPreferredDate());
        }
        return dateList;
    }

    /**
     * This method is used to see if the inputted number is valid
     * @param choice chosen number
     * @param arraySize the array's size
     * @return is it valid or not?
     */
    public boolean validateAnswer(int choice, int arraySize) {
        return choice >= 0 && choice <= arraySize;
    }

    /**
     * This method is used to delete the answered message from its repository, create a new response, add it to its repository and send the response to the client's email address.
     * @param message message to be answered
     * @param responseText text to be featured in the new response
     * @throws MessagingException if there was an error when sending the email
     * @throws IOException if the file 'config.properties' did not exist
     */
    public void deleteFromRepositoryAndSendEmail(Message message, String responseText) throws MessagingException, IOException {
        messageRepository.remove(message);
        Client client = message.getClient();
        AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
        Agent agent = agentRepository.getAgentByEmail(authenticationRepository.getCurrentUserSession().getUserId().toString());
        Response response = new Response("Re: " + message.getSubject(), responseText, agent, client, "Pending");
        ResponseRepository responseRepository = Repositories.getInstance().getResponseRepository();
        responseRepository.add(response);
        SendEmail sendEmail = new SendEmail();
        sendEmail.sendAnEmail(message, agent, client, responseText);
    }
}
