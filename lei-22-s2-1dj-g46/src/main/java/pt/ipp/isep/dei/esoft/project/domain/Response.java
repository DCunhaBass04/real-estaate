package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.Serializable;

/**
 * The type Response.
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subject;
    private String text;
    private Agent sender;
    private Client recipient;
    private String state;
    private String reason;
    private String DEFAULT_REASON = "n/a";

    /**
     * Instantiates a new Response.
     *
     * @param subject   the subject
     * @param text      the text
     * @param sender    the sender
     * @param recipient the recipient
     * @param state     the state
     */
    public Response(String subject, String text, Agent sender, Client recipient, String state){
        this.subject = subject;
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.state = state;
        this.reason = DEFAULT_REASON;
    }

    /**
     * Instantiates a new Response.
     *
     * @param subject   the subject
     * @param text      the text
     * @param sender    the sender
     * @param recipient the recipient
     * @param state     the state
     * @param reason    the reason
     */
    public Response(String subject, String text, Agent sender, Client recipient, String state, String reason){
        this.subject = subject;
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.state = state;
        this.reason = reason;
    }
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (!(object instanceof Response)) {
            return false;
        }
        Response response = (Response) object;
        return subject.equals(response.subject) && text.equals(response.text) &&
                sender.equals(response.sender) && recipient.equals(response.recipient) &&
                state.equals(response.state) && reason.equals(response.reason);
    }

    /**
     * Set state.
     *
     * @param state the state
     */
    public void setState(String state){this.state = state;}

    /**
     * Set reason.
     *
     * @param reason the reason
     */
    public void setReason(String reason){this.reason = reason;}
    public String toString(){
        return String.format("%nFrom: %s%nTo: %s%nContactable via: %s%nSubject: %s%nMessage: %s%nState: %s%nReason: %s%n", sender.getEmail(), recipient.getEmail(),
                recipient.getPhoneNumber(), subject, text, state, reason);
    }
    public Response clone(){return new Response(this.subject, this.text, this.sender, this.recipient, this.state, this.reason);}

    /**
     * Get recipient client.
     *
     * @return the client
     */
    public Client getRecipient(){return recipient;}

    /**
     * Get state string.
     *
     * @return the string
     */
    public String getState(){return state;}

    /**
     * Get sender agent.
     *
     * @return the agent
     */
    public Agent getSender(){return sender;}
}
