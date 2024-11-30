package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Message;

import java.io.Serializable;
import java.util.*;

public class MessageRepository implements Serializable {
    private List<Message> messages = new ArrayList<Message>();

    /**
     * This method returns adds a message to the Repository.
     *
     * @param message The message that is being added.
     */
    public Optional<Message> add(Message message) {

        Optional<Message> newMessage = Optional.empty();
        boolean operationSuccess = false;

        if (validateMessage(message)) {
            newMessage = Optional.of(message.clone());
            operationSuccess = messages.add(newMessage.get());
        }

        if (!operationSuccess) {
            newMessage = Optional.empty();
        }

        return newMessage;

    }
    public void remove(Message message) {messages.remove(message);}
    /**
     * This method returns whether or not the message can be registered in the system.
     *
     * @param message The message that is being validated.
     * @return The boolean values of the phrase "The message is valid.".
     */
    public boolean validateMessage(Message message) {return !messages.contains(message);}

    /**
     * This method returns whether or not two messages overlap.
     *
     * @param message The message that is getting compared ti all registered messages.
     * @param otherMessage The message that the unregistered message is being compared to.
     * @return The boolean values of the phrase "The two messages overlap.".
     */
    public boolean checkIfTimeSlotsOverlap(Message message, Message otherMessage){
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

    /**
     * This method returns the list of all the messages stored in the repository.
     *
     * @return List of all messages.
     */
    public List<Message> getMessages(){
        return messages;
    }
}
