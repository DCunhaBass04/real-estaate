package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Message.
 */
public class Message implements Serializable {

    private final Property property;
    private final String agentID;
    private final String subject;
    private final String text;
    private final Client client;
    private final Date preferredDate;
    private final int startOfVisit;
    private final int endOfVisit;
    private final String DEFAULT_SUBJECT = "No Subject";

    /**
     * Instantiates a new Message.
     *
     * @param agentID       the agent id
     * @param property      the property
     * @param text          the text
     * @param client        the client
     * @param preferredDate the preferred date
     * @param startOfVisit  the start of visit
     * @param endOfVisit    the end of visit
     */
    public Message (String agentID, Property property, String text, Client client, Date preferredDate, int startOfVisit, int endOfVisit){
        this.agentID = agentID;
        this.property = property;
        subject = DEFAULT_SUBJECT;
        this.text = text;
        this.client = client;
        this.preferredDate = preferredDate;
        this.startOfVisit = startOfVisit;
        this.endOfVisit = endOfVisit;
    }

    /**
     * Instantiates a new Message.
     *
     * @param agentID       the agent id
     * @param property      the property
     * @param subject       the subject
     * @param text          the text
     * @param client        the client
     * @param preferredDate the preferred date
     * @param startOfVisit  the start of visit
     * @param endOfVisit    the end of visit
     */
    public Message (String agentID, Property property, String subject, String text, Client client, Date preferredDate, int startOfVisit, int endOfVisit){
        this.agentID = agentID;
        this.property = property;
        this.subject = subject;
        this.text = text;
        this.client = client;
        this.preferredDate = preferredDate;
        this.startOfVisit = startOfVisit;
        this.endOfVisit = endOfVisit;
    }
    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (!(object instanceof Message)) {
            return false;
        }
        Message message = (Message) object;
        return agentID.equals(message.agentID) && property.equals(message.property) && subject.equals(message.subject) && text.equals(message.text) &&
                client.equals(message.client) && preferredDate == message.preferredDate &&
                startOfVisit == message.startOfVisit && endOfVisit == message.endOfVisit;
    }
    public String toString(){
        int day = preferredDate.getDate();
        int month = preferredDate.getMonth()+1;
        int year = preferredDate.getYear()+1900;
        String string = String.format("%02d-%02d-%4d", day, month, year);
        return String.format("%nFrom: %s%nTo: %s%nContactable via: %s%nPreferred date for visit: %s%nPreferred time slot: from %dh to %dh%nSubject: %s%nMessage: %s%n", client.getName(), agentID, client.getPhoneNumber(), string, startOfVisit, endOfVisit, subject,text);
    }

    public Message clone() {
        return new Message(this.agentID, this.property, this.subject, this.text, this.client, this.preferredDate, this.startOfVisit, this.endOfVisit);
    }

    /**
     * Get agent id string.
     *
     * @return the string
     */
    public String getAgentID(){return agentID;}

    /**
     * Get preferred date date.
     *
     * @return the date
     */
    public Date getPreferredDate(){
        return preferredDate;
    }

    /**
     * Get preferred date in comparable int int.
     *
     * @return the int
     */
    public int getPreferredDateInComparableInt(){
        int day = preferredDate.getDate();
        int month = preferredDate.getMonth()+1;
        int year = preferredDate.getYear()+1900;
        int preferredDateInt = Integer.parseInt(String.format("%4d%02d%02d",year, month, day));
        return preferredDateInt;
    }

    /**
     * Get client client.
     *
     * @return the client
     */
    public Client getClient(){return client;}

    /**
     * Get property property.
     *
     * @return the property
     */
    public Property getProperty(){return property;}

    /**
     * Get subject string.
     *
     * @return the string
     */
    public String getSubject(){return subject;}

    /**
     * Get start of visit int.
     *
     * @return the int
     */
    public int getStartOfVisit(){
        return startOfVisit;
    }

    /**
     * Get end of visit int.
     *
     * @return the int
     */
    public int getEndOfVisit(){
        return endOfVisit;
    }
}
