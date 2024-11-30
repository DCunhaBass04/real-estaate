# US015 - Listing all bookings

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

As decided previously, the interaction with the System starts when the user (Agent) requests to see the booking requests addressed to them.

```java
    public void run(){
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Set Commission from Owner", new SetCommissionUI()));
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
        -->options.add(new MenuItem("Check property visit requests", new CheckVisitsUI()));
        options.add(new MenuItem("Accept/Decline Property Offers", new AcceptOffersUI()));
        options.add(new MenuItem("Check the state of the Responses", new ReadResponseUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");
            if ((option >= 0) && (option < options.size())) {
            options.get(option).run();
            }
        } while (option != -1);
    }
```      

CheckVisitsUI calls a CheckVisitsController...

```java
    public class CheckVisitsUI implements Runnable {
        private final VerifyInput verifyInput = new VerifyInput();
        -->private final CheckVisitsController controller = new CheckVisitsController();
        [...]
```

...that is responsible for getting the necessary data to fulfill the request from the user(Agent).

```java
    public List<Message> getMessages() {
        EditAndSortLists editAndSortLists = new EditAndSortLists();
        String agentID = authenticationRepository.getCurrentUserSession().getUserId().toString();
        List<Message> messages = messageRepository.getMessages();
        messages = editAndSortLists.getMessagesDoneToXAgent(messages, agentID);
        -->return messages;
    }

    public List<Date> getDates(List<Message> messageList){
        List<Date> dateList = new ArrayList<Date>();
        for (int i = 0; i < messageList.size(); i++) {
            dateList.add(messageList.get(i).getPreferredDate());
        }
        -->return dateList;
    }
```

The CheckVisitsUI asks for the user to select a time period...

```java
    public void getTimePeriod(){
        System.out.printf("Available dates for selection:%n");
        dateList = controller.getDates(messageList);
        genericMethods.printFullList(dateList);
        System.out.print("Please chose begin date.");
        -->Date beginDate = (Date)Utils.selectsObject(dateList);
        if (beginDate == null){
            nullDate = true;
        } else {
            -->Date endDate = getEndDate(beginDate);
            if (endDate == null){
                nullDate=true;
            } else {
                messageList = controller.changeListWithTimePeriod(messageList, beginDate, endDate);
            }
        }
    }
```

...and the Controller changes the list accordingly.

```java
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
        -->return messageList;
    }
```

Finally, the CheckVisitsUI shows the booking requests.

```java
    public  void getMessageList(){
        int option = -1;
        while (!messageList.isEmpty() && option != 0) {
            -->genericMethods.printFullList(messageList);
        [...]
```

# 6. Integration and Demo

We made sure the agent cannot chose invalid dates so that they cannot mess up by accident. The less the user has to think, the ,ore comfortable the program is to use.

Everything else happens automatically backdoors to provide the user what they asked for.

# 7. Observations

There are no observations to be made.