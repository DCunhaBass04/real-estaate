# US009 - Leaving a Message

# 4. Tests

**Test 1:** Check that the message is placed in the Repository.

```java
    @Test void testAddMessage() {
        MessageRepository messageRepository = new MessageRepository();
        Agent agent = new Agent("agent@this.app");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Requested", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land property = new Land(123,"Isep",321, agent, photograph, 1000, "25/12", "30/12", request);
        Message message = new Message("agent@this.app", property, "Test", "Client", "999999999", "26/05", 1, 3);
        messageRepository.add(message);
        messageRepository.getMessages().contains(message);
        }
```

**Test 2:** Check that getting the Messages list works.

```java
    @Test void ensureGetMessagesWorks(){
        MessageRepository messageRepository = new MessageRepository();
        List <Message> messageList = messageRepository.getMessages();
    }
```

**Test 3:** Check that the method for checking overlap works.

```java
    @Test boolean checkIfTimeSlotsOverlap(Message message, Message otherMessage){
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
```

**Test 4:** Check that the Message's data is validated.

```java
    @Test boolean ensureValidateMessageWorks(Message message){
        MessageRepository messageRepository = new MessageRepository();
        boolean valid = true;
        String client = message.getPhoneNumber(), otherClient, preferredDate = message.getPreferredDate(), otherPreferredDate;
        List<Message> messages = messageRepository.getMessages();
        for (int i = 0; i < messages.size() && valid; i++) {
        otherClient = messages.get(i).getPhoneNumber();
        otherPreferredDate = messages.get(i).getPreferredDate();
        if (Objects.equals(otherClient, client) && messageRepository.checkIfTimeSlotsOverlap(message, messages.get(i)) && Objects.equals(otherPreferredDate, preferredDate)){
        valid = false;
        System.out.printf("This visit request overlaps with another made previously.%n");
        }
        }
        messageRepository.add(message);
        return valid;
        }
```
# 5. Construction (Implementation)

As decided previously, the interaction with the System starts when the user (Client) requests to visit a property, using the ClientUI to access the LeaveMessageUI.

```java
    public void run(){
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Send Property Request(s) to Agent", new SendPropertyRequestUI()));
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
        -->options.add(new MenuItem("Request to visit a property", new LeaveMessageUI()));
        options.add(new MenuItem("Place an Order", new PlaceOrderUI()));
        options.add(new MenuItem("Check Responses to Orders", new CheckResponsesToOrdersUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
```      

LeaveMessageUI calls a LeaveMessageController...

```java
public class LeaveMessageUI implements Runnable {

    private Message message;
    private List<Property> propertyList;
    -->private final LeaveMessageController controller = new LeaveMessageController();
    [...]
```

...that requests and submits the data for the Message to request the visit.

```java
    public void requestToVisit(){
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        int requests;
        int requestIndex = 0;
        requests = verifyInput.verifyInt( input, "how many visit requests you want to make");
        while (requests != 0){
        requestIndex++;
        System.out.printf("Request number %d:%n", requestIndex);
        -->writeMessage();
        -->sendMessageToAgent();
        requests--;
        }
    }

    public void writeMessage(){
        -->message = controller.requestData(propertyList);
    }        
```

# 6. Integration and Demo

We made sure the user will have no problem sending a message to an agent by never letting them type or register invalid data. Whether it be from being in the wrong format or overlapping with and existing visit request, as stated by AC3.

To that effect, we also made sure to always warn the user about how the operation is going (Whether it was successful or not).

We also made it so the end of the visit cannot coincide or come before its start, as that would make no sense.

# 7. Observations

There are no observations to be made.