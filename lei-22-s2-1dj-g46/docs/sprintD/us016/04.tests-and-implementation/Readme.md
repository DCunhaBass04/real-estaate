# US 016 - Sending an Email Response

# 4. Tests

**Test 1:** Check that different Responses are indeed different, and that 2 equal orders are indeed equal
```java
    @Test void ensureTwoResponsesWithSameDataEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        assertEquals(response1, response2);
    }
```
```java
    @Test void ensureTwoResponsesWithDifferentSubjectsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test #1", "This is a text", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test #2", "This is a text", agent, client, "Pending", "n/a");
        assertNotEquals(response1, response2);
    }
```
```java
    @Test void ensureTwoResponsesWithDifferentTextsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text #1", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text #2", agent, client, "Pending", "n/a");
        assertNotEquals(response1, response2);
    }
```
```java
    @Test void ensureTwoResponsesWithDifferentAgentsNotEqual(){
        Agent agent1 = new Agent("johndoeagent@this.app");
        Agent agent2 = new Agent("janedoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text", agent1, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent2, client, "Pending", "n/a");
        assertNotEquals(response1, response2);
    }
```
```java
    @Test void ensureTwoResponsesWithDifferentClientsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client1 = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Client client2 = new Client("Jane Doe", "janedoeclient@this.app", "janedoe");
        Response response1 = new Response("Response Test", "This is a text", agent, client1, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent, client2, "Pending", "n/a");
        assertNotEquals(response1, response2);
    }
```
```java
    @Test void ensureTwoResponsesWithDifferentStatesNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent, client, "Accepted", "n/a");
        assertNotEquals(response1, response2);
    }
```
```java
    @Test void ensureTwoResponsesWithDifferentReasonsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text #1", agent, client, "Declined", "I don't agree");
        Response response2 = new Response("Response Test", "This is a text #2", agent, client, "Declined", "agree don't I");
        assertNotEquals(response1, response2);
    }
```
**Test 2:** Check that a response was correctly added to and removed from the repository
```java
    @Test void responseSuccessfullyAdded(){
        ResponseRepository responseRepository = new ResponseRepository();
        Response response = new Response("Response Test", "This is a text", new Agent("johndoeagent@this.app"), new Client("John Doe", "johndoeclient@this.app", "johndoe"), "Pending", "n/a");
        responseRepository.add(response);
        assertTrue(responseRepository.getResponses().contains(response));
    }
```
```java
    @Test void responseSuccessfullyRemoved(){
        ResponseRepository responseRepository = new ResponseRepository();
        Response response = new Response("Response Test", "This is a text", new Agent("johndoeagent@this.app"), new Client("John Doe", "johndoeclient@this.app", "johndoe"), "Pending", "n/a");
        responseRepository.add(response);
        responseRepository.remove(response);
        assertFalse(responseRepository.getResponses().contains(response));
    }
```

# 5. Construction (Implementation)

## Class AcceptOffersUI
```java
    public void run() {
        String email = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<Property> listOfProperties = ctrl.getPropertiesByCurrentAgent(email);
        for(Property property : listOfProperties){
            manageOrdersByProperty(property);
        }
    }
```
```java
    private void manageOrdersByProperty(Property property){
        VerifyInput verifyInput = new VerifyInput();
        Scanner ler = new Scanner(System.in);
        boolean accepted = false;
        List<Order> listOfOrders = ctrl.getOrdersToXProperty(property);
        System.out.println("Orders for:");
        System.out.println(property);
        printAllOrdersForXProperty(listOfOrders);
        if(!listOfOrders.isEmpty()) {
            for (int i = 0; i < listOfOrders.size() && !accepted; i++) {
                System.out.println("Order no. " + (i + 1));
                boolean response = verifyInput.verifyBoolean(ler, "Do you accept this order");
                if (response) {
                    ctrl.acceptOffer(property, listOfOrders, i);
                    accepted = true;
                    System.out.println("Offer accepted! All other offers for this property were declined.");
                    ler.nextLine();
                } else {
                    ctrl.declineOffer(i);
                }
            }
        }
    }
```

## Class CheckVisitsUI
```java
    public List<Message> respondToXMessage(Message message, List<Message> messageList){
        List<Message> mutableMessageList = new ArrayList<>(messageList);
        System.out.println(message);
        String response = verifyInput.verifyComplexString(input, "your response to this booking request");
        try {
            controller.deleteFromRepositoryAndSendEmail(message, response);
            mutableMessageList.remove(message);
        } catch (MessagingException | IOException e){
            System.out.println("There was an unexpected error. The email was not sent.");
        }
        return Collections.unmodifiableList(mutableMessageList);
    }
```
## Class CheckVisitsController
```java
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
```
## Class SendEmail
```java
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
```

# 6. Integration and Demo

* This one uses the same menu option as US15

# 7. Observations

We made this US in a way that it actually sends an email to the client's email address, the response isn't just sent to a text file.