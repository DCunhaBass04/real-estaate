# US004 - Request for Listing

# 4. Tests

**Test 1:** Check that a property request is not null.
```java
@Test void ensureLandRequestDoesNotEqualNull() {
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    LandRequest land1 = new LandRequest(999.99f, "here", 55.55f, agent, 999.99f, photograph);
    assertNotEquals(land1, null);
}
```
```java
@Test void ensureApartmentRequestDoesNotEqualNull() {
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    ApartmentRequest apartment1 = new ApartmentRequest(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a",  photograph);
    assertNotEquals(apartment1, null);
}
```
```java
@Test void ensureHouseRequestDoesNotEqualNull() {
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    HouseRequest house1 = new HouseRequest(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", true, true, "north",  photograph);
    assertNotEquals(house1, null);
}
```
**Test 2:** Check that a property request is properly added to the repository
```java
@Test void ensureLandRequestIsAddedToList(){
    PropertyRequestRepository requestRepository = new PropertyRequestRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    LandRequest land = new LandRequest(999.99f, "here", 55.55f, agent, 999.99f, photograph);
    requestRepository.add(land);
    assertTrue(requestRepository.getProperties().contains(land));
}
```
```java
@Test void ensureApartmentRequestIsAddedToList(){
    PropertyRequestRepository requestRepository = new PropertyRequestRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    ApartmentRequest apartment = new ApartmentRequest(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", photograph);
    requestRepository.add(apartment);
    assertTrue(requestRepository.getProperties().contains(apartment));
}
```
```java
@Test void ensureHouseRequestIsAddedToList(){
    PropertyRequestRepository requestRepository = new PropertyRequestRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    HouseRequest house = new HouseRequest(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", true, true, "north", photograph);
    requestRepository.add(house);
    assertTrue(requestRepository.getProperties().contains(house));
}
```

# 5. Construction (Implementation)

## Class SendPropertyRequestController
**1. Choosing the Agent**
```java
public Agent chooseAgent(Scanner input, VerifyInput verifyInput){
    System.out.printf("%nWhat agent you want to send the request(s) to?%n");
    AgentRepository agentRepository = Repositories.getInstance().getAgentRepository();
    agentRepository.printAgents();
    int numOfOptions = agentRepository.getSize();
    int option = 0;
    boolean valid = false;
    do{
        option = verifyInput.verifyInt(input, "Agent Number");
        if (!(option < 1 || option > numOfOptions)) {
            valid = true;
        } else {
            System.out.println("Invalid number, try again.");
        }
    }while(!valid);
    return agentRepository.getxAgent(option);
}
```
**2. Filling the request**
```java
public void fillRequest(Scanner input, Agent agent, VerifyInput verifyInput){
    System.out.printf("%nInsert the data in the appropriate format.%nThe same Property cannot be requested more than once.%n%n");
    int type = insertType(input, verifyInput);
    float area = verifyInput.verifyFloat(input, "Area (in meters square)");
    String location = verifyInput.verifyComplexString(input, "Location");
    float distanceFromCityCentre = verifyInput.verifyFloat(input, "Distance from the City Centre (in meters)");
    float requestedPrice = verifyInput.verifyFloat(input, "Requested Price (in euros)");
    File[] photographs = insertPhotographs(input, verifyInput);
    switch (type) {
        case 1 :
            PropertyRequestRepository requestRepository = Repositories.getInstance().getPropertyRequestRepository();
            requestRepository.add(new LandRequest(area, location, distanceFromCityCentre, agent, requestedPrice, photographs));
            break;
        case 2 :
            fillRequestForApartment(area, location, distanceFromCityCentre, agent, requestedPrice, photographs, input, verifyInput);
            break;
        case 3 :
            fillRequestForHouse(area, location, distanceFromCityCentre, agent, requestedPrice, photographs, input, verifyInput);
            break;
    }
    System.out.println("Request sent to the specified Agent!");
}
```


# 6. Integration and Demo

We made sure that the agents are listed for the client to choose and that only the chosen agent can set the commission for a specific property request.

# 7. Observations

n/a
