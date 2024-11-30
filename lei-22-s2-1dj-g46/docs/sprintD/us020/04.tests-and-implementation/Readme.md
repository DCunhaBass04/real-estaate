# US 011 - Accept Offers

# 4. Tests

**Test 1:** Check if two equal responses are equal
```java
    @Test void ensureTwoResponsesWithSameDataEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        assertEquals(response1, response2);
    }
```
**Test 2:** Check if two answers with different states are different
```java
    @Test void ensureTwoResponsesWithDifferentStatesNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text", agent, client, "Pending", "n/a");
        Response response2 = new Response("Response Test", "This is a text", agent, client, "Accepted", "n/a");
        assertNotEquals(response1, response2);
    }
```
**Test 3:** Check if two declined answers with different motives are different
```java
    @Test void ensureTwoResponsesWithDifferentReasonsNotEqual(){
        Agent agent = new Agent("johndoeagent@this.app");
        Client client = new Client("John Doe", "johndoeclient@this.app", "johndoe");
        Response response1 = new Response("Response Test", "This is a text #1", agent, client, "Declined", "I don't agree");
        Response response2 = new Response("Response Test", "This is a text #2", agent, client, "Declined", "agree don't I");
        assertNotEquals(response1, response2);
    }
```
# 5. Construction (Implementation)
## Class ReadResponseUI
```java
    public void printResponseList (List<Response> responseList) {
        if (!responseList.isEmpty()) {
            System.out.println();
            for (int i = 0; i < responseList.size(); i++) {
                Response response = responseList.get(i);
                System.out.println((i + 1) + ". " + response);
            }
        } else {
            System.out.println("There are no responses");
            System.out.println("Press Enter to continue");
            sc.nextLine();
        }
    }
```
## Class ReadResponseController
```java
    public List<Response> getResponsesToClient() {
        String currentUserEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        Client currentClient = Repositories.getInstance().getClientRepository().getClientByEmail(currentUserEmail);
        List<Response> responseList = Repositories.getInstance().getResponseRepository().getResponses("responses");
        responseList = editAndSortLists.getResponsesByClient(responseList, currentClient);
        responseList = editAndSortLists.getResponsesPending(responseList);
        return responseList;
    }
```
```java
    public void changeResponse(Response response) {
        response.setState("Accepted");
    }
```
```java
    public void changeResponseWithReason(Response response, String reason) {
        response.setState("Declined");
        response.setReason(reason);
    }
```

# 6. Integration and Demo

* A new option was added to the Client's menu (both in console and in javaFX).
* A new option was also added to the Agent's menu (just in console), so they can see the state of the responses sent by them.

# 7. Observations

n/a