package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Message;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class SendEmail {
    public SendEmail(){};

    /**
     * This method gets all the data needed to write a response and creates an email with said data, sending it to the specified recipient
     * @param message the original message
     * @param agent the agent that will be responding
     * @param client the client that will receive said response
     * @param response the response's content
     * @throws MessagingException if there's an error while sending the email
     * @throws IOException if the file 'config.properties' is not found
     */
    public void sendAnEmail(Message message, Agent agent, Client client, String response) throws MessagingException, IOException{
        Properties props = new Properties();
        File file = new File("config.properties");
        Scanner fileReader = new Scanner(file);
        String userName = fileReader.nextLine().replace("email.username = ", "");
        String password = fileReader.nextLine().replace("email.password = ", "");
        String host = fileReader.nextLine().replace("email.smtp.host = ", "");
        String port = fileReader.nextLine().replace("email.smtp.port = ", "");
        String auth = fileReader.nextLine().replace("email.smtp.auth = ", "");
        String protocols = fileReader.nextLine().replace("mail.smtp.ssl.protocols = ", "");
        String enableTLS = fileReader.nextLine().replace("email.smtp.starttls.enable = ", "");
        String trustTLS = fileReader.nextLine().replace("mail.smtp.ssl.trust = ", "");

        String to = client.getEmail();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.ssl.protocols", protocols);
        props.put("mail.smtp.starttls.enable", enableTLS);
        props.put("mail.smtp.ssl.trust", trustTLS);
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(userName));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject("Re: " + message.getSubject());
        email.setText(String.format("Dear Mr/Ms %s,%nYour Booking Request on the property presented below has received " +
                "a response from the responsible agent.%n The response:\"%s\"%n%nProperty info:%n%s%n%nAgent info:%nName: %s%nEmail: %s%n" +
                "Phone Number: %s%n", client.getName(), response, message.getProperty(), agent.getName(), agent.getEmail(), agent.getPhoneNumber()));
        Transport.send(email);
    }
}
